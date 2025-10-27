package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entidades.Mascota;

import jakarta.transaction.Transactional;

public interface RepositorioMascotas extends JpaRepository<Mascota, Long> {

    // Encuentra mascotas por el ID del dueño
    List<Mascota> findByDueno_Id(Long duenoId);

    // Cuenta mascotas por estado (ignorando mayúsculas/minúsculas)
    long countByEstadoIgnoreCase(String estado);

    // Actualiza el estado de una mascota por su ID
    @Modifying
    @Transactional
    @Query("UPDATE Mascota m SET m.estado = :estado WHERE m.id = :id")
    void updateEstadoById(@Param("id") Long id, @Param("estado") String estado);
}


