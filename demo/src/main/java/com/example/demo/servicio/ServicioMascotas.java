package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import com.example.demo.entidades.Mascota;

public interface ServicioMascotas {
    public List<Mascota> getAllMascotas();
    public void softdeleteById(Long id);
    public Mascota getMascotaById(Long id);
    public void deleteMascota(Long id);
    public void addMascota(Mascota mascota);
    public void updateMascota(Mascota mascota);
    public List<Mascota> getByDueño_Id(Long id);
}
