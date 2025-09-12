package com.poo.demo.domain.dto;

public class UsuarioCadastroDto {
    /**
     * Nome de usuário para cadastro.
     */
    private String username;

    /**
     * Email do usuário para cadastro.
     */
    private String email;

    /**
     * Senha do usuário para cadastro.
     */
    private String password;

    /**
     * Retorna o nome de usuário.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Retorna o email do usuário.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Retorna a senha do usuário.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Define o nome de usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Define o email do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Define a senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
