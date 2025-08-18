package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainControlador {
    //mostrar un perro
    @GetMapping("/Perro")
    public String Perro(){
        return "index";
    }
    //mostrar varios perros
    @GetMapping("/Perros")
    public String Varios(){
        return "index";
    }
    
}
