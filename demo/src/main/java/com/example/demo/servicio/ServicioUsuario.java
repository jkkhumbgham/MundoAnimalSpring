package com.example.demo.servicio;

import java.util.Collection;

import com.example.demo.entidades.Usuario;

public interface ServicioUsuario {

    Collection<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Long id);
    void addUsuario(Usuario usuario);
    void removeUsuario(Long id);
    void updateUsuario(Usuario usuario);
    public Usuario getByEmail(String email);
    
}
