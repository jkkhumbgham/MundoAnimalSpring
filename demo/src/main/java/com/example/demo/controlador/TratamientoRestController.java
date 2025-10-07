package com.example.demo.controlador;

import com.example.demo.entidades.Tratamiento;
import com.example.demo.servicio.ServicioTratamiento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoRestController {

    private final ServicioTratamiento service;
    public TratamientoRestController(ServicioTratamiento service) { this.service = service; }

    // Crear tratamiento para una mascota (IDs en URL/query, cuerpo = Tratamiento)
    @PostMapping("/mascotas/{mascotaId}/tratamientos")
    public ResponseEntity<Tratamiento> crear(
            @PathVariable Long mascotaId,
            @RequestParam Long medicamentoId,
            @RequestParam Long veterinarioId,
            @RequestBody Tratamiento body) {

        Tratamiento saved = service.crear(body, mascotaId, medicamentoId, veterinarioId);
        return ResponseEntity
                .created(URI.create("/api/tratamientos/" + saved.getId()))
                .body(saved);
    }

    // Listar tratamientos de una mascota
    @GetMapping("/mascotas/{mascotaId}/tratamientos")
    public List<Tratamiento> porMascota(@PathVariable Long mascotaId) {
        return service.listarPorMascota(mascotaId);
    }

    // Eliminar tratamiento
    @DeleteMapping("/tratamientos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
