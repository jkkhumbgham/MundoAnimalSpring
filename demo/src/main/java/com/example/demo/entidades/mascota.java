package com.example.demo.entidades;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class mascota {
    private int id;
    private String foto;
    private String nombre;
    private String especie;//de tipo Especie pero no tenemos que conectar clases
    private String raza;
    private String sexo;
    private Date fechaNacimiento;
    private float peso;
    private int microchipID;
    
}
