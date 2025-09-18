package com.poo.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsuarioLogin {

    /**
     * Identificador único do usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome de usuário utilizado para login.
     */
    private String username;

    /**
     * Email do usuário, utilizado para autenticação e contato.
     */
    private String email;

    /**
     * Senha do usuário (armazenada de forma criptografada).
     */
    private String password;

    /**
     * Retorna o identificador único do usuário.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do usuário.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome de usuário.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o nome de usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retorna o email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário (criptografada).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário (deve ser criptografada).
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
