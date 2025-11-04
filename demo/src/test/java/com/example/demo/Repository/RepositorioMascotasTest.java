package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioUsuarios;

@DataJpaTest
@ActiveProfiles("test")
public class RepositorioMascotasTest {

    @Autowired
    private RepositorioMascotas repositorioMascotas;

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;

    private Usuario dueno;

    @BeforeEach
    void setUp() {
        // Crear y guardar un usuario
        dueno = new Usuario();
        dueno.setNombre("Eduardo");
        dueno.setEmail("i@mundoanimal.com"); 
        repositorioUsuarios.save(dueno);

        // Crear y guardar dos mascotas asociadas al usuario
        Mascota luna = new Mascota();
        luna.setNombre("Luna");
        luna.setEspecie("Perro"); 
        luna.setEstado("activo");
        luna.setDueno(dueno);

        Mascota michi = new Mascota();
        michi.setNombre("Michi");
        michi.setEspecie("Gato"); 
        michi.setEstado("inactivo");
        michi.setDueno(dueno);

        repositorioMascotas.save(luna);
        repositorioMascotas.save(michi);
    }

    @Test
    void guardar_y_buscar_por_id() {
        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Firulais");
        nuevaMascota.setEspecie("Perro");
        nuevaMascota.setEstado("activo");
        nuevaMascota.setDueno(dueno);

        Mascota guardada = repositorioMascotas.save(nuevaMascota);

        assertThat(guardada.getId()).isNotNull();

        Optional<Mascota> encontrada = repositorioMascotas.findById(guardada.getId());
        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getNombre()).isEqualTo("Firulais");
    }

    @Test
    void listar_todas() {
        List<Mascota> mascotas = repositorioMascotas.findAll();
        assertThat(mascotas).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void actualizar_estado_con_update_query() {
        Mascota mascota = repositorioMascotas.findAll().get(0);
        repositorioMascotas.updateEstadoById(mascota.getId(), "inactivo");

        Optional<Mascota> actualizada = repositorioMascotas.findById(mascota.getId());
        assertThat(actualizada).isPresent();
        assertThat(actualizada.get().getEstado()).isEqualTo("inactivo");
    }

    @Test
    void eliminar_por_id() {
        Mascota mascota = repositorioMascotas.findAll().get(0);
        repositorioMascotas.deleteById(mascota.getId());

        Optional<Mascota> eliminada = repositorioMascotas.findById(mascota.getId());
        assertThat(eliminada).isNotPresent();
    }

    @Test
    void listar_por_dueno() {
        List<Mascota> mascotas = repositorioMascotas.findByDueno_Id(dueno.getId());
        assertThat(mascotas).hasSize(2);
        assertThat(mascotas).extracting("nombre").containsExactlyInAnyOrder("Luna", "Michi");
    }

    @Test
    void contar_por_estado_ignore_case() {
        long inactivas = repositorioMascotas.countByEstadoIgnoreCase("inactivo");
        assertThat(inactivas).isGreaterThanOrEqualTo(1);
    }
}
