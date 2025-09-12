package com.poo.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.demo.domain.entity.UsuarioLogin;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
    /**
     * Busca um usuário pelo email.
     * @param email Email do usuário
     * @return Optional contendo o usuário, se encontrado
     */
    Optional<UsuarioLogin> findByEmail(String email);
}