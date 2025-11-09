package com.example.demo.DTOs;

import lombok.Data;

@Data
public class UsuarioTablaDto {
    private Long id;
    private String foto;
    private String nombre;
    private String telefono;
    private String email;
}
