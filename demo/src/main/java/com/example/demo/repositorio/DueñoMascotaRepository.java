package com.example.demo.repositorio;

import com.example.demo.entidades.DueñoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DueñoMascotaRepository extends JpaRepository<DueñoMascota, Long> {
    Optional<DueñoMascota> findByEmail(String email);
}