package ru.filin.HavachMVC.configuration;//package ru.filin.havach.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.security.Principal;
//
//@Configuration
////@EnableWebSecurity
//public class webSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .mvcMatchers("/", "login**", "/js/**", "/error**").permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/").permitAll()
//                .and()
//                .csrf().disable();
//    }
//
////    @Bean
////    public PrincipalExtractor principalExtractor(UserDetailsRepository userDetailsRepository) {
////        return map -> {
////            return new User();
////        }
////    }
//}
