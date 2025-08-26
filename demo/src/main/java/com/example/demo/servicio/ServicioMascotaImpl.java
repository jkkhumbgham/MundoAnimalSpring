package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

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
        return repositorio.getAllMascotas();
    }

    @Override
    public Mascota getMascotaById(int id) {
        return repositorio.getMascotaById(id);
    }

    @Override
    public List<Mascota> getMascotasByUsuario(int usuario) {
        return RepositorioMascotas.getMascotasByUsuario(usuario);
    }

    @Override
    public void deleteMascota(Integer id){
        repositorio.deleteMascota(id);
    }

    @Override
    public void addMascota(Mascota mascota){
        repositorio.addMascota(mascota);
    }
    
    @Override
    public void updateMascota(Mascota mascota){
        repositorio.addMascota(mascota);
    }
}
