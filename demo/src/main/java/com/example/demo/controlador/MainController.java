package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidades.Usuario;
import com.example.demo.servicio.ServicioUsuario;

import org.springframework.ui.Model;



@Controller
public class MainController {
    @Autowired
    ServicioUsuario servicio;

        @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Usuario usuario=servicio.getByEmail(email);
        if (password.equals(usuario.getContraseña())) {

            return "redirect:/usuarios/"+usuario.getId();
        }else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
}
