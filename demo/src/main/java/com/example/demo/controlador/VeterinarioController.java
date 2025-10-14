package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entidades.Veterinario;
import java.util.List;

import com.example.demo.servicio.ServicioVeterinario;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {
    @Autowired ServicioVeterinario servicio;

    @GetMapping("")
    public List<Veterinario> listarVeterinarios() {
        return servicio.getAllVeterinarios();
    }
}
