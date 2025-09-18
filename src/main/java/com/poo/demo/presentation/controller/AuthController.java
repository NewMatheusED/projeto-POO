package com.poo.demo.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.demo.application.UsuarioLoginService;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private UsuarioLoginService usuarioLoginService;

    /**
     * Endpoint para login do usuário.
     * Recebe email e senha via JSON, retorna ApiResponse com token JWT ou erro.
     *
     * @param dto DTO com email e senha do usuário
     * @return ResponseEntity<ApiResponse<String>> com token JWT ou mensagem de erro
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody com.poo.demo.domain.dto.UsuarioLoginDto dto) {
        ApiResponse<String> response = usuarioLoginService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Endpoint para cadastro de novo usuário.
     * Recebe dados via JSON, cadastra usuário e retorna ApiResponse com usuário ou erro.
     *
     * @param dto DTO com dados do usuário para cadastro
     * @return ResponseEntity<ApiResponse<UsuarioLogin>> com usuário cadastrado ou mensagem de erro
     */
    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<com.poo.demo.domain.entity.UsuarioLogin>> cadastro(@Valid @RequestBody com.poo.demo.domain.dto.UsuarioCadastroDto dto) {
        ApiResponse<com.poo.demo.domain.entity.UsuarioLogin> response = usuarioLoginService.cadastro(dto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Endpoint para renovar token JWT.
     * Requer autenticação válida para funcionar.
     * Útil para evitar que o usuário precise fazer login novamente.
     *
     * @return ResponseEntity<ApiResponse<String>> com novo token JWT
     */
    @PostMapping("/renovar-token")
    public ResponseEntity<ApiResponse<String>> renovarToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(ApiResponse.error("Usuário não autenticado", 401));
        }
        
        String username = authentication.getName();
        ApiResponse<String> response = usuarioLoginService.renovarToken(username);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Endpoint para logout do usuário.
     * Requer autenticação válida para funcionar.
     * Invalida a sessão do usuário no servidor.
     *
     * @return ResponseEntity<ApiResponse<String>> confirmando logout
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(ApiResponse.error("Usuário não autenticado", 401));
        }
        
        String username = authentication.getName();
        ApiResponse<String> response = usuarioLoginService.logout(username);
        
        // Limpar o contexto de segurança
        SecurityContextHolder.clearContext();
        
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
}
