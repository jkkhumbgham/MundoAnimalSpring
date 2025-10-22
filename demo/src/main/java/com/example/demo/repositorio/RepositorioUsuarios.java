package com.example.demo.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Usuario;


@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
    
    //metodo para buscar usuario por email
    Usuario findByEmail(String email);

}
