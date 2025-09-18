package com.poo.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter formato válido")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
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
