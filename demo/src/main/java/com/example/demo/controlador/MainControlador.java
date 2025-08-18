package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.servicio.ServicioMascotas;

import org.springframework.ui.Model;


@Controller
public class MainControlador {
    @Autowired
    ServicioMascotas servicio;

    //
    @GetMapping("/Mascotas")
    public String MostrarMascotas(Model model){
        model.addAttribute("mascotas", servicio.getAllMascotas());
        return "mascotas";
    }
    
}
