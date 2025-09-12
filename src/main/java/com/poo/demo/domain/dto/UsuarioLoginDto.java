package com.poo.demo.domain.dto;

public class UsuarioLoginDto {
    private String email;
    private String password;
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
     * Retorna a senha do usuário.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Define a senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
