package com.Ems.EmployeeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
class AppConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUserDetails = User.builder().
                username("Mukul@gmail.com")
                .password(passwordEncoder().encode("Mukul123"))
                .roles("USER").
                build();
        UserDetails adminUserDetails = User.builder().
                username("itt@gmail.com")
                .password(passwordEncoder().encode("itt123"))
                .roles("ADMIN").
                build();
        return new InMemoryUserDetailsManager(normalUserDetails,adminUserDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}