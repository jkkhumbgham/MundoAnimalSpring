package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Medicamento;

public interface ServicioMedicamentos {
    //metodo para buscar todos los medicamentos
    List<Medicamento> getAllMedicamentos();
    //metodo para buscar medicamento por id
    Medicamento getMedicamentoById(Long id);
    //metodo para agregar medicamento
    void addMedicamento(Medicamento medicamento);
    //metodo para eliminar medicamento
    void removeMedicamento(Long id);
    //metodo para actualizar medicamento
    void updateMedicamento(Medicamento medicamento);
    //metodo para buscar medicamentos por tratamiento
    List<Medicamento>  findByTratamientos_Id(Long tratamientoId);
}
