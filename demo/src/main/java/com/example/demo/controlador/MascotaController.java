package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.servicio.ServicioMascotaImpl;
import com.example.demo.servicio.ServicioUsuarioImpl;

@Controller
public class MascotaController {

    @Autowired
    private ServicioMascotaImpl servicioMascotas;

    @Autowired
    private ServicioUsuarioImpl servicioUsuarios; // 👈 para buscar al dueño

    // Mostrar una mascota por ID
    @GetMapping("/mascotas/{id}")
    public String verDetalleMascota(@PathVariable Long id, Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascota-detalle";
        } else {
            return "redirect:/mascotas";
        }
    }

    // Mostrar todas las mascotas
    @GetMapping("/mascotas")
    public String mostrarMascotasTabla(Model model) {
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas-tabla";
    }

    // Listar mascotas de un usuario específico
    @GetMapping("/usuarios/{usuarioId}/mascotas")
    public String listarMascotasUsuario(@PathVariable("usuarioId") Long usuarioId, Model model) {
        model.addAttribute("usuarioId", usuarioId);
        return "mascotas-tabla";
    }

    // Eliminar mascota
    @GetMapping({"/mascotas/delete/{id}", "/usuarios/{usuarioId}/mascotas/delete/{id}"})
    public String eliminarMascota(@PathVariable("id") Long id,
                                  @PathVariable(value = "usuarioId", required = false) Long usuarioId) {
        servicioMascotas.softdeleteById(id);
        if (usuarioId != null) {
            return "redirect:/usuarios/" + usuarioId;
        } else {
            return "redirect:/mascotas";
        }
    }

    // Formulario para agregar mascota
    @GetMapping("/usuarios/{usuarioId}/mascotas/agregar")
    public String agregarMascota(@PathVariable("usuarioId") Long usuarioId, Model model) {
        Mascota mascota = new Mascota();
        Usuario dueño = servicioUsuarios.getUsuarioById(usuarioId); // 👈 buscar el dueño
        mascota.setDueño(dueño);
        model.addAttribute("mascota", mascota);
        return "nuevo_paciente";
    }

    // Guardar mascota nueva
    @PostMapping("/mascotas")
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota) {
        if (mascota.getDueño() == null) {
            throw new IllegalStateException("No se puede guardar la mascota sin dueño");
        }
        servicioMascotas.addMascota(mascota);
        return "redirect:/usuarios/" + mascota.getDueño().getId(); // 👈 ahora usamos objeto Usuario
    }

    // Formulario para editar mascota
    @GetMapping({"/mascotas/editar/{id}", "/usuarios/{usuarioId}/mascotas/editar/{id}"})
    public String editarMascota(@PathVariable("id") Long id,
                                @PathVariable(value = "usuarioId", required = false) Long usuarioId,
                                Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        model.addAttribute("mascota", mascota);
        return "nuevo_paciente";
    }

    // Actualizar mascota existente
    @PostMapping({"/mascotas/editar", "/usuarios/{usuarioId}/mascotas/editar"})
    public String actualizarMascota(@PathVariable(value = "usuarioId", required = false) Long usuarioId,
                                    @ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.updateMascota(mascota);
        if (usuarioId != null) {
            return "redirect:/usuarios/" + usuarioId;
        } else {
            return "redirect:/mascotas";
        }
    }

}
