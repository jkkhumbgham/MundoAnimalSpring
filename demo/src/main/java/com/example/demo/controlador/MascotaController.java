package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidades.Mascota;
import com.example.demo.servicio.ServicioMascotaImpl;

@Controller
public class MascotaController {

    @Autowired
    ServicioMascotaImpl servicioMascotas;

    // Mostrar una mascota por ID
    @GetMapping("/mascotas/{id}")
    public String verDetalleMascota(@PathVariable int id, Model model) {
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
    @GetMapping("/usuarios/{Usuarioid}/mascotas")
    public String listarMascotasUsuario(@PathVariable("Usuarioid") Integer Usuarioid, Model model) {
        model.addAttribute("usuarioId", Usuarioid);
        model.addAttribute("mascotas", servicioMascotas.getMascotasByUsuario(Usuarioid));
        return "mascotas-tabla";
    }

    // Eliminar mascota
    @GetMapping({"/mascotas/delete/{id}", "/usuarios/{Usuarioid}/mascotas/delete/{id}"})
    public String eliminarMascota(@PathVariable("id") Integer id,
                                  @PathVariable(value = "Usuarioid", required = false) Integer Usuarioid) {
        servicioMascotas.deleteMascota(id);
        if (Usuarioid != null) {
            return "redirect:/usuarios/" + Usuarioid;
        } else {
            return "redirect:/mascotas";
        }
    }

    // Formulario para agregar mascota
    @GetMapping("/usuarios/{Usuarioid}/mascotas/agregar")
    public String agregarMascota(@PathVariable("Usuarioid") Integer Usuarioid, Model model) {
        Mascota mascota = new Mascota();
        mascota.setDueñoid(Usuarioid);
        model.addAttribute("mascota", mascota);
        return "nuevo_paciente";
    }

    // Guardar mascota nueva
    @PostMapping("/mascotas")
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota) {
        if (mascota.getDueñoid() == null) {
            throw new IllegalStateException("No se puede guardar la mascota sin dueño");
        }
        servicioMascotas.addMascota(mascota);
        return "redirect:/usuarios/" + mascota.getDueñoid();
    }

    // Formulario para editar mascota
    @GetMapping({"/mascotas/editar/{id}", "/usuarios/{Usuarioid}/mascotas/editar/{id}"})
    public String editarMascota(@PathVariable("id") int id,
                                @PathVariable(value = "Usuarioid", required = false) Integer Usuarioid,
                                Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        model.addAttribute("mascota", mascota);
        return "nuevo_paciente";
    }

    // Actualizar mascota existente
    @PostMapping({"/mascotas/editar", "/usuarios/{Usuarioid}/mascotas/editar"})
    public String actualizarMascota(@PathVariable(value = "Usuarioid", required = false) Integer Usuarioid,
                                    @ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.updateMascota(mascota); // <-- actualizar en vez de agregar
        if (Usuarioid != null) {
            return "redirect:/usuarios/" + Usuarioid;
        } else {
            return "redirect:/mascotas";
        }
    }

}
