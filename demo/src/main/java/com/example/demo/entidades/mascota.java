package com.example.demo.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "mascotas")
@JsonIgnoreProperties({"vacunasTexto", "alergiasTexto"})
public class Mascota {
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JsonProperty("dueno")
    @JsonAlias({"dueño"})
    private Usuario dueno;

    @ElementCollection
    @CollectionTable(name = "mascota_vacunas", joinColumns = @JoinColumn(name = "mascota_id"))
    @Column(name = "vacuna")
    private List<String> vacunas = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "mascota_alergias", joinColumns = @JoinColumn(name = "mascota_id"))
    @Column(name = "alergia")
    private List<String> alergias = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamiento;

    private String observaciones;
    @JsonProperty("foto")
    private String foto;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private String estado;
    @JsonProperty("ultimavisita")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ultimavisita;

    @JsonProperty("fechaNacimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    @JsonProperty("peso")
    private float peso;
    private int microchipId;
    private String estadoOriginal;
    @Transient
    private String vacunasTexto;

    @Transient
    private String alergiasTexto;


    // ---- Constructores ----
    public Mascota() {
    }

    public Mascota(Long id, Usuario dueño, List<String> vacunas, List<String> alergias,
                   String observaciones, String foto, String nombre, String especie, String raza, String sexo,
                   String estado, Date ultimavisita, Date fechaNacimiento, float peso, int microchipID) {
        this.id = id;
        this.dueno = dueño;
        this.vacunas = vacunas != null ? vacunas : new ArrayList<>();
        this.alergias = alergias != null ? alergias : new ArrayList<>();
        this.observaciones = observaciones;
        this.foto = foto;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.estado = estado;
        this.ultimavisita = ultimavisita;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.microchipId = microchipID;
    }


    public String getVacunasTexto() {
        return vacunasTexto;
    }

    public void setVacunasTexto(String vacunasTexto) {
        this.vacunasTexto = vacunasTexto;
    }

    public String getAlergiasTexto() {
        return alergiasTexto;
    }

    public void setAlergiasTexto(String alergiasTexto) {
        this.alergiasTexto = alergiasTexto;
    }

    public Mascota(List<String> vacunas, List<String> alergias,
               String observaciones, String foto, String nombre, String especie,
               String raza, String sexo, String estado, Date ultimavisita,
               Date fechaNacimiento, float peso, int microchipID) {

    this.vacunas = vacunas != null ? new ArrayList<>(vacunas) : new ArrayList<>();
    this.alergias = alergias != null ? new ArrayList<>(alergias) : new ArrayList<>();
    this.observaciones = observaciones;
    this.foto = foto;
    this.nombre = nombre;
    this.especie = especie;
    this.raza = raza;
    this.sexo = sexo;
    this.estado = estado;
    this.ultimavisita = ultimavisita;
    this.fechaNacimiento = fechaNacimiento;
    this.peso = peso;
    this.microchipId = microchipID;
    this.estadoOriginal="Activo";
}


    // ---- Métodos ----
    public int getEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        LocalDate fecha = this.fechaNacimiento.toLocalDate();
        return Period.between(fecha, LocalDate.now()).getYears();
    }
     public String getEstadoOriginal() {
    return estadoOriginal;
    }
    public void setEstadoOriginal(String estadoOriginal) {
        this.estadoOriginal = estadoOriginal;
    }

    // ---- Getters & Setters ----
    public Long getId() {
        return id;
    }
    public void setId(Long Id) {
        this.id = Id;
    }


    public List<String> getVacunas() {
        return vacunas;
    }
    public void setVacunas(List<String> vacunas) {
        this.vacunas = vacunas;
    }

    public List<String> getAlergias() {
        return alergias;
    }
    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    

    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimavisita() {
        return ultimavisita;
    }
    public void setUltimavisita(Date ultimavisita) {
        this.ultimavisita = ultimavisita;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getMicrochipID() {
        return microchipId;
    }
    public void setMicrochipID(int microchipID) {
        this.microchipId = microchipID;
    }

    public List<Tratamiento> getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(List<Tratamiento> tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Usuario getDueno() {
        return dueno;
    }

    public void setDueno(Usuario dueno) {
        this.dueno = dueno;
    }

}

