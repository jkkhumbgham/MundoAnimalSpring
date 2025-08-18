package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControladorPerros {
    //mostrar un perro
    @GetMapping("/Perro")
    public String Perro(){
        return "index";
    }
    
}