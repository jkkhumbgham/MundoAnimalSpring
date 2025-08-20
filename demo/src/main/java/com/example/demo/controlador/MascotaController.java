package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entidades.mascota;
import com.example.demo.servicio.ServicioMascotaImpl;


@Controller
public class MascotaController {
    @Autowired
    ServicioMascotaImpl servicioMascotas;
    //mostrar un perro
    @GetMapping("/Mascota/{id}")
public String verDetalleMascota(@PathVariable int id, Model model) {
    mascota mascota = servicioMascotas.getMascotaById(id);
    if(mascota!= null){
        model.addAttribute("mascota", mascota);
        return "mascota-detalle"; // nombre del template
    }else return "La mascota no existe";
}
//http://localhost:8080/Mascotas
/*@GetMapping("/Mascotas")
    public String MostrarMascotas(Model model){
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas";
    } */

    //http://localhost:8080/Mascotas-Tabla
    @GetMapping("/Mascota")
    public String MostrarMascotasTabla(Model model){
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas-tabla";
    }
            
}
    
