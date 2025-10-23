package com.ntsabelle.userservicespringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for development environment.
 * - Permits all requests (no authentication)
 * - Enables access to H2 console
 * - Disables CSRF and frame protection (required for H2 console)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the HTTP security filter chain.
     * - Disables CSRF protection (safe for dev, not for production)
     * - Allows access to H2 console at /h2-console/**
     * - Permits all other requests without authentication
     * - Disables frame options to allow H2 console iframe rendering
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .headers().frameOptions().disable();

        return http.build();
    }

    /**
     * Provides a BCrypt password encoder bean.
     * Used for hashing user passwords before storing in the database.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
