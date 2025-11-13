package com.example.demo.entidades;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tratamiento {
    @Id
    @GeneratedValue
    private Long id;
    
    private String nombre;
   
    //@JsonIgnore
    @ManyToOne
    private Veterinario veterinario;

    @JsonIgnore
    @ManyToOne
    private Mascota mascota;
    
    //@JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "tratamiento_medicamento",
        joinColumns = @JoinColumn(name = "tratamiento_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    public Tratamiento(String nombre) {
        this.nombre = nombre;
        this.medicamentos = new ArrayList<>();
    }
    
}
