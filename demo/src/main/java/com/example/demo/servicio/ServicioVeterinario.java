package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Veterinario;

public interface ServicioVeterinario {
    //metodo para buscar todos los veterinarios
    List<Veterinario> getAllVeterinarios();
    //metodo para buscar veterinario por id
    Veterinario getVeterinarioById(Long id);
    //metodo para agregar veterinario
    void addVeterinario(Veterinario veterinario);
    //metodo para eliminar veterinario
    void removeVeterinario(Long id);
    //metodo para el soft delete
    public void softdeleteById(Long id);
    //metodo para actualizar veterinario
    void updateVeterinario(Veterinario veterinario);
    //metodo para buscar veterinario por email
    public Veterinario getByEmail(String email);
    //metodo para obtener mascotas tratadas por veterinario
    List<Mascota> obtenerMascotasTratadasPorVeterinario(Long idVeterinario);
}
