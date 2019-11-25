package ru.filin.HavachMVC.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.filin.HavachMVC.model.userManagement.repositories.impl.UserRepositoryImpl;
import ru.filin.HavachMVC.service.userManagement.impl.UserServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private UserDetailsService userService;

    public WebSecurityConfiguration(DataSource dataSource, JdbcTemplate jdbcTemplate, @Qualifier("userServiceImpl") UserDetailsService userService) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/", "/registration**", "/error**", "/static**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserServiceImpl(new UserRepositoryImpl(jdbcTemplate)))
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
