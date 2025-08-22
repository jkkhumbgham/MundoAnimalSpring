package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidades.Mascota;

public interface ServicioMascotas {
    public Collection<Mascota> getAllMascotas();
    public Mascota getMascotaById(int id);
}
