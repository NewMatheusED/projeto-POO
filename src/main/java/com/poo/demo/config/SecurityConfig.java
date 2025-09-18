package com.poo.demo.config;

/**
 * Configuração de segurança da aplicação.
 * Define as regras de autenticação, autorização e filtros para endpoints protegidos.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.poo.demo.application.TokenBlacklistService;

@Configuration
@EnableScheduling
public class SecurityConfig {

    private final JwtSecretProvider jwtSecretProvider;
    private final TokenBlacklistService tokenBlacklistService;

    @Autowired
    public SecurityConfig(JwtSecretProvider jwtSecretProvider, TokenBlacklistService tokenBlacklistService) {
        this.jwtSecretProvider = jwtSecretProvider;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    /**
     * Configura o filtro de segurança do Spring Security.
     * Libera o endpoint de login e protege os demais endpoints, adicionando o filtro JWT.
     *
     * @param http objeto de configuração do HttpSecurity
     * @return SecurityFilterChain configurado
     * @throws Exception em caso de erro na configuração
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/cadastro").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/docs/**").permitAll()
                .requestMatchers("/api/v1/health").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtSecretProvider, tokenBlacklistService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Bean para criptografia de senhas usando BCrypt.
     *
     * @return PasswordEncoder configurado com BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}