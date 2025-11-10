package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private UsuarioVet usuarioVet;
    private String nombre;
    private String telefono;
    private int cedula;
    private String email;
    @Transient
    private String password;
    private String foto;
    @JsonIgnore
    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL)
    private List<Mascota> mascotas= new ArrayList<>();

    public Usuario(Long id, String nombre, String telefono, String email, String password, String foto, List<Mascota> mascotas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.mascotas = mascotas;
    }

     public Usuario( String nombre, String telefono, String email, String password, String foto, int cedula) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.cedula = cedula;
        this.email = email;
        this.password = password;
        this.foto = foto;
    }



}
