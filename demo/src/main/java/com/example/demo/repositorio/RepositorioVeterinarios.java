package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Veterinario;

import jakarta.transaction.Transactional;

@Repository
public interface RepositorioVeterinarios extends JpaRepository<Veterinario, Long> {
    //metodo para buscar veterinario por email
	Veterinario findByEmail(String email);
    //metodo para el soft delete
	@Modifying
    @Transactional
    @Query("UPDATE Veterinario v SET v.estado =:estado WHERE v.id = :id")
    void updateEstadoById(@Param("id") Long id, @Param("estado") String estado);
}
