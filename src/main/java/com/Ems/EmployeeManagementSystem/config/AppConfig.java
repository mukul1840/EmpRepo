package com.Ems.EmployeeManagementSystem.config;

import com.Ems.EmployeeManagementSystem.constants.AuthConstants;
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
                username(AuthConstants.NORMAL_USER)
                .password(passwordEncoder().encode(AuthConstants.NORMAL_USER_PASSWORD))
                .roles(AuthConstants.USER_ROLE).
                build();
        UserDetails adminUserDetails = User.builder().
                username(AuthConstants.ADMIN_USER)
                .password(passwordEncoder().encode(AuthConstants.ADMIN_USER_PASSWORD))
                .roles(AuthConstants.ADMIN_ROLE).
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