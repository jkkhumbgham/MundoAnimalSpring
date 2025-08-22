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
        return repositorio.getAllMascotas();
    }

    @Override
    public Mascota getMascotaById(int id) {
        return repositorio.getMascotaById(id);
    }
}
