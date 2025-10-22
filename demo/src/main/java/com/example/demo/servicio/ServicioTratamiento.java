package com.example.demo.servicio;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;

import java.util.List;

public interface ServicioTratamiento {
    //metodo para crear tratamiento
    Tratamiento crear(Tratamiento t, Long mascotaId, Long medicamentoId, Long veterinarioId);
    //metodo para listar tratamientos por mascota
    List<Tratamiento> listarPorMascota(Long mascotaId);
    //metodo para eliminar tratamiento
    void eliminar(Long id);
    //metodo para obtener veterinario por tratamiento
    Veterinario obtenerVeterinarioPorTratamiento(Long tratamientoId);
}
