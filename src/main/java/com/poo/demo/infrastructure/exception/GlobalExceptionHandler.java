package com.poo.demo.infrastructure.exception;

import com.poo.demo.domain.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

/**
 * Handler global de exceções
 * Segue o princípio de responsabilidade única (SRP)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções relacionadas a clientes HTTP
     * @param e Exceção capturada
     * @return Resposta de erro padronizada
     */
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse<String>> handleRestClientException(RestClientException e) {
        ApiResponse<String> response = ApiResponse.error(
            "Erro na comunicação com API externa: " + e.getMessage(), 
            500
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Trata exceções genéricas
     * @param e Exceção capturada
     * @return Resposta de erro padronizada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception e) {
        ApiResponse<String> response = ApiResponse.error(
            "Erro interno do servidor: " + e.getMessage(), 
            500
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Trata exceções de validação
     * @param e Exceção capturada
     * @return Resposta de erro padronizada
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        ApiResponse<String> response = ApiResponse.error(
            "Parâmetro inválido: " + e.getMessage(), 
            400
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
