package com.example.demo.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioMedicamento;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioVeterinarios;
import com.example.demo.servicio.ServicioTratamiento;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ServicioTratamientoIntegrationTest {

    @Autowired
    private ServicioTratamiento servicioTratamiento;

    @Autowired
    private RepositorioMascotas repoMascota;

    @Autowired
    private RepositorioMedicamento repoMedicamento;

    @Autowired
    private RepositorioVeterinarios repoVeterinario;

    @Autowired
    private RepositorioTratamiento repoTratamiento;

    private Mascota mascota;
    private Medicamento medicamento;
    private Veterinario veterinario;

    @BeforeEach
    void setUp() { 
        mascota = new Mascota();
        mascota.setNombre("Rocky");
        mascota.setEstado("activo");
        repoMascota.save(mascota);

        medicamento = new Medicamento();
        medicamento.setNombre("Antibiótico");
        medicamento.setUnidades(5);
        medicamento.setUnidades_vendidas(0);
        repoMedicamento.save(medicamento);

        veterinario = new Veterinario();
        veterinario.setNombre("Dr. López");
        repoVeterinario.save(veterinario);
    }

    @Test
    void crearTratamiento_deberiaGuardarCorrectamente() {
        Tratamiento t = new Tratamiento("Tratamiento de prueba");
        Tratamiento guardado = servicioTratamiento.crear(t, mascota.getId(), medicamento.getId(), veterinario.getId());

        assertNotNull(guardado.getId());
        assertEquals("Tratamiento de prueba", guardado.getNombre());
        assertEquals(veterinario.getId(), guardado.getVeterinario().getId());
        assertEquals(mascota.getId(), guardado.getMascota().getId());
        assertEquals(1, guardado.getMedicamentos().size());
    }

    @Test
    void listarPorMascota_deberiaRetornarListaDeTratamientos() {
        Tratamiento t = new Tratamiento("Control anual");
        servicioTratamiento.crear(t, mascota.getId(), medicamento.getId(), veterinario.getId());

        List<Tratamiento> tratamientos = servicioTratamiento.listarPorMascota(mascota.getId());

        assertEquals(1, tratamientos.size());
        assertEquals("Control anual", tratamientos.get(0).getNombre());
    }

    @Test
    void eliminarTratamiento_deberiaEliminarloDeLaBase() {
        Tratamiento t = new Tratamiento("Eliminar test");
        Tratamiento guardado = servicioTratamiento.crear(t, mascota.getId(), medicamento.getId(), veterinario.getId());

        servicioTratamiento.eliminar(guardado.getId());
        boolean existe = repoTratamiento.findById(guardado.getId()).isPresent();

        assertFalse(existe);
    }

    @Test
    void obtenerVeterinarioPorTratamiento_deberiaRetornarVeterinarioAsociado() {
        Tratamiento t = new Tratamiento("Chequeo");
        Tratamiento guardado = servicioTratamiento.crear(t, mascota.getId(), medicamento.getId(), veterinario.getId());

        Veterinario obtenido = servicioTratamiento.obtenerVeterinarioPorTratamiento(guardado.getId());

        assertEquals(veterinario.getNombre(), obtenido.getNombre());
    }

    @Test
    void crearTratamiento_conMascotaInactiva_deberiaLanzarExcepcion() {
        mascota.setEstado("inactivo");
        repoMascota.save(mascota);

        Tratamiento t = new Tratamiento("Tratamiento inválido");

        assertThrows(Exception.class, () -> {
            servicioTratamiento.crear(t, mascota.getId(), medicamento.getId(), veterinario.getId());
        });
    }
}
