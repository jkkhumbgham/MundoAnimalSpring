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
// Lombok removido para evitar fallos de Annotation Processor en el IDE

@Entity
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
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

    public Veterinario() {
    }

    public Veterinario(String nombre, String email, String password, int cedula, String especialidad, String foto, String estado) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.estado = estado;
        this.foto = foto;
        this.estadoOriginal = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioVet getUsuarioVet() { return usuarioVet; }
    public void setUsuarioVet(UsuarioVet usuarioVet) { this.usuarioVet = usuarioVet; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getCedula() { return cedula; }
    public void setCedula(int cedula) { this.cedula = cedula; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getEstadoOriginal() { return estadoOriginal; }
    public void setEstadoOriginal(String estadoOriginal) { this.estadoOriginal = estadoOriginal; }

    public List<Tratamiento> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
