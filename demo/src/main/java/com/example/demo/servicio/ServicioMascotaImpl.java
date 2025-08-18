package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.mascota;
import com.example.demo.repositorio.RepositorioMascotas;
@Service
public class ServicioMascotaImpl implements ServicioMascotas {
    @Autowired
    RepositorioMascotas repositorio;

    @Override
    public Collection<mascota> getAllMascotas() {
        return repositorio.getAllMascotas();
    }

    @Override
    public mascota getMascotaById(int id) {
        return repositorio.getMascotaById(id);
    }
}
