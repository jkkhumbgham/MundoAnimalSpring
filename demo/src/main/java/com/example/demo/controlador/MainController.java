package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.servicio.ServicioMascotas;



@Controller
public class MainController {
    @Autowired
    ServicioMascotas servicio;

    
    
}
