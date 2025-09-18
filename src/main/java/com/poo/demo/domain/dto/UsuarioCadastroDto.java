package com.poo.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCadastroDto {
    
    /**
     * Nome de usuário para cadastro.
     */
    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    private String username;

    /**
     * Email do usuário para cadastro.
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter formato válido")
    private String email;

    /**
     * Senha do usuário para cadastro.
     */
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
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
