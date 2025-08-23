package com.poo.demo.application.service;

import com.poo.demo.domain.dto.UserDto;
import com.poo.demo.domain.entity.ApiResponse;
import com.poo.demo.infrastructure.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço específico para gerenciar usuários de API externa
 * Segue o princípio de responsabilidade única (SRP)
 */
@Service
public class UserService {

    private final HttpClient httpClient;
    private static final String JSONPLACEHOLDER_API = "https://jsonplaceholder.typicode.com";

    @Autowired
    public UserService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Busca todos os usuários da API externa
     * @return Lista de usuários
     */
    public ApiResponse<UserDto[]> buscarTodosUsuarios() {
        try {
            String url = JSONPLACEHOLDER_API + "/users";
            UserDto[] users = httpClient.get(url, UserDto[].class);
            return ApiResponse.success(users);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar usuários: " + e.getMessage(), 500);
        }
    }

    /**
     * Busca um usuário específico por ID
     * @param id ID do usuário
     * @return Usuário encontrado
     */
    public ApiResponse<UserDto> buscarUsuarioPorId(Long id) {
        try {
            String url = JSONPLACEHOLDER_API + "/users/" + id;
            UserDto user = httpClient.get(url, UserDto.class);
            return ApiResponse.success(user);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao buscar usuário: " + e.getMessage(), 500);
        }
    }

    /**
     * Cria um novo usuário na API externa
     * @param user Dados do usuário
     * @return Usuário criado
     */
    public ApiResponse<UserDto> criarUsuario(UserDto user) {
        try {
            String url = JSONPLACEHOLDER_API + "/users";
            UserDto createdUser = httpClient.post(url, user, UserDto.class);
            return ApiResponse.success(createdUser);
        } catch (Exception e) {
            return ApiResponse.error("Erro ao criar usuário: " + e.getMessage(), 500);
        }
    }
}
