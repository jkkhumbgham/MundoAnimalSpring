package com.example.demo.repositorio;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Mascota;

import jakarta.transaction.Transactional;

@Repository
public interface RepositorioMascotas extends JpaRepository<Mascota, Long> {
    
    @Modifying
    @Transactional
    @Query("UPDATE Mascota m SET m.estado =:estado WHERE m.id = :id")
    void updateEstadoById(@Param("id") Long id, @Param("estado") String estado);

    List<Mascota> findByDueno_Id(Long usuarioId);

    
}


