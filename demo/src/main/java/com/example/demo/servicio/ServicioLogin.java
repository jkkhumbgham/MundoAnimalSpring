package com.example.demo.servicio;

import com.example.demo.entidades.DueñoMascota;
import com.example.demo.repositorio.DueñoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioLogin {

    @Autowired
    private DueñoMascotaRepository dueñoMascotaRepository;

    public DueñoMascota autenticar(String email, String password) {
        Optional<DueñoMascota> optionalDueno = dueñoMascotaRepository.findByEmail(email);
        if (optionalDueno.isPresent()) {
            DueñoMascota dueno = optionalDueno.get();
            if (dueno.getPassword().equals(password)) {
                return dueno;
            }
        }
        return null;
    }
}