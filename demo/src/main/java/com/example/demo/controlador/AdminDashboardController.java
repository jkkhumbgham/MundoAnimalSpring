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
  // Use the repository method that counts by estado (case-insensitive)
  mascotasActivas = mascotas.countByEstadoIgnoreCase("activo");

    long tratamientosTotales = tratamientos.count(); 

    Map<String, Object> r = new LinkedHashMap<>();
    r.put("mascotasActivas", mascotasActivas);
    r.put("tratamientosActivos", tratamientosTotales);
    return r;
  }

  
@GetMapping("/top-meds")
public List<Map<String, Object>> topMeds() {
    List<Object[]> topMeds = medicamentos.findTop10Medicamentos();
    return topMeds.stream()
        .map(med -> {
            Map<String, Object> row = new HashMap<>();
            row.put("nombre", med[0]);
            row.put("total", med[1]);
            return row;
        })
        .collect(Collectors.toList());
}

@GetMapping("/stock")
public List<Map<String, Object>> stock() {
  return medicamentos.findStockMedicamentos().stream()
      .map(m -> {
        Map<String, Object> row = new HashMap<>();
        row.put("nombre", m[0]);
        row.put("stock", m[1]);
        return row;
      })
      .collect(Collectors.toList());
}
@GetMapping("/ganancias")
public Map<String, Object> gananciasTotales() {
    long medicinasVendidas = medicamentos.countMedicinasVendidas();
    long unidadesVendidas = medicamentos.sumUnidadesVendidas();
    Double ingresos = medicamentos.calculateTotalRevenue();
    Double costo = medicamentos.calculateTotalCost();

    
    Double ganancia = ingresos - costo;

    
    Map<String, Object> r = new LinkedHashMap<>();
    r.put("medicinasVendidas", medicinasVendidas);
    r.put("unidadesVendidas", unidadesVendidas);
    r.put("ingresos", ingresos);
    r.put("ganancia", ganancia);
    return r;
}

}
