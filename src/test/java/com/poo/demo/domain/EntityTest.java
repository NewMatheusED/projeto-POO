package com.poo.demo.domain;

import com.poo.demo.domain.entity.UsuarioLogin;
import com.poo.demo.domain.entity.TokenBlacklist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste para verificar se as entidades estão sendo carregadas corretamente
 */
class EntityTest {

    @Test
    void testUsuarioLoginEntity() {
        // Teste básico para verificar se a entidade UsuarioLogin está funcionando
        UsuarioLogin usuario = new UsuarioLogin();
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password123");

        assertEquals("testuser", usuario.getUsername());
        assertEquals("test@example.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
    }

    @Test
    void testTokenBlacklistEntity() {
        // Teste básico para verificar se a entidade TokenBlacklist está funcionando
        TokenBlacklist blacklist = new TokenBlacklist();
        blacklist.setJti("test-jti");
        blacklist.setUsername("testuser");
        blacklist.setReason("test");

        assertEquals("test-jti", blacklist.getJti());
        assertEquals("testuser", blacklist.getUsername());
        assertEquals("test", blacklist.getReason());
    }
}
