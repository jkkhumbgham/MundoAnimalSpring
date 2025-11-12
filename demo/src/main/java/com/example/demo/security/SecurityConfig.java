package com.example.demo.security;

// Removed Lombok constructor annotation to avoid IDE/editor errors when Lombok processing is not enabled
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthFilter jwtFilter;
  private final CustomUserDetailsService users;

  // Explicit constructor so the class doesn't rely on Lombok-generated constructors.
  public SecurityConfig(JwtAuthFilter jwtFilter, CustomUserDetailsService users) {
    this.jwtFilter = jwtFilter;
    this.users = users;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
       .headers(h -> h.frameOptions(f -> f.disable()))
       .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .userDetailsService(users)
       .authorizeHttpRequests(auth -> auth
         .requestMatchers("/auth/**", "/h2/**").permitAll()
         // Endpoints funcionales para todos los roles autenticados
         .requestMatchers("/mascotas", "/mascotas/**", "/medicamentos", "/medicamentos/**", "/tratamientos", "/tratamientos/**")
           .hasAnyAuthority("usuario","veterinario","admin")
         // Solo veterinario o admin
         .requestMatchers("/veterinarios", "/veterinarios/**", "/admin", "/admin/**")
           .hasAnyAuthority("veterinario","admin")
         // Permitir endpoints de usuarios (compatibilidad con flujo actual del frontend)
         .requestMatchers("/usuarios/**").permitAll()
         .anyRequest().authenticated()
       )
       .exceptionHandling(e -> e.authenticationEntryPoint((req,res,ex)->{
         res.setStatus(401); 
         res.getWriter().write("Unauthorized");
       }))
       .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
