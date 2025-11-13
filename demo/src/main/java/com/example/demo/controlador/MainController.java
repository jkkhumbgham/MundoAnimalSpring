package com.example.demo.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.Veterinario;
import com.example.demo.security.CustomUserDetilService;
import com.example.demo.security.JwtGenerator;
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

    @Autowired
    private CustomUserDetilService detail;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtGenerator jwtGenerator;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    //Metodo post de login para iniciar sesion devuelve tipo de usuario y su id
        @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, Model model,HttpServletResponse response) {
        try{
        logger.info("Iniciando sesion");
        Usuario usuario = null;
        Veterinario veterinario = null;
        logger.info("Authenticando: Email: "+email+" Password: "+password);
        Authentication auth = manager.authenticate(
            
            new UsernamePasswordAuthenticationToken(email , password)
        );
        logger.info("Authenticado");
        SecurityContextHolder.getContext().setAuthentication(auth);
        String Token = jwtGenerator.generateToken(auth);
        logger.info("Token Generado");
        System.out.println("Token: "+Token);
        if (servicio.getByEmail(email)!=null) {
            usuario = servicio.getByEmail(email);
            if (email.equals("admin@example.com")) {
                return Token+",admin"+","+usuario.getId();
            }
            
            return Token+",cliente"+","+usuario.getId();
        }else if (serviciovet.getByEmail(email)!=null) {
            veterinario = serviciovet.getByEmail(email);
            if (veterinario.getEstado().equals("inactivo")) {
                return "inactivo";
            }
            
            return Token+",veterinario"+","+veterinario.getId();
        }
        
        return "fallo";}
        catch(Exception e){
            logger.info( "Error: "+e.getMessage());
            return "fallo Exception";
        }
        
        
    }


    
}
