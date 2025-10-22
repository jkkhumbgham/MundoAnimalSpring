package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Medicamento;
import com.example.demo.servicio.ServicioMedicamentoImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    ServicioMedicamentoImpl servicio;

    //obtener todos los medicamentos
    @GetMapping("")
    public List<Medicamento> getMedicamentos() {
        return servicio.getAllMedicamentos();
    }

    //obtener medicamento por id
    @GetMapping("/{id}")
    public Medicamento getMedicamentoById(@PathVariable("id") Long id) {
        return servicio.getMedicamentoById(id);
    }

    //obtener medicamentos por tratamiento
    @GetMapping("/tratamiento/{id}")
    public List<Medicamento> getByTratamiento(@PathVariable("id") Long id) {
        return servicio.findByTratamientos_Id(id);
    }
    
    
    
}
