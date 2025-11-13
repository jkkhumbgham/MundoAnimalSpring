package com.example.demo.controlador;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.DTOs.UsuarioTablaDto;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioVet;
import com.example.demo.repositorio.RepositorioUsuarioVet;
import com.example.demo.security.CustomUserDetilService;
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

    @Autowired
    private RepositorioUsuarioVet repositorioUsuarios;

    @Autowired
    private CustomUserDetilService customUserDetilService;

    // Listar usuarios
    @GetMapping("")
    public List<UsuarioTablaDto> listarUsuarios(HttpServletRequest request, Model model) {
        
        
        return servicioUsuario.getAllUsuarios();
    }
    // Eliminar usuario
    @DeleteMapping("/delete/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        servicioUsuario.removeUsuario(id);
        
    }
    

    // Agregar usuario
    @PostMapping("/agregar")
    public ResponseEntity agregarfinal(@RequestBody Usuario usuario) {
        usuario.setId(null);
        if (repositorioUsuarios.existsByUsername(usuario.getEmail())) {
            return new ResponseEntity<String>("El usuario ya existe",HttpStatus.BAD_REQUEST);
        }
        UsuarioVet user = customUserDetilService.mapUsuario(usuario);
        repositorioUsuarios.save(user);
        usuario.setUsuarioVet(user);
        try{
        servicioUsuario.addUsuario(usuario);
        }catch(Exception e){
            return new ResponseEntity<Usuario>(usuario,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);

    }

    // Editar usuario
    @PutMapping("/editar/{id}")
    public void editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        servicioUsuario.updateUsuario(usuario);
    }
    

    // Obtener usuario por id
    @GetMapping("/find/{id}")
    public Usuario getUsuario(HttpServletRequest request, @PathVariable Long id) {
    
        Usuario usuario = servicioUsuario.getUsuarioById(id);
        usuario.getMascotas().size();
        return usuario;
    }
    
    // Obtener usuario por email
    @PostMapping("/mail")
    public Usuario getByEmail(@RequestBody String email) {
        return servicioUsuario.getByEmail(email);
    }
    
    
}
