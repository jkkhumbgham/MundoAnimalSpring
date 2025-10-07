package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Medicamento;

public interface RepositorioMedicamento extends JpaRepository<Medicamento,Long> {
    List<Medicamento> findAll();
    Medicamento findById(long id);
    List<Medicamento>  findByTratamientos_Id(Long tratamientoId);
}
