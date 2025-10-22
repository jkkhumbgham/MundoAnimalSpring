package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import com.example.demo.entidades.Mascota;

public interface ServicioMascotas {
    //metodo para obtener todas las mascotas
    public List<Mascota> getAllMascotas();
    //metodo para el soft delete
    public void softdeleteById(Long id);
    //metodo para obtener mascota por id
    public Mascota getMascotaById(Long id);
    //metodo para eliminar mascota
    public void deleteMascota(Long id);
    //metodo para agregar mascota
    public void addMascota(Mascota mascota);
    //metodo para actualizar mascota
    public void updateMascota(Mascota mascota);
    //metodo para obtener mascotas por dueño
    public List<Mascota> getByDueño_Id(Long id);
    //metodo para obtener mascotas tratadas por veterinario
    public List<Mascota> obtenerMascotasTratadasPorVeterinario(Long idVeterinario);
}
