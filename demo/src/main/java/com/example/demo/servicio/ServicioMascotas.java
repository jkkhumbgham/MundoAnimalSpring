package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import com.example.demo.entidades.Mascota;

public interface ServicioMascotas {
    public Collection<Mascota> getAllMascotas();
    public Mascota getMascotaById(int id);
    public List<Mascota> getMascotasByUsuario(int usuario);
}
