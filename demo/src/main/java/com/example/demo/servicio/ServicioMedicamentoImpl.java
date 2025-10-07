package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.RepositorioMedicamento;

@Service
public class ServicioMedicamentoImpl implements ServicioMedicamentos {

    @Autowired 
    RepositorioMedicamento repositorio;
    @Override
    public List<Medicamento> getAllMedicamentos() {
        return repositorio.findAll();
    }
    @Override
    public Medicamento getMedicamentoById(Long id) {
       return repositorio.findById(id).get();
    }   
    @Override
    public void addMedicamento(Medicamento medicamento) {
       repositorio.save(medicamento);
    }
    @Override
    public void removeMedicamento(Long id) {
       repositorio.deleteById(id);
    }
    @Override
    public void updateMedicamento(Medicamento medicamento) {
       repositorio.save(medicamento);
    }
    @Override
    public List<Medicamento>  findByTratamientos_Id(Long tratamientoId) {
        return repositorio.findByTratamientos_Id(tratamientoId);
    }
}
