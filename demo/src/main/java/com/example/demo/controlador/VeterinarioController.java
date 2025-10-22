package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.ServicioVeterinario;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {
    @Autowired ServicioVeterinario servicio;

    // Listar veterinarios
    @GetMapping("")
    public List<Veterinario> listarVeterinarios() {
        return servicio.getAllVeterinarios();
    }
    // Eliminar veterinario
    @DeleteMapping("/delete/{id}")
    public void eliminarVeterinario(@PathVariable Long id) {
        servicio.removeVeterinario(id);
    }
    // Agregar veterinario
    @PostMapping("/agregar")
    public void agregarVeterinario(@RequestBody Veterinario veterinario) {
        veterinario.setId(null);
        servicio.addVeterinario(veterinario);
    }

    // Editar veterinario

    @PutMapping("/editar/{id}")
    public void editarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        servicio.updateVeterinario(veterinario);
    }

    // Obtener veterinario por id
    @GetMapping("/find/{id}")
    public Veterinario getVeterinario(HttpServletRequest request, @PathVariable Long id) {

        Veterinario veterinario = servicio.getVeterinarioById(id);
        return veterinario;
    }

    // Soft eliminar veterinario desactivar un veterinario
    @DeleteMapping("/softdelete/{id}")
    public void softEliminarVeterinario(@PathVariable Long id) {
        servicio.softdeleteById(id);
    }


    // Obtener mascotas tratadas por veterinario
    @GetMapping("/{id}/mascotas-tratadas")
    public List<Mascota> getMascotasTratadas(@PathVariable Long id) {
        return servicio.obtenerMascotasTratadasPorVeterinario(id);
    }


}
