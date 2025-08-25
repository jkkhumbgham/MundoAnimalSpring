package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidades.Usuario;

public interface ServicioUsuario {

    Collection<Usuario> getAllUsuarios();
    Usuario getUsuarioById(int id);
    void addUsuario(Usuario usuario);
    void removeUsuario(int id);
    
}
