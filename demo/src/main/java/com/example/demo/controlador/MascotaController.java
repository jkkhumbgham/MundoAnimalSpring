package com.example.demo.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Mascota;
import com.example.demo.servicio.ServicioMascotaImpl;
import com.example.demo.servicio.ServicioUsuarioImpl;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    private ServicioMascotaImpl servicioMascotas;

    @Autowired
    private ServicioUsuarioImpl servicioUsuarios; 

    // Mostrar una mascota por ID
    @GetMapping("/mascotas/find/{id}")
    public Mascota verDetalleMascota(HttpServletRequest request,@PathVariable Long id) {
        
        return servicioMascotas.getMascotaById(id);
    }

    // Mostrar todas las mascotas
    @GetMapping("/mascotas")
    public List<Mascota> mostrarMascotasTabla(HttpServletRequest request) {
         return servicioMascotas.getAllMascotas();
    
    }


    // Eliminar mascota
    @DeleteMapping({"/mascotas/delete/{id}", "/usuarios/{usuarioId}/mascotas/delete/{id}"})
    public void eliminarMascota(@PathVariable("id") Long id) {
        // Call delete as the tests expect this behavior
        servicioMascotas.deleteMascota(id);
        
    }
    /*
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
         */

    // Guardar mascota nueva
    @PostMapping("/mascotas")
    public void guardarMascota(@RequestBody Mascota mascota) {
        mascota.setId(null);
        if (mascota.getDueno() == null) {
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
    
}
    /* 
    // Formulario para editar mascota
    @GetMapping({"/mascotas/editar/{id}", "/usuarios/{usuarioId}/mascotas/editar/{id}"})
    public String editarMascota(HttpServletRequest request,@PathVariable("id") Long id,
                                @PathVariable(value = "usuarioId", required = false) Long usuarioId, Model model) {
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
         */


         // Editar desde la lista de mascotas
     @PutMapping("/mascotas/editar")
    public void actualizarMascota(@RequestBody Mascota mascota) {

    
    System.out.println("➡ Mascota mapeada: id=" + mascota.getId() + ", foto=" + mascota.getFoto() + ", peso=" + mascota.getPeso());
        servicioMascotas.updateMascota(mascota);
    }
   


    // Editar desde un usuario específico
    @PutMapping("/usuarios/{usuarioId}/mascotas/editar")
    public void actualizarMascotaUsuario(@RequestBody Mascota mascota) {
        servicioMascotas.updateMascota(mascota);
    }

    //Obtener mascotas por dueño
    @GetMapping("/mascotas/dueno/{duenoId}")
    public List<Mascota> getByDueno(@PathVariable Long duenoId) {
        return servicioMascotas.getByDueño_Id(duenoId);
    }
    
    @PatchMapping("/mascotas/{id}/estado")
public ResponseEntity<com.example.demo.entidades.Mascota> patchEstado(
    @PathVariable Long id, @RequestParam String estado) {
    var m = servicioMascotas.getMascotaById(id);
    if (m == null) {
        return ResponseEntity.notFound().build();
    }
  
    servicioMascotas.updateEstadoById(id, estado);
    m.setEstado(estado);
    return ResponseEntity.ok(m);
}

}
