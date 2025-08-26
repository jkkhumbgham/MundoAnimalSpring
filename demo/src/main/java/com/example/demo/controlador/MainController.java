package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.servicio.ServicioMascotas;

import org.springframework.ui.Model;



@Controller
public class MainController {
    @Autowired
    ServicioMascotas servicio;

        @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if ("usuario".equals(username) && "contraseña".equals(password)) {
            return "privado";
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/privado")
    public String privado() {
        return "privado"; 
    }
    
}
