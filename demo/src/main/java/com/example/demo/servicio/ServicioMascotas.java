package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidades.mascota;

public interface ServicioMascotas {
    public Collection<mascota> getAllMascotas();
    public mascota getMascotaById(int id);
}
