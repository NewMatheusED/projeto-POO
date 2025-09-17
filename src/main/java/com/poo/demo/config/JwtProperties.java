package com.poo.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Propriedades de configuração do JWT.
 * Centraliza valores como segredo e tempo de expiração.
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	/**
	 * Chave secreta do JWT (em Base64).
	 */
	private String secret;

	/**
	 * Tempo de expiração do token em milissegundos.
	 */
	private long expiration;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
}
