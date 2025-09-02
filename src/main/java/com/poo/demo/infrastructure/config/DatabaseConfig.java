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
     * Se DATABASE_URL estiver definida (ambiente Render), converte de postgresql:// para jdbc:postgresql://
     * Caso contrário, usa as variáveis individuais.
     * 
     * @return DataSource configurado
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        
        String jdbcUrl;
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // Converte postgresql:// para jdbc:postgresql://
            jdbcUrl = databaseUrl.replaceFirst("^postgresql://", "jdbc:postgresql://");
            config.setJdbcUrl(jdbcUrl);
        } else {
            // Usa variáveis individuais
            jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?useSSL=true", 
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
}
