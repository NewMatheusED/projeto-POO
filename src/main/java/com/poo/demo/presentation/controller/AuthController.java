package com.poo.demo.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.demo.application.UsuarioLoginService;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.http.ResponseEntity;


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
    public ResponseEntity<ApiResponse<String>> login(@RequestBody com.poo.demo.domain.dto.UsuarioLoginDto dto) {
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
    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResponse<com.poo.demo.domain.entity.UsuarioLogin>> cadastrar(@RequestBody com.poo.demo.domain.dto.UsuarioCadastroDto dto) {
        ApiResponse<com.poo.demo.domain.entity.UsuarioLogin> response = usuarioLoginService.cadastrar(dto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
}
