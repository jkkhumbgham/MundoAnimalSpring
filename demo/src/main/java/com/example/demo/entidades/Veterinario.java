package com.example.demo.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private UsuarioVet usuarioVet;
    private String nombre;
    private String email;
     @Transient
    private String password;
    private int cedula;
    private String especialidad;
    private String foto;
    private String estado;
    private String estadoOriginal;

    @JsonIgnore
    @OneToMany(mappedBy = "veterinario")
    private List<Tratamiento> tratamientos;

    public Veterinario(String nombre, String email, String password, int cedula, String especialidad, String foto, String estado) {
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        this.cedula=cedula;
        this.especialidad = especialidad;
        this.estado = estado;
        this.foto = foto;
        this.estadoOriginal = estado;
    }

    
}
