package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.repositorio.RepositorioMascotas;
@Service
public class ServicioMascotaImpl implements ServicioMascotas {
    @Autowired
    RepositorioMascotas repositorio;
    

    @Override
    public Collection<Mascota> getAllMascotas() {
        return repositorio.findAll();
    }
    

    @Override
    public Mascota getMascotaById(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public void softdeleteById(Long id){
        repositorio.softDeleteById(id);
    }

    

    @Override
    public void deleteMascota(Long id){
        repositorio.deleteById(id);
    }

    @Override
    public void addMascota(Mascota mascota){
        repositorio.save(mascota);
    }
    
    @Override
    public void updateMascota(Mascota mascota){
        repositorio.save(mascota);
    }
}
