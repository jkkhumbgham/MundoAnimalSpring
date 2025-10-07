package com.example.demo.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entidades.Tratamiento;
import java.util.List;

@Repository
public interface RepositorioTratamiento extends JpaRepository<Tratamiento, Long>{
    List<Tratamiento> findByMascotaId(Long mascotaId);
}
