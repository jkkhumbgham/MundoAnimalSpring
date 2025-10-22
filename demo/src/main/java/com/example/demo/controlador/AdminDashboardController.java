package com.example.demo.controlador;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioMedicamento;
import com.example.demo.entidades.Medicamento;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

  @Autowired  
   RepositorioMascotas mascotas;
  @Autowired
   RepositorioTratamiento tratamientos;
  @Autowired
   RepositorioMedicamento medicamentos;

  

  
  @GetMapping("/resumen")
  public Map<String, Object> resumen() {
    long mascotasActivas = 0;
    mascotasActivas = mascotas.countByEstadoIgnoreCase("Saludable")+ mascotas.countByEstadoIgnoreCase("En tratamiento")+ mascotas.countByEstadoIgnoreCase("Recuperándose")+ mascotas.countByEstadoIgnoreCase("Control rutinario");

    long tratamientosTotales = tratamientos.count(); 

    Map<String, Object> r = new LinkedHashMap<>();
    r.put("mascotasActivas", mascotasActivas);
    r.put("tratamientosActivos", tratamientosTotales);
    return r;
  }

  
  @GetMapping("/top-meds")
public List<Map<String, Object>> topMeds() {
  List<Medicamento> all = medicamentos.findAll();
  return all.stream()
      .sorted(Comparator.comparingInt(Medicamento::getUnidades_vendidas).reversed())
      .limit(10)
      .map(m -> {
        Map<String, Object> row = new HashMap<>();
        row.put("nombre", m.getNombre());
        row.put("total", m.getUnidades_vendidas());
        return row;
      })
      .collect(Collectors.toList());
}

@GetMapping("/stock")
public List<Map<String, Object>> stock() {
  return medicamentos.findAll().stream()
      .map(m -> {
        Map<String, Object> row = new HashMap<>();
        row.put("nombre", m.getNombre());
        row.put("stock", m.getUnidades());
        return row;
      })
      .collect(Collectors.toList());
}
@GetMapping("/ganancias")
public Map<String, Object> gananciasTotales() {
  var all = medicamentos.findAll();

  long vendidas = all.stream()
      .filter(m -> m.getUnidades_vendidas() > 0)
      .count();

  long unidadesVendidas = all.stream()
      .mapToLong(m -> (long) m.getUnidades_vendidas())
      .sum();

  
 double ingresos = all.stream()
    .mapToDouble(m -> {
      double unidades =  Double.valueOf(m.getUnidades_vendidas());
      double precio = m.getPrecio_venta();
      return unidades * precio;
    })
    .sum();

  
 double costo = all.stream()
    .mapToDouble(m -> {
      double unidades =  Double.valueOf(m.getUnidades_vendidas());
      double precio = m.getPrecio_compra();
      return unidades * precio;
    })
    .sum();

  Double ganancia = ingresos-costo;

  Map<String, Object> r = new java.util.LinkedHashMap<>();
  r.put("medicinasVendidas", vendidas);
  r.put("unidadesVendidas", unidadesVendidas);
  r.put("ingresos", ingresos);   
  r.put("ganancia", ganancia);  
  return r;
}

}
