package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.ServicioUsuario;
import com.example.demo.servicio.ServicioVeterinario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
    @Autowired
    ServicioUsuario servicio;
    @Autowired 
    ServicioVeterinario serviciovet;


    //Metodo post de login para iniciar sesion devuelve tipo de usuario y su id
        @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model,HttpServletResponse response) {
        Usuario usuario = null;
        Veterinario veterinario = null;
        if (servicio.getByEmail(email)!=null) {
            usuario=servicio.getByEmail(email);
        }else if (serviciovet.getByEmail(email)!= null) {
             veterinario=serviciovet.getByEmail(email);
        }else{
            
            return "fallo";
        }
        
        
        if (usuario !=null && password.equals(usuario.getpassword())) {
            if (usuario.getEmail().equals("admin@example.com")) {
                return "admin," + usuario.getId();
            }
            else{
                return "cliente," + usuario.getId();
            }
        }else {
            if (veterinario != null && password.equals(veterinario.getPassword())) {
                if (veterinario.getEstado().equals("inactivo")) {
                    model.addAttribute("error", "El veterinario se encuentra inactivo");
                    return "fallo";
                }else{
                    return "veterinario," + veterinario.getId();
                }
            }else{
            model.addAttribute("error", "Usuario o password incorrectos");
            return "fallo";
            }
        }
    }


    
}
