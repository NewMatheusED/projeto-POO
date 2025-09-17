package com.poo.demo.application;

/**
 * Serviço responsável pelo cadastro e autenticação de usuários.
 * Realiza validação de email, criptografia de senha, geração de token JWT e autenticação.
 */
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poo.demo.config.JwtSecretProvider;
import com.poo.demo.domain.dto.UsuarioCadastroDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.domain.entity.UsuarioLogin;
import com.poo.demo.domain.repository.UsuarioLoginRepository;

import io.jsonwebtoken.Jwts;

@Service
public class UsuarioLoginService {


    private final UsuarioLoginRepository usuarioLoginRepository;
    private final JwtSecretProvider jwtSecretProvider;

    @Autowired
    public UsuarioLoginService(UsuarioLoginRepository usuarioLoginRepository, JwtSecretProvider jwtSecretProvider) {
        this.usuarioLoginRepository = usuarioLoginRepository;
        this.jwtSecretProvider = jwtSecretProvider;
    }

    /**
     * Realiza o cadastro de um novo usuário.
     * Valida se o email já existe e criptografa a senha antes de salvar.
     * Retorna ApiResponse para facilitar o uso no front-end.
     *
     * @param dto DTO com dados do usuário para cadastro
     * @return ApiResponse com usuário cadastrado ou erro
     */
    public ApiResponse<UsuarioLogin> cadastrar(UsuarioCadastroDto dto) {
        if (usuarioLoginRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ApiResponse.error("Email já cadastrado", 400);
        }
        UsuarioLogin usuario = new UsuarioLogin();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword())); 
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
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, usuario.getPassword())) {
                String token = gerarToken(usuario);
                return ApiResponse.success(token);
            }
        }
        return ApiResponse.error("Email ou senha inválidos", 401);
    }

    /**
     * Gera um token JWT para o usuário autenticado.
     *
     * @param usuario Usuário autenticado
     * @return Token JWT no formato Bearer
     */
    private String gerarToken(UsuarioLogin usuario) {
        long expirationTime = System.currentTimeMillis() + 3600000; // 1 hora em milissegundos

        return "Bearer " + Jwts.builder()
            .setSubject(usuario.getUsername())
            .claim("email", usuario.getEmail())
            .claim("username", usuario.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(expirationTime))
            .signWith(jwtSecretProvider.getSecretKey())
            .compact();
    }
}
