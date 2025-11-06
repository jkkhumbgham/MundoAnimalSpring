package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioMedicamento;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioVeterinarios;
import com.example.demo.servicio.ServicioTratamientoImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) 
public class ServicioTratamientoMockTest {
    @Mock
    private RepositorioTratamiento repoTratamiento;
    @Mock
    private RepositorioMascotas repoMascotas;
    @Mock
    private RepositorioMedicamento repoMedicamento;
    @Mock
    private RepositorioVeterinarios repoVeterinarios;

    @InjectMocks
    private ServicioTratamientoImpl servicioTratamiento;

    private Mascota mascotaActiva;
    private Mascota mascotaInactiva;
    private Medicamento medicamentoSinStock; 
    private Veterinario veterinario;
    private Tratamiento tratamiento;

    @BeforeEach
    public void setUp() {
        mascotaActiva = new Mascota();
        mascotaActiva.setId(1L);
        mascotaActiva.setNombre("Firulais");
        mascotaActiva.setEstado("activo");

        mascotaInactiva = new Mascota();
        mascotaInactiva.setId(1L);
        mascotaInactiva.setNombre("Bobi");
        mascotaInactiva.setEstado("inactivo");

        medicamentoSinStock = new Medicamento();
        medicamentoSinStock.setId(4L);
        medicamentoSinStock.setUnidades(0);
        medicamentoSinStock.setUnidades_vendidas(10);

        veterinario = new Veterinario();
        veterinario.setId(3L);
        veterinario.setNombre("Dr. Gómez");

        tratamiento = new Tratamiento("Tratamiento Test");
        tratamiento.setMedicamentos(new ArrayList<>()); 
        
    }


// PRUEBAS PARA EL MÉTODO: crear
//NO SIRVEEEE :(
    @Test
void crearTratamiento_deberiaGuardarYActualizarStockCorrectamente() {
    Tratamiento tratamientoParaTest = new Tratamiento("Tratamiento de Prueba OK");
    tratamientoParaTest.setMedicamentos(new ArrayList<>());
    
    Medicamento medicamentoParaTest = new Medicamento();
    medicamentoParaTest.setId(2L);
    medicamentoParaTest.setNombre("Antibiótico");
    medicamentoParaTest.setUnidades(10);
    medicamentoParaTest.setUnidades_vendidas(0);

    when(repoMascotas.findById(1L)).thenReturn(Optional.of(mascotaActiva));
    when(repoVeterinarios.findById(3L)).thenReturn(Optional.of(veterinario));
    
    doReturn(Optional.of(medicamentoParaTest)).when(repoMedicamento).findById(2L);
    

    when(repoTratamiento.save(any(Tratamiento.class))).thenReturn(tratamientoParaTest); 

    Tratamiento resultado = servicioTratamiento.crear(tratamientoParaTest, 1L, 2L, 3L);

    assertNotNull(resultado, "El tratamiento no debería ser nulo");
    assertEquals(9, medicamentoParaTest.getUnidades(), "El stock debe disminuir en 1");
    
    verify(repoTratamiento, times(1)).save(tratamientoParaTest);
    verify(repoMedicamento, times(1)).findById(2L);
    verify(repoMascotas, times(1)).findById(1L);
    verify(repoVeterinarios, times(1)).findById(3L);
}



    @Test
    void crearTratamiento_conMascotaInactiva_deberiaLanzarExcepcion() {
        Medicamento dummyMed = new Medicamento();
        when(repoMascotas.findById(1L)).thenReturn(Optional.of(mascotaInactiva));
        when(repoMedicamento.findById(2L)).thenReturn(Optional.of(dummyMed));
        when(repoVeterinarios.findById(3L)).thenReturn(Optional.of(veterinario));

        assertThrows(ResponseStatusException.class, 
                     () -> servicioTratamiento.crear(tratamiento, 1L, 2L, 3L),
                     "Debe lanzar ResponseStatusException si la mascota está inactiva");

        verify(repoTratamiento, never()).save(any(Tratamiento.class));
    }

    @Test
    void crearTratamiento_sinUnidadesDisponibles_deberiaLanzarExcepcion() {
        when(repoMascotas.findById(1L)).thenReturn(Optional.of(mascotaActiva));
        when(repoMedicamento.findById(4L)).thenReturn(Optional.of(medicamentoSinStock));
        when(repoVeterinarios.findById(3L)).thenReturn(Optional.of(veterinario));

        assertThrows(ResponseStatusException.class, 
                     () -> servicioTratamiento.crear(tratamiento, 1L, 4L, 3L),
                     "Debe lanzar ResponseStatusException si no hay unidades");

        verify(repoTratamiento, never()).save(any(Tratamiento.class));
    }

    @Test
    void crearTratamiento_conMascotaNoEncontrada_deberiaLanzarExcepcion() {
        when(repoMascotas.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, 
                     () -> servicioTratamiento.crear(tratamiento, 99L, 2L, 3L));

        verify(repoTratamiento, never()).save(any(Tratamiento.class));
    }
    

// PRUEBAS PARA EL MÉTODO: listarPorMascota 
    @Test
    void listarPorMascota_deberiaRetornarListaDeTratamientos() {
        Tratamiento t1 = new Tratamiento("T1");
        Tratamiento t2 = new Tratamiento("T2");
        List<Tratamiento> listaEsperada = List.of(t1, t2);

        when(repoTratamiento.findByMascotaId(1L)).thenReturn(listaEsperada);

        List<Tratamiento> listaResultado = servicioTratamiento.listarPorMascota(1L);

        assertEquals(2, listaResultado.size(), "Debería retornar 2 tratamientos");
        assertEquals("T1", listaResultado.get(0).getNombre(), "El primer tratamiento es T1"); 

        verify(repoTratamiento, times(1)).findByMascotaId(1L);
    }
    
// PRUEBAS PARA EL MÉTODO: eliminar
    @Test
    void eliminarTratamiento_deberiaLlamarADeleteById() {
        servicioTratamiento.eliminar(5L);
        verify(repoTratamiento, times(1)).deleteById(5L);
    }

// PRUEBAS PARA EL MÉTODO: obtenerVeterinarioPorTratamiento
    @Test
    void obtenerVeterinarioPorTratamiento_deberiaRetornarVeterinarioAsociado() {
        tratamiento.setVeterinario(veterinario);
        when(repoTratamiento.findById(10L)).thenReturn(Optional.of(tratamiento));

        Veterinario resultado = servicioTratamiento.obtenerVeterinarioPorTratamiento(10L);

        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals("Dr. Gómez", resultado.getNombre(), "Debe retornar el veterinario del tratamiento");
        
        verify(repoTratamiento, times(1)).findById(10L);
    }

    @Test
    void obtenerVeterinarioPorTratamiento_conTratamientoNoEncontrado_deberiaLanzarExcepcion() {
        when(repoTratamiento.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, 
                     () -> servicioTratamiento.obtenerVeterinarioPorTratamiento(99L),
                     "Debe lanzar ResponseStatusException si el tratamiento no se encuentra");
    }
}