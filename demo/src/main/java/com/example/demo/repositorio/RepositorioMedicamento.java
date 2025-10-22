package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Medicamento;

public interface RepositorioMedicamento extends JpaRepository<Medicamento,Long> {
    //metodo para buscar todos los medicamentos
    List<Medicamento> findAll();
    //metodo para buscar medicamento por id
    Medicamento findById(long id);
    //metodo para buscar medicamentos por tratamiento
    List<Medicamento>  findByTratamientos_Id(Long tratamientoId);
}
