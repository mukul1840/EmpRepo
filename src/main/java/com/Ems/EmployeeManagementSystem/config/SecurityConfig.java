package com.Ems.EmployeeManagementSystem.config;

import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationEntryPoint;
import com.Ems.EmployeeManagementSystem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;



//
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .requestMatchers(
                                new AntPathRequestMatcher("/auth/login")).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/**", HttpMethod.GET.toString())).hasAnyRole("USER_ROLE", "ADMIN_ROLE")
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/**", HttpMethod.POST.toString())).hasRole("ADMIN_ROLE")
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/**", HttpMethod.PUT.toString())).hasRole("ADMIN_ROLE")
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/**", HttpMethod.DELETE.toString())).hasRole("ADMIN_ROLE")
                        .requestMatchers(
                                new AntPathRequestMatcher("/ems")).authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//    @Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    http.csrf(csrf -> csrf.disable())
//            .authorizeRequests().
//            requestMatchers("/ems").authenticated().requestMatchers("/auth/login").permitAll()
//            .anyRequest()
//            .authenticated()
//            .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//}





}