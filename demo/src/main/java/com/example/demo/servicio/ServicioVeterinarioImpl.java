package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioVeterinarios;

@Service
public class ServicioVeterinarioImpl implements ServicioVeterinario {
    
    @Autowired
    RepositorioVeterinarios repositorio;

    @Override
    public List<Veterinario> getAllVeterinarios() {
        return repositorio.findAll();
    }

    @Override
    public Veterinario getVeterinarioById(Long id) {
        return repositorio.findById(id).get();
    }
    @Override
    public void addVeterinario(Veterinario veterinario) {
        repositorio.save(veterinario);
    }

    @Override
    public void removeVeterinario(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public void softdeleteById(Long id) {
    Veterinario veterinario = repositorio.findById(id).orElse(null);
    if (veterinario != null) {
        if ("inactivo".equalsIgnoreCase(veterinario.getEstado())) {
            // Restaurar el estado original
            repositorio.updateEstadoById(id, veterinario.getEstadoOriginal());
        } else {
            // Guardar el estado actual antes de poner inactivo
            veterinario.setEstadoOriginal(veterinario.getEstado());
            repositorio.save(veterinario);
            repositorio.updateEstadoById(id, "inactivo");
        }
    }
}

    @Override
    public void updateVeterinario(Veterinario Veterinario) {
        repositorio.save(Veterinario);
    }
    
    @Override
    public Veterinario getByEmail(String email) {
        return repositorio.findByEmail(email);
    }
}
