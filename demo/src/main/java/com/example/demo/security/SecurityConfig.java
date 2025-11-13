package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.controlador.MainController;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


      private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      logger.info("Configurando seguridad");
        http.
            csrf(AbstractHttpConfigurer::disable)
            .headers(headers-> headers.frameOptions(frame->frame.disable()))
            .authorizeRequests(
                requests -> requests
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/usuarios/mail/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/usuarios/**").hasAnyAuthority("usuario","veterinario","admin")
                .requestMatchers("/usuarios","/usuarios/**", "/usuarios/editar/**","/mascotas","/mascotas/**","/veterinarios/mascotas/**").hasAnyAuthority("veterinario","admin")
                .requestMatchers("/usuarios","/usuarios/**", "/usuarios/editar/**","/mascotas","/mascotas/**","/veterinarios/mascotas/**","/veterinarios", "/veterinarios/**","/admin/dashboard/**").hasAuthority("admin")
                .anyRequest().permitAll()
                
            )
            .exceptionHandling(exception-> exception.authenticationEntryPoint(jwtAuthEntryPoint));
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration
      ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
      return new JwtAuthenticationFilter();
    }
}
