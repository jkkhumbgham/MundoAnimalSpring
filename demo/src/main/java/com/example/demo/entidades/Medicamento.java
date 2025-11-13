package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private int precio_venta;
    private int unidades;
    private int precio_compra;
    private int unidades_vendidas;
    @JsonIgnore
    @ManyToMany(mappedBy = "medicamentos")
    private List<Tratamiento> tratamientos = new ArrayList<>();

    public Medicamento(String nombre, int precio_venta, int unidades, int precio_compra, int unidades_vendidas) {
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.unidades = unidades;
        this.precio_venta = precio_venta;
        this.unidades_vendidas = unidades_vendidas;
    }
    
}
