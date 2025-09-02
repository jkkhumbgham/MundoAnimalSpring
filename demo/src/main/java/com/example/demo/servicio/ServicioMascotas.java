package com.example.demo.servicio;

import java.util.Collection;


import com.example.demo.entidades.Mascota;

public interface ServicioMascotas {
    public Collection<Mascota> getAllMascotas();
    public void softdeleteById(Long id);
    public Mascota getMascotaById(Long id);
    public void deleteMascota(Long id);
    public void addMascota(Mascota mascota);
    public void updateMascota(Mascota mascota);
}
