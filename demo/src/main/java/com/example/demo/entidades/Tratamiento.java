package com.example.demo.entidades;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Tratamiento {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Veterinario veterinario;
    private Mascota mascota;
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
    public Veterinario getVeterinario() {
        return veterinario;
    }
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    public Mascota getMascota() {
        return mascota;
    }
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    
}
