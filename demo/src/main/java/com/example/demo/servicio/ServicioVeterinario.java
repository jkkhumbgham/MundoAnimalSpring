package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import com.example.demo.entidades.Veterinario;

public interface ServicioVeterinario {
    List<Veterinario> getAllVeterinarios();
    Veterinario getVeterinarioById(Long id);
    void addVeterinario(Veterinario veterinario);
    void removeVeterinario(Long id);
    void updateVeterinario(Veterinario veterinario);
    public Veterinario getByEmail(String email);
}
