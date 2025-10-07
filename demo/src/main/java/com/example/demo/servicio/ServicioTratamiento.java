package com.example.demo.servicio;

import com.example.demo.entidades.Tratamiento;
import java.util.List;

public interface ServicioTratamiento {
    Tratamiento crear(Tratamiento t, Long mascotaId, Long medicamentoId, Long veterinarioId);
    List<Tratamiento> listarPorMascota(Long mascotaId);
    void eliminar(Long id);
}
