package com.poo.demo.presentation.controller;

import com.poo.demo.application.service.UserService;
import com.poo.demo.domain.dto.UserDto;
import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller específico para gerenciar usuários
 * Segue o princípio de responsabilidade única (SRP)
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para buscar todos os usuários
     * @return Lista de usuários
     */
    @GetMapping
    public ResponseEntity<ApiResponse<UserDto[]>> buscarTodosUsuarios() {
        ApiResponse<UserDto[]> response = userService.buscarTodosUsuarios();
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para buscar usuário por ID
     * @param id ID do usuário
     * @return Usuário encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> buscarUsuarioPorId(@PathVariable Long id) {
        ApiResponse<UserDto> response = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para criar novo usuário
     * @param user Dados do usuário
     * @return Usuário criado
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> criarUsuario(@RequestBody UserDto user) {
        ApiResponse<UserDto> response = userService.criarUsuario(user);
        return ResponseEntity.ok(response);
    }
}
