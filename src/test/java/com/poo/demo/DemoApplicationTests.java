package com.poo.demo;

import com.poo.demo.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class DemoApplicationTests {

	@Test
	void contextLoads() {
		// Teste básico para verificar se o contexto Spring carrega corretamente
		// As configurações de teste estão no application-test.properties
	}

}
