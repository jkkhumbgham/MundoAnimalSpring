package com.example.demo.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidades.Medicamento;
import com.example.demo.repositorio.RepositorioMedicamento;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MedicamentoRepositoryTest {

    @Autowired
    private RepositorioMedicamento repositorioMedicamento;
    
    private Medicamento med(String nombre, int unidades, int unidadesVendidas, int precioCompra, int precioVenta) {
        Medicamento m = new Medicamento();
        m.setNombre(nombre);
        m.setUnidades(unidades);
        m.setUnidades_vendidas(unidadesVendidas);
        m.setPrecio_compra(precioCompra);
        m.setPrecio_venta(precioVenta);
        return m;
    }

    @BeforeEach
   void init() {
        System.out.println("Seed Basico");
        repositorioMedicamento.deleteAll();
        repositorioMedicamento.saveAll(Arrays.asList(
            med("A", 100,  5,  3,  5),
            med("B",  80,  0,  2,  4),
            med("C",  60,  2,  1,  3),
            med("D",  50,  9,  4,  8),
            med("E",  40,  1,  2,  5),
            med("F",  30, 12,  6, 10),
            med("G",  20,  7,  1,  2),
            med("H",  15,  3,  2,  1),
            med("I",  10, 20,  7, 12),
            med("J",   5, 14,  3,  6),
            med("K",  70,  8,  2,  4),
            med("L",  90, 11,  5,  9)
        ));
    }


    @Test
    public void RepositorioMedicamento_findTop10Medicamentos_MapTop10Medicamentos() {

        List<Object[]> filas = repositorioMedicamento.findTop10Medicamentos();
        Assertions.assertThat(filas).hasSize(10);

        List<String> nombres = filas.stream().map(r -> (String) r[0]).collect(Collectors.toList());
        List<Number> vendidos = filas.stream().map(r -> (Number) r[1]).collect(Collectors.toList());

        Assertions.assertThat(nombres).isEqualTo(Arrays.asList("I","J","F","L","D","K","G","A","H","C"));

        Assertions.assertThat(vendidos).isEqualTo(Arrays.asList(20,14,12,11,9,8,7,5,3,2));

    }

    @Test
    public void RepositorioMedicamento_findStockMedicamentos_MapNombreYStock() {
        List<Object[]> filas = repositorioMedicamento.findStockMedicamentos(); 

        Map<String,Integer> mapa = filas.stream().collect(Collectors.toMap(
            r -> (String) r[0], r -> ((Number) r[1]).intValue()
        ));

        Assertions.assertThat(mapa.get("A")).isEqualTo(100);
        Assertions.assertThat(mapa.get("J")).isEqualTo(5);
        Assertions.assertThat(mapa.get("I")).isEqualTo(10);
    }

    @Test
    public void RepositorioMedicamento_countMedicinasVendidas_RetornaConteo() {

        long conteo = repositorioMedicamento.countMedicinasVendidas(); 

        Assertions.assertThat(conteo).isEqualTo(11L);
    }

    @Test
    public void RepositorioMedicamento_sumUnidadesVendidas_SumaEntera() {
 
        long suma = repositorioMedicamento.sumUnidadesVendidas();

        Assertions.assertThat(suma).isEqualTo(92L);
    }

    @Test
    public void RepositorioMedicamento_calculateTotalRevenue_SumaDouble() {

        Double total = repositorioMedicamento.calculateTotalRevenue();


        Assertions.assertThat(total).isEqualTo(700);
    }

    @Test
    public void RepositorioMedicamento_calculateTotalCost_SumaDouble() {

        Double total = repositorioMedicamento.calculateTotalCost();

        Assertions.assertThat(total).isEqualTo(393);
    }

}