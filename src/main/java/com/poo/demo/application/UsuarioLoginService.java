package com.poo.demo.application;

/**
 * Serviço responsável pelo cadastro e autenticação de usuários.
 * Realiza validação de email, criptografia de senha, geração de token JWT e autenticação.
 */
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poo.demo.config.JwtProperties;
import com.poo.demo.config.JwtSecretProvider;
import com.poo.demo.domain.dto.UsuarioCadastroDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.domain.entity.UsuarioLogin;
import com.poo.demo.domain.repository.UsuarioLoginRepository;

import io.jsonwebtoken.Jwts;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UsuarioLoginService {


    private final UsuarioLoginRepository usuarioLoginRepository;
    private final JwtSecretProvider jwtSecretProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final TokenBlacklistService tokenBlacklistService;

    @Autowired
    public UsuarioLoginService(UsuarioLoginRepository usuarioLoginRepository, 
                              JwtSecretProvider jwtSecretProvider,
                              PasswordEncoder passwordEncoder,
                              JwtProperties jwtProperties,
                              TokenBlacklistService tokenBlacklistService) {
        this.usuarioLoginRepository = usuarioLoginRepository;
        this.jwtSecretProvider = jwtSecretProvider;
        this.passwordEncoder = passwordEncoder;
        this.jwtProperties = jwtProperties;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    /**
     * Realiza o cadastro de um novo usuário.
     * Valida se o email já existe e criptografa a senha antes de salvar.
     * Retorna ApiResponse para facilitar o uso no front-end.
     *
     * @param dto DTO com dados do usuário para cadastro
     * @return ApiResponse com usuário cadastrado ou erro
     */
    public ApiResponse<UsuarioLogin> cadastro(UsuarioCadastroDto dto) {
        if (usuarioLoginRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ApiResponse.error("Email já cadastrado", 400);
        }
        UsuarioLogin usuario = new UsuarioLogin();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); 
        UsuarioLogin salvo = usuarioLoginRepository.save(usuario);
        return ApiResponse.success(salvo);
    }

    /**
     * Realiza o login do usuário e retorna um token JWT.
     * Valida email e senha utilizando BCrypt.
     * Retorna ApiResponse para facilitar o uso no front-end.
     *
     * @param email Email do usuário
     * @param password Senha do usuário
     * @return ApiResponse com token JWT ou erro
     */
    public ApiResponse<String> login(String email, String password) {
        Optional<UsuarioLogin> usuarioOpt = usuarioLoginRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            UsuarioLogin usuario = usuarioOpt.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                String token = gerarToken(usuario);
                return ApiResponse.success(token);
            }
        }
        return ApiResponse.error("Email ou senha inválidos", 401);
    }

    /**
     * Renova o token JWT do usuário autenticado.
     * Útil para evitar que o usuário precise fazer login novamente.
     *
     * @param username Nome do usuário autenticado
     * @return ApiResponse com novo token JWT ou erro
     */
    public ApiResponse<String> renovarToken(String username) {
        Optional<UsuarioLogin> usuarioOpt = usuarioLoginRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            String novoToken = gerarToken(usuarioOpt.get());
            return ApiResponse.success(novoToken);
        }
        return ApiResponse.error("Usuário não encontrado", 404);
    }

    /**
     * Realiza logout do usuário.
     * Invalida todos os tokens do usuário na blacklist para maior segurança.
     *
     * @param username Nome do usuário que está fazendo logout
     * @return ApiResponse confirmando logout ou erro
     */
    public ApiResponse<String> logout(String username) {
        Optional<UsuarioLogin> usuarioOpt = usuarioLoginRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            // Invalidar todos os tokens do usuário na blacklist
            tokenBlacklistService.invalidateAllUserTokens(username, "logout");
            return ApiResponse.success("Logout realizado com sucesso");
        }
        return ApiResponse.error("Usuário não encontrado", 404);
    }

    /**
     * Invalida um token específico (logout de token específico).
     * Útil para logout seletivo ou em caso de comprometimento de token.
     *
     * @param token Token JWT a ser invalidado
     * @param username Nome do usuário proprietário do token
     * @return ApiResponse confirmando invalidação ou erro
     */
    public ApiResponse<String> invalidateToken(String token, String username) {
        try {
            var claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecretProvider.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

            String jti = claims.getId();
            LocalDateTime tokenExpiresAt = claims.getExpiration().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (jti != null) {
                tokenBlacklistService.addToBlacklist(jti, username, tokenExpiresAt, "manual_invalidation");
                return ApiResponse.success("Token invalidado com sucesso");
            }
            
            return ApiResponse.error("Token inválido", 400);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao invalidar token", 500);
        }
    }

    /**
     * Gera um token JWT para o usuário autenticado.
     *
     * @param usuario Usuário autenticado
     * @return Token JWT
     */
    private String gerarToken(UsuarioLogin usuario) {
        // Usa configuração do JwtProperties ou fallback para 1 hora
        long expirationTime = System.currentTimeMillis() + 
            (jwtProperties.getExpiration() > 0 ? jwtProperties.getExpiration() : 3600000);

        return Jwts.builder()
            .setSubject(usuario.getUsername())
            .claim("email", usuario.getEmail())
            .claim("username", usuario.getUsername())
            .setId(java.util.UUID.randomUUID().toString()) // JTI para blacklist
            .setIssuedAt(new Date())
            .setExpiration(new Date(expirationTime))
            .signWith(jwtSecretProvider.getSecretKey())
            .compact();
    }
}
