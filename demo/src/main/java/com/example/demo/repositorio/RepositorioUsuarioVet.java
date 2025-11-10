package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.UsuarioVet;

public interface RepositorioUsuarioVet extends JpaRepository<UsuarioVet, Long> {
    Optional<UsuarioVet> findByUsername(String username);
    Boolean existsByUsername(String username);
}
