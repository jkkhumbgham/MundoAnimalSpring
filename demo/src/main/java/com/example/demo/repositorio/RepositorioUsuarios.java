package com.example.demo.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entidades.Usuario;


@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {

    
    Usuario findByEmail(String email);



}
