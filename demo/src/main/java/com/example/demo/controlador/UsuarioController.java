package com.example.demo.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidades.Usuario;
import com.example.demo.servicio.ServicioUsuario;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private ServicioUsuario servicioUsuario;


    @GetMapping("")
    public String listarUsuarios(Model model) {
        Collection<Usuario> usuarios = servicioUsuario.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        servicioUsuario.removeUsuario(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/agregar")
    public String agregarUsuario(Model model) {
        Usuario usuario = new Usuario(null, "", "", "", "", null);
        model.addAttribute("usuario", usuario);
        return "agregar_usuario";
    }
    @PostMapping("")
    public String agregarfinal(@ModelAttribute("usuario") Usuario usuario) {
        servicioUsuario.addUsuario(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "agregar_usuario";
    }
    @GetMapping("/{id}")
    public String getUsuario(@PathVariable int id, Model model) {
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        model.addAttribute("usuario", usuario);
        return "usuario_detalle";
    }
    
    
}
