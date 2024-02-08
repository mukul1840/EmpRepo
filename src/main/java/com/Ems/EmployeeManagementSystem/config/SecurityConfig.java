package com.Ems.EmployeeManagementSystem.config;

import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationEntryPoint;
import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize) -> {
                authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
                authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
                authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                authorize.requestMatchers("/auth/login").permitAll();
                authorize.anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults());
    http.exceptionHandling(exception -> exception
            .authenticationEntryPoint(point));
    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

}