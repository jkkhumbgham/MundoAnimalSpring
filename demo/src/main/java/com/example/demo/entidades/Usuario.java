package com.example.demo.entidades;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Usuario {
    private Integer id;
    private String nombre;
    private String telefono;
    private String email;
    private String contraseña;
    private String foto;
    private List<Mascota> mascotas;

}
