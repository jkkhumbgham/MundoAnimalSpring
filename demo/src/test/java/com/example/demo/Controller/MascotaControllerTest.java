package com.example.demo.controller;

import com.example.demo.controlador.MascotaController;
import com.example.demo.entidades.Mascota;
import com.example.demo.servicio.ServicioMascotaImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MascotaController.class)
@ActiveProfiles("test")
public class MascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ServicioMascotaImpl servicioMascotas;
    
    @MockBean
    private com.example.demo.servicio.ServicioUsuarioImpl servicioUsuarios;

    private Mascota mascota(String nombre, String especie, String estado, Long id, Long duenoId) {
        var m = new Mascota();
        m.setId(id);
        m.setNombre(nombre);
        m.setEspecie(especie);
        m.setEstado(estado);
        if (duenoId != null) {
            var u = new com.example.demo.entidades.Usuario();
            u.setId(duenoId);
            m.setDueno(u);
        }
        return m;
    }

    @Test
    void GET_lista() throws Exception {
        when(servicioMascotas.getAllMascotas()).thenReturn(Arrays.asList(
            mascota("Luna", "Perro", "activo", 1L, 10L),
            mascota("Michi", "Gato", "inactivo", 2L, 10L)
        ));

        mvc.perform(get("/mascotas"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].nombre").value("Luna"));

        verify(servicioMascotas).getAllMascotas();
    }

    @Test
    void GET_detalle() throws Exception {
        when(servicioMascotas.getMascotaById(1L)).thenReturn(mascota("Luna", "Perro", "activo", 1L, 10L));

        mvc.perform(get("/mascotas/find/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Luna"));

        verify(servicioMascotas).getMascotaById(1L);
    }

    @Test
    void POST_crear() throws Exception {
        Mascota nuevaMascota = mascota("Firulais", "Perro", "activo", null, 10L);

        mvc.perform(post("/mascotas")
                .contentType("application/json")
                .content(mapper.writeValueAsString(nuevaMascota)))
            .andExpect(status().isOk());

        verify(servicioMascotas).addMascota(any(Mascota.class));
    }

    @Test
    void PUT_actualizar() throws Exception {
        Mascota mascotaActualizada = mascota("Luna", "Perro", "inactivo", 1L, 10L);

        mvc.perform(put("/mascotas/editar")
                .contentType("application/json")
                .content(mapper.writeValueAsString(mascotaActualizada)))
            .andExpect(status().isOk());

        verify(servicioMascotas).updateMascota(any(Mascota.class));
    }

    @Test
    void PATCH_actualizar_estado() throws Exception {
        when(servicioMascotas.getMascotaById(5L)).thenReturn(mascota("Luna", "Perro", "activo", 5L, 10L));

        mvc.perform(patch("/mascotas/5/estado")
                .param("estado", "inactivo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.estado").value("inactivo"));

        verify(servicioMascotas).updateEstadoById(5L, "inactivo");
    }

    @Test
    void DELETE_borrar() throws Exception {
        mvc.perform(delete("/mascotas/delete/1"))
            .andExpect(status().isOk());

        verify(servicioMascotas).deleteMascota(1L);
    }
}
