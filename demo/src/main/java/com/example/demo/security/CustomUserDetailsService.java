package com.example.demo.security;

import com.example.demo.entidades.UsuarioVet;
import com.example.demo.repositorio.RepositorioUsuarioVet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final RepositorioUsuarioVet usuarioRepo;

  // Explicit constructor to avoid relying on Lombok
  public CustomUserDetailsService(RepositorioUsuarioVet usuarioRepo) {
    this.usuarioRepo = usuarioRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsuarioVet u = usuarioRepo.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    java.util.List<GrantedAuthority> auths = u.getRoles().stream()
      .map(r -> new SimpleGrantedAuthority(r.getName()))
      .map(GrantedAuthority.class::cast)
      .toList();
    return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), auths);
  }
}
