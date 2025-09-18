package com.poo.demo.config;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

/**
 * Provedor centralizado da chave secreta JWT.
 * Lê a chave do application.properties ou variável de ambiente.
 * Fornece a SecretKey para uso em toda a aplicação.
 */
@Component
public class JwtSecretProvider {

	private final SecretKey secretKey;

	public JwtSecretProvider(@Value("${jwt.secret:${JWT_SECRET:}}") String secret) {
		if (secret == null || secret.trim().isEmpty()) {
			throw new IllegalArgumentException("JWT secret não pode ser nulo ou vazio. Configure jwt.secret ou JWT_SECRET");
		}
		
		SecretKey tempKey;
		try {
			// Suporta chave em Base64 ou texto puro
			byte[] keyBytes = Base64.getDecoder().decode(secret);
			tempKey = Keys.hmacShaKeyFor(keyBytes);
		} catch (IllegalArgumentException e) {
			// Se falhar ao decodificar Base64, usa o texto como chave diretamente
			tempKey = Keys.hmacShaKeyFor(secret.getBytes());
		}
		this.secretKey = tempKey;
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}
}
