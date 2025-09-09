package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Medicamento;

public interface RepositorioMedicamento extends JpaRepository<Medicamento,Long> {
    
}
