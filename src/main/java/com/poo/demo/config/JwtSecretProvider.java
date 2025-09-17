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

	public JwtSecretProvider(@Value("${jwt.secret:${JWT_SECRET}}") String secret) {
		// Suporta chave em Base64 ou texto puro
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}
}
