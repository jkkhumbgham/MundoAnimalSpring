package com.example.demo.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class mascota {
    private int id;
    private List<String> vacunas;
    private List<String> alergias;
    private List<String> medicamentos;
    private String observaciones;
    private String foto;
    private String dueño;
    private String nombre;
    private String especie;//de tipo Especie pero no tenemos que conectar clases
    private String raza;
    private String sexo;
    private String estado;
    private Date ultimavisita;
    private Date fechaNacimiento;
    private float peso;
    private int microchipID;
    


    public int getEdad() {
    if (this.fechaNacimiento == null) {
        return 0;
    }
    LocalDate fecha = this.fechaNacimiento.toLocalDate();
    return Period.between(fecha, LocalDate.now()).getYears();
}



}
