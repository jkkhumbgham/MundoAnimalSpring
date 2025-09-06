package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.servicio.ServicioUsuario;
import com.example.demo.servicio.ServicioVeterinario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;



@Controller
public class MainController {
    @Autowired
    ServicioUsuario servicio;
    @Autowired 
    ServicioVeterinario serviciovet;

        @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model,HttpServletResponse response) {
        Usuario usuario = null;
        Veterinario veterinario = null;
        if (servicio.getByEmail(email)!=null) {
            usuario=servicio.getByEmail(email);
        }else if (serviciovet.getByEmail(email)!= null) {
             veterinario=serviciovet.getByEmail(email);
        }else{
            model.addAttribute("error", "Ese correo no existe");
            return "login";
        }
        
        
        if (usuario !=null && password.equals(usuario.getContraseña())) {
            Cookie cookie = new Cookie("userRole", "dueno");
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24); // 1 día
            response.addCookie(cookie);
            
            return "redirect:/usuarios/"+usuario.getId();
        }else {
            if (veterinario != null && password.equals(veterinario.getPassword())) {
                Cookie cookie = new Cookie("userRole", "veterinario");
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24); // 1 día
                response.addCookie(cookie);

                return "redirect:/usuarios";
            }else{
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
            }
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
}
