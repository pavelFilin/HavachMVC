package ru.filin.HavachMVC.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:db.properties", ignoreResourceNotFound = true)
public class DBConfig {

    private final Environment environment;

    public DBConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String property = environment.getProperty("jdbc.driver");
        dataSource.setDriverClassName(property);
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }
}
