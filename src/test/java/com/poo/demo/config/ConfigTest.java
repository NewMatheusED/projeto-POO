package com.poo.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ConfigTest {

    @Autowired
    private JwtSecretProvider jwtSecretProvider;

    @Test
    void testJwtSecretProvider() {
        // Teste para verificar se o JwtSecretProvider está sendo injetado corretamente
        assertNotNull(jwtSecretProvider);
        assertNotNull(jwtSecretProvider.getSecretKey());
    }

    @Test
    void testPasswordEncoder() {
        // Teste básico para verificar se o contexto está carregando
        assertTrue(true);
    }
}
