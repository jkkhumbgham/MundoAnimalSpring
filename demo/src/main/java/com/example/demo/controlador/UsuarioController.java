package com.example.demo.controlador;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidades.Usuario;

import com.example.demo.servicio.ServicioUsuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private ServicioUsuario servicioUsuario;



    @GetMapping("")
    public String listarUsuarios(HttpServletRequest request, Model model) {
        String role = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("userRole".equals(cookie.getName())) {
                    role = cookie.getValue();
                    break;
                }
            }
        }
        Collection<Usuario> usuarios = servicioUsuario.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("rol", role);
        return "usuarios";
    }
    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        servicioUsuario.removeUsuario(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/agregar")
    public String agregarUsuario(HttpServletRequest request, Model model) {
        Usuario usuario = new Usuario(null, "","", "", "", "", null);
        String role = null;

    if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        model.addAttribute("usuario", usuario);
        return "agregar_usuario";
    }
    @PostMapping("")
    public String agregarfinal(@ModelAttribute("usuario") Usuario usuario) {
        servicioUsuario.addUsuario(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(HttpServletRequest request, @PathVariable Long id, Model model) {
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        String role = null;

    if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if ("userRole".equals(cookie.getName())) {
                role = cookie.getValue();
                break;
            }
        }
    }
        model.addAttribute("rol", role);
        model.addAttribute("usuario", usuario);
        return "agregar_usuario";
    }
    @GetMapping("/{id}")
    public String getUsuario(HttpServletRequest request, @PathVariable Long id, Model model) {
        String role = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("userRole".equals(cookie.getName())) {
                    role = cookie.getValue();
                    break;
                }
            }
        }
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarioId", id);
        model.addAttribute("rol", role);
        return "usuario_detalle";
    }
    
    
}
