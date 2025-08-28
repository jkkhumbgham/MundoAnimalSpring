package com.example.demo.servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.RepositorioUsuarios;
@Service
public class ServicioUsuarioImpl implements ServicioUsuario {
    @Autowired
    RepositorioUsuarios repositorio;

    @Override
    public Collection<Usuario> getAllUsuarios() {
        return repositorio.getAllUsuarios();
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return repositorio.getUsuarioById(id);
    }
    @Override
    public void addUsuario(Usuario usuario) {
        repositorio.addUsuario(usuario);
    }

    @Override
    public void removeUsuario(int id) {
        repositorio.removeUsuario(id);
    }
    
    @Override
    public Usuario getUsuarioEmail(String email) {
        return repositorio.getUsuarioEmail(email);
    }
}
