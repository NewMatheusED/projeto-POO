package com.poo.demo.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.poo.demo.application.TokenBlacklistService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtSecretProvider jwtSecretProvider;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtSecretProvider jwtSecretProvider, TokenBlacklistService tokenBlacklistService) {
        this.jwtSecretProvider = jwtSecretProvider;
        this.tokenBlacklistService = tokenBlacklistService;
    }
    
    /**
     * Executa a validação do token JWT presente no header Authorization.
     * Caso o token seja válido, autentica o usuário no contexto de segurança.
     * Caso contrário, retorna status 401 e mensagem personalizada.
     *
     * @param request requisição HTTP
     * @param response resposta HTTP
     * @param filterChain cadeia de filtros
     * @throws ServletException em caso de erro de servlet
     * @throws IOException em caso de erro de IO
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        String requestPath = request.getRequestURI();
        
        // Endpoints públicos que não precisam de autenticação
        if (isPublicEndpoint(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Token de autorização necessário\"}");
            return;
        }
        
        String token = header.replace("Bearer ", "");
        try {
            var claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretProvider.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            // Verificar se o token está na blacklist
            String jti = claims.getId();
            if (jti != null && tokenBlacklistService.isTokenBlacklisted(jti)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\": \"Token foi invalidado. Faça login novamente.\"}");
                return;
            }

            String username = claims.get("username", String.class);
            if (username != null) {
                var auth = new UsernamePasswordAuthenticationToken(username, null, java.util.Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"O Token ou seu uso está inválido\"}");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
    
    /**
     * Verifica se o endpoint é público e não precisa de autenticação.
     * 
     * @param requestPath caminho da requisição
     * @return true se o endpoint é público
     */
    private boolean isPublicEndpoint(String requestPath) {
        return requestPath.startsWith("/api/v1/auth/login") ||
               requestPath.startsWith("/api/v1/auth/cadastro") ||
               requestPath.startsWith("/swagger-ui") ||
               requestPath.startsWith("/v3/api-docs") ||
               requestPath.startsWith("/docs") ||
               requestPath.equals("/api/v1/health") ||
               requestPath.equals("/actuator/health");
    }
}