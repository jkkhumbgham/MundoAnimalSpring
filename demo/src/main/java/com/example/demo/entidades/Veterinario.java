package com.example.demo.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos;

    public Veterinario(){

    }
    public Veterinario(String nombre, String email, String password){
        this.nombre=nombre;
        this.email=email;
        this.password=password;
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
    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }
    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
    public String getMail() {
        return email;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
