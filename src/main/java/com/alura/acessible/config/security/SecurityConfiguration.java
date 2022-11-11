package com.alura.acessible.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("prd")
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
            .and() 
            .authorizeHttpRequests()
                //Tarefas
                .antMatchers(HttpMethod.GET, "/api/**/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/**/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/**/**").permitAll()
                
                //Usuários
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()

                // WEB
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**/delete/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
               
                
                //h2
                .antMatchers("/h2-console/**").permitAll()

                .anyRequest().permitAll()
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                .formLogin()
                //.loginPage("/meulogin")
                //.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();
    }

     @Bean
     public UserDetailsService users(){
         UserDetails user = User.builder()
             .username("joao@fiap.com.br")
             .password("$2a$12$fJRy5k93hXvZbgFvXS0eN.FjZ5ykqjYHIhuNDTr62C/Pj10y6eorG")
             .roles("USER")
             .build();

         return new InMemoryUserDetailsManager(user);
     }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
    
}
