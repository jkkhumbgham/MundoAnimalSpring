package com.example.demo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entidades.Medicamento;


public interface RepositorioMedicamento extends JpaRepository<Medicamento,Long> {
    //metodo para buscar todos los medicamentos
    List<Medicamento> findAll();
    //metodo para buscar medicamento por id
    //Medicamento findById(long id);
    Optional<Medicamento> findById(long id);

    //metodo para buscar medicamentos por tratamiento
    List<Medicamento>  findByTratamientos_Id(Long tratamientoId);

    @Query("SELECT m.nombre, m.unidades_vendidas AS total FROM Medicamento m ORDER BY m.unidades_vendidas DESC LIMIT 10")
    List<Object[]> findTop10Medicamentos();

    @Query("SELECT m.nombre, m.unidades AS stock FROM Medicamento m")
    List<Object[]> findStockMedicamentos();

    @Query("SELECT COUNT(m) FROM Medicamento m WHERE m.unidades_vendidas > 0")
    long countMedicinasVendidas();

    @Query("SELECT SUM(m.unidades_vendidas) FROM Medicamento m")
    long sumUnidadesVendidas();

    @Query("SELECT SUM(m.unidades_vendidas * m.precio_venta) FROM Medicamento m")
    Double calculateTotalRevenue();

    @Query("SELECT SUM(m.unidades_vendidas * m.precio_compra) FROM Medicamento m")
    Double calculateTotalCost();

}
