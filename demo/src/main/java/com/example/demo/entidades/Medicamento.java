package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
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

    public Medicamento() {}
    public Medicamento(String nombre, int precio_venta, int unidades, int precio_compra, int unidades_vendidas) {
        this.nombre = nombre;
        this.precio_compra = precio_compra;
        this.unidades = unidades;
        this.precio_venta = precio_venta;
        this.unidades_vendidas = unidades_vendidas;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPrecio_compra() {
        return precio_compra;
    }
    public void setPrecio_compra(int precio) {
        this.precio_compra = precio;
    }
    public int getPrecio_venta() {
        return precio_venta;
    }
    public void setPrecio_venta(int precio) {
        this.precio_venta = precio;
    }
    public int getUnidades_vendidas() {
        return unidades_vendidas;
    }
    public void setUnidades_vendidas(int unidades) {
        this.unidades_vendidas = unidades;
    }
    public int getUnidades() {
        return unidades;
    }
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
