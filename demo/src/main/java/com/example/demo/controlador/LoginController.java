package com.example.demo.controlador;

import com.example.demo.entidades.DueñoMascota;
import com.example.demo.servicio.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private ServicioLogin servicioLogin;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                                @RequestParam String password,
                                Model model,
                                HttpSession session) {
        DueñoMascota dueno = servicioLogin.autenticar(email, password);
        if (dueno != null) {
            session.setAttribute("usuarioLogueado", dueno);
            model.addAttribute("dueno", dueno);
            return "redirect:/"; // Redirige al inicio
        } else {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}