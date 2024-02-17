package com.Ems.EmployeeManagementSystem.config;

import com.Ems.EmployeeManagementSystem.constants.AuthConstants;
import com.Ems.EmployeeManagementSystem.constants.SecurityConstants;
import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationEntryPoint;
import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers(HttpMethod.POST, SecurityConstants.EMP_PATH).hasRole(AuthConstants.ADMIN_ROLE);
                authorize.requestMatchers(HttpMethod.PUT, SecurityConstants.EMP_PATH).hasRole(AuthConstants.ADMIN_ROLE);
                authorize.requestMatchers(HttpMethod.DELETE, SecurityConstants.EMP_PATH).hasRole(AuthConstants.ADMIN_ROLE);
                authorize.requestMatchers(HttpMethod.GET, SecurityConstants.EMP_PATH).hasAnyRole(AuthConstants.ADMIN_ROLE, AuthConstants.USER_ROLE);
                authorize.requestMatchers(AuthConstants.AUTH+AuthConstants.LOGIN).permitAll();
                authorize.anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults());
    http.exceptionHandling(exception -> exception
            .authenticationEntryPoint(jwtAuthenticationEntryPoint));
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
}