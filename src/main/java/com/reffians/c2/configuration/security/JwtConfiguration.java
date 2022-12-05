package com.reffians.c2.configuration.security;

import com.reffians.c2.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** JWT Configuration. */
@EnableWebSecurity
@Configuration
public class JwtConfiguration {

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  /** Security Filter Chain that adds the JWT authentication. */
  @Bean
  public SecurityFilterChain securityFilterChain(JwtFilter jwtFilter, HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests()
        .antMatchers("/register", "/login", "/beacon/command", "/beacon/result").permitAll()
        .anyRequest().authenticated();
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
