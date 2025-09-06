package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Veterinario;

@Repository
public interface RepositorioVeterinarios extends JpaRepository<Veterinario, Long> {
	Veterinario findByEmail(String email);
}
