package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Medicamento;

public interface ServicioMedicamentos {
    List<Medicamento> getAllMedicamentos();
    Medicamento getMedicamentoById(Long id);
    void addMedicamento(Medicamento medicamento);
    void removeMedicamento(Long id);
    void updateMedicamento(Medicamento medicamento);
    List<Medicamento>  findByTratamientos_Id(Long tratamientoId);
}
