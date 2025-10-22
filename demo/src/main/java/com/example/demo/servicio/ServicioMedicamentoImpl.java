package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.RepositorioMedicamento;

@Service
public class ServicioMedicamentoImpl implements ServicioMedicamentos {
   // declaracion repos necesarios
    @Autowired 
    RepositorioMedicamento repositorio;

    //definicion de metodos

    //metodo para buscar todos los medicamentos
    @Override
    public List<Medicamento> getAllMedicamentos() {
        return repositorio.findAll();
    }
//metodo para buscar medicamento por id
    @Override
    public Medicamento getMedicamentoById(Long id) {
       return repositorio.findById(id).get();
    }   
    //metodo para agregar medicamento
    @Override
    public void addMedicamento(Medicamento medicamento) {
       repositorio.save(medicamento);
    }
    //metodo para eliminar medicamento
    @Override
    public void removeMedicamento(Long id) {
       repositorio.deleteById(id);
    }
    //metodo para actualizar medicamento
    @Override
    public void updateMedicamento(Medicamento medicamento) {
       repositorio.save(medicamento);
    }
    //metodo para buscar medicamentos por tratamiento
    @Override
    public List<Medicamento>  findByTratamientos_Id(Long tratamientoId) {
        return repositorio.findByTratamientos_Id(tratamientoId);
    }
}
