package ru.filin.HavachMVC.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    private HchJdbcUserDetailsManager jdbcUserDetailsManager;

    public WebSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/", "/registration**", "/error**").permitAll()
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
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderJdbcUserDetailsManagerConfigurer = auth.jdbcAuthentication();
        authenticationManagerBuilderJdbcUserDetailsManagerConfigurer
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT email, password, active from usr where email=?")
                .authoritiesByUsernameQuery("SELECT email as username, name as authority FROM usr u\n" +
                        "INNER JOIN user_roles ur ON u.id=ur.user_id\n" +
                        "INNER JOIN roles r on ur.role_id = r.id WHERE email = ?;");
//                .authoritiesByUsernameQuery("SELECT u.email, ur.roles from usr u INNER JOIN user_roles ur ON u.id=ur.userId where u.email=?");
    }

//    @Bean(name = "jdbcUserDetailsManager")
//    public JdbcUserDetailsManager jdbcUserDetailsManager()
//    {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new HchJdbcUserDetailsManager();
//        jdbcUserDetailsManager.setDataSource(dataSource);
//
//        return jdbcUserDetailsManager;
//    }
}
