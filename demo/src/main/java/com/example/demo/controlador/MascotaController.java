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
    //mostrar una mascota
    //CRUD TABLA GLOBAL
    @GetMapping("/mascotas/{id}")
public String verDetalleMascota(@PathVariable int id, Model model) {
    Mascota mascota = servicioMascotas.getMascotaById(id);
    if(mascota!= null){
        model.addAttribute("mascota", mascota);
        return "mascota-detalle"; // nombre del template
    }else {
        return "redirect:/mascotas";
    }
}
   

    //http://localhost:8080/mascotas
    @GetMapping("/mascotas")
    public String MostrarMascotasTabla(Model model){
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas-tabla";
    }

    @GetMapping({"/mascotas/delete/{id}","/usuarios/{Usuarioid}/mascotas/delete/{id}"})
    public String eliminarMascota(@PathVariable(value = "id") Integer id, @PathVariable(value = "Usuarioid", required = false) Integer Usuarioid){
        servicioMascotas.deleteMascota(id);
        if (Usuarioid != null) {
            return "redirect:/usuarios/" + Usuarioid;
        }else {
            return "redirect:/mascotas";
        }

    }

    @GetMapping({"/mascotas/agregar","/usuarios/{Usuarioid}/mascotas/agregar"})
    public String agregarMascota(@PathVariable(value = "Usuarioid", required = false) Integer Usuarioid,Model model) {
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        mascota.setDueñoid(Usuarioid);
        return "nuevo_paciente";
    }
    @PostMapping({"/mascotas","/usuarios/{Usuarioid}"})
    public String agregarfinal(@PathVariable(value = "Usuarioid", required = false) Integer Usuarioid, @ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.addMascota(mascota);

        if (Usuarioid != null) {
            return "redirect:/usuarios/" + Usuarioid;
        }else {
            return "redirect:/mascotas";
        }
    }

    @GetMapping({"/mascotas/editar/{id}", "/usuarios/{Usuarioid}/mascotas/editar/{id}"})
    public String editarMascota(@PathVariable int id,@PathVariable(value = "Usuarioid", required = false) Integer Usuarioid, Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        model.addAttribute("mascota", mascota);
        mascota.setDueñoid(Usuarioid);
        return "nuevo_paciente";
    }

    @PostMapping({"/mascotas/editar","/usuarios/{Usuarioid}/mascotas/editar"})
    public String actualizarMascota(@PathVariable(value = "Usuarioid", required = false) Integer Usuarioid,@ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.addMascota(mascota);
        if (Usuarioid != null) {
            return "redirect:/usuarios/" + Usuarioid;
        }else {
            return "redirect:/mascotas";
        }
    }

}