package com.poo.demo.config;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import com.poo.demo.config.JwtSecretProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtSecretProvider jwtSecretProvider;

    public JwtAuthenticationFilter(JwtSecretProvider jwtSecretProvider) {
        this.jwtSecretProvider = jwtSecretProvider;
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
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            try {
                var claims = Jwts.parser()
                    .setSigningKey(jwtSecretProvider.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();

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
        }
        filterChain.doFilter(request, response);
    }
}