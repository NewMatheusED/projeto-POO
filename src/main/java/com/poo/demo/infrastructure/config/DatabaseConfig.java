package com.poo.demo.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Configuração personalizada para o banco de dados.
 * 
 * Esta classe resolve o problema de conversão da DATABASE_URL do Render
 * de postgresql:// para jdbc:postgresql://, seguindo os princípios SOLID:
 * 
 * - Single Responsibility: Responsável apenas pela configuração do DataSource
 * - Open/Closed: Pode ser estendida sem modificação
 * - Dependency Inversion: Depende de abstrações (DataSource)
 */
@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Value("${DB_HOST:localhost}")
    private String dbHost;

    @Value("${DB_PORT:5432}")
    private String dbPort;

    @Value("${DB_NAME:analise_senadores}")
    private String dbName;

    @Value("${DB_USER:postgres}")
    private String dbUser;

    @Value("${DB_PASSWORD:password}")
    private String dbPassword;

    /**
     * Configura o DataSource principal.
     * 
     * Se DATABASE_URL estiver definida (ambiente Render), faz o parsing correto da URL
     * Caso contrário, usa as variáveis individuais.
     * 
     * @return DataSource configurado
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // Parse da URL do Render: postgresql://user:password@host:port/database
            try {
                String jdbcUrl = parseDatabaseUrl(databaseUrl);
                config.setJdbcUrl(jdbcUrl);
                
                // Extrai credenciais da URL
                String[] credentials = extractCredentials(databaseUrl);
                config.setUsername(credentials[0]);
                config.setPassword(credentials[1]);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao fazer parsing da DATABASE_URL: " + databaseUrl, e);
            }
        } else {
            // Usa variáveis individuais
            String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?useSSL=true", 
                                          dbHost, dbPort, dbName);
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(dbUser);
            config.setPassword(dbPassword);
        }
        
        // Configurações de pool de conexões otimizadas para produção
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(60000);
        
        // Configurações específicas do PostgreSQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        
        return new HikariDataSource(config);
    }
    
    /**
     * Faz o parsing da URL do Render para o formato JDBC correto.
     * 
     * @param databaseUrl URL no formato: postgresql://user:password@host:port/database
     * @return URL no formato JDBC: jdbc:postgresql://host:port/database
     */
    private String parseDatabaseUrl(String databaseUrl) {
        // Remove o prefixo postgresql://
        String urlWithoutPrefix = databaseUrl.replaceFirst("^postgresql://", "");
        
        // Encontra o @ que separa credenciais do host
        int atIndex = urlWithoutPrefix.indexOf('@');
        if (atIndex == -1) {
            throw new IllegalArgumentException("URL inválida: não encontrou @ para separar credenciais");
        }
        
        // Pega a parte após o @ (host:port/database)
        String hostAndDatabase = urlWithoutPrefix.substring(atIndex + 1);
        
        // Constrói a URL JDBC
        return "jdbc:postgresql://" + hostAndDatabase + "?useSSL=true";
    }
    
    /**
     * Extrai username e password da URL do Render.
     * 
     * @param databaseUrl URL no formato: postgresql://user:password@host:port/database
     * @return Array com [username, password]
     */
    private String[] extractCredentials(String databaseUrl) {
        // Remove o prefixo postgresql://
        String urlWithoutPrefix = databaseUrl.replaceFirst("^postgresql://", "");
        
        // Encontra o @ que separa credenciais do host
        int atIndex = urlWithoutPrefix.indexOf('@');
        if (atIndex == -1) {
            throw new IllegalArgumentException("URL inválida: não encontrou @ para separar credenciais");
        }
        
        // Pega a parte antes do @ (user:password)
        String credentials = urlWithoutPrefix.substring(0, atIndex);
        
        // Encontra o : que separa user de password
        int colonIndex = credentials.indexOf(':');
        if (colonIndex == -1) {
            throw new IllegalArgumentException("URL inválida: não encontrou : para separar user e password");
        }
        
        String username = credentials.substring(0, colonIndex);
        String password = credentials.substring(colonIndex + 1);
        
        return new String[]{username, password};
    }
}
