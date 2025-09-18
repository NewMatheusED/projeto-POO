package com.poo.demo.integration;

import com.poo.demo.application.UsuarioLoginService;
import com.poo.demo.domain.dto.UsuarioCadastroDto;
import com.poo.demo.domain.entity.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.flyway.enabled=false",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "jwt.secret=test-secret-key-for-testing-only",
    "jwt.expiration=3600000"
})
@Transactional
class AuthIntegrationTest {

    @Autowired
    private UsuarioLoginService usuarioLoginService;

    @Test
    void testCadastroUsuario() {
        // Arrange
        UsuarioCadastroDto dto = new UsuarioCadastroDto();
        dto.setUsername("testuser");
        dto.setEmail("test@example.com");
        dto.setPassword("123456");

        // Act
        ApiResponse<?> response = usuarioLoginService.cadastro(dto);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void testLoginUsuario() {
        // Arrange
        UsuarioCadastroDto dto = new UsuarioCadastroDto();
        dto.setUsername("testuser2");
        dto.setEmail("test2@example.com");
        dto.setPassword("123456");
        
        // Cadastrar usuário primeiro
        usuarioLoginService.cadastro(dto);

        // Act
        ApiResponse<String> response = usuarioLoginService.login("test2@example.com", "123456");

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getData());
        assertFalse(response.getData().isEmpty());
    }

    @Test
    void testLoginComCredenciaisInvalidas() {
        // Act
        ApiResponse<String> response = usuarioLoginService.login("inexistente@example.com", "senhaerrada");

        // Assert
        assertFalse(response.isSuccess());
        assertEquals(401, response.getStatusCode());
    }
}
