package com.example.demo.DTOs;

import lombok.Data;

@Data
public class MascotaTablaDto {
    private Long id;
    private String foto;
    private String nombre;
    private String especie;
    private String raza;
    private String peso;
    private String estado;
}

