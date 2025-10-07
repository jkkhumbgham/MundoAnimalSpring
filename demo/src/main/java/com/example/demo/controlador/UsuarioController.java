package com.example.demo.controlador;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Usuario;

import com.example.demo.servicio.ServicioUsuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private ServicioUsuario servicioUsuario;



    @GetMapping("")
    public List<Usuario> listarUsuarios(HttpServletRequest request, Model model) {
        
        
        return servicioUsuario.getAllUsuarios();
    }
    @DeleteMapping("/delete/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        servicioUsuario.removeUsuario(id);
        
    }
    /* 
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
    */
    @PostMapping("/agregar")
    public void agregarfinal(@RequestBody Usuario usuario) {
        usuario.setId(null);
        servicioUsuario.addUsuario(usuario);
    }

    @PutMapping("/editar/{id}")
    public void editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        servicioUsuario.updateUsuario(usuario);
    }
    /* 
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
        */
    @GetMapping("/find/{id}")
    public Usuario getUsuario(HttpServletRequest request, @PathVariable Long id) {
    
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        usuario.getMascotas().size();
        return usuario;
    }
    
    @PostMapping("/mail")
    public Usuario getMethodName(@RequestBody String email) {
        return servicioUsuario.getByEmail(email);
    }
    
    
}
