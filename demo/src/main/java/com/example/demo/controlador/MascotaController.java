package com.example.demo.controlador;

import java.util.Arrays;

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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MascotaController {

    @Autowired
    private ServicioMascotaImpl servicioMascotas;

    @Autowired
    private ServicioUsuarioImpl servicioUsuarios; // 👈 para buscar al dueño

    // Mostrar una mascota por ID
    @GetMapping("/mascotas/{id}")
    public String verDetalleMascota(HttpServletRequest request,@PathVariable Long id, Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        String role = null;
        if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);

    
            return "mascota-detalle";
        } else {
            return "redirect:/mascotas";
        }
    }

    // Mostrar todas las mascotas
    @GetMapping("/mascotas")
    public String mostrarMascotasTabla(HttpServletRequest request, Model model) {
         String role = null;

    if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
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
    public String agregarMascota(HttpServletRequest request,@PathVariable("usuarioId") Long usuarioId, Model model) {
        Mascota mascota = new Mascota();
        Usuario dueño = servicioUsuarios.getUsuarioById(usuarioId);
        mascota.setDueño(dueño);
        String role = null;

    if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        model.addAttribute("mascota", mascota);
        model.addAttribute("agregar", true);
        return "nuevo_paciente";
    }

    // Guardar mascota nueva
    @PostMapping("/mascotas")
    public String guardarMascota(@ModelAttribute("mascota") Mascota mascota) {
        if (mascota.getDueño() == null) {
            throw new IllegalStateException("No se puede guardar la mascota sin dueño");
        }
         if (mascota.getVacunasTexto() != null && !mascota.getVacunasTexto().isBlank()) {
        mascota.setVacunas(
            Arrays.stream(mascota.getVacunasTexto().split(","))
                  .map(String::trim)
                  .toList()
        );
        }

        if (mascota.getAlergiasTexto() != null && !mascota.getAlergiasTexto().isBlank()) {
        mascota.setAlergias(
            Arrays.stream(mascota.getAlergiasTexto().split(","))
                  .map(String::trim)
                  .toList()
        );
        
    }
    servicioMascotas.addMascota(mascota);
        return "redirect:/usuarios/" + mascota.getDueño().getId(); // ahora usamos objeto Usuario
}

    // Formulario para editar mascota
    @GetMapping({"/mascotas/editar/{id}", "/usuarios/{usuarioId}/mascotas/editar/{id}"})
    public String editarMascota(HttpServletRequest request,@PathVariable("id") Long id,
                                @PathVariable(value = "usuarioId", required = false) Long usuarioId,
                                Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        mascota.setVacunasTexto(String.join(", ", mascota.getVacunas()));
        mascota.setAlergiasTexto(String.join(", ", mascota.getAlergias()));
        String role = null;
        if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        model.addAttribute("mascota", mascota);
        model.addAttribute("usuarioId", usuarioId);
        model.addAttribute("agregar", false);
        return "nuevo_paciente";
    }

        // Editar desde la lista de mascotas
    @PostMapping("/mascotas/editar")
    public String actualizarMascota(@ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.updateMascota(mascota);
        return "redirect:/mascotas";
    }

    // Editar desde un usuario específico
    @PostMapping("/usuarios/{usuarioId}/mascotas/editar")
    public String actualizarMascotaUsuario(@PathVariable Long usuarioId,
                                        @ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.updateMascota(mascota);
        return "redirect:/usuarios/" + usuarioId;
    }

}
