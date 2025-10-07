package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.RepositorioUsuarios;
@Service
public class ServicioUsuarioImpl implements ServicioUsuario {
    @Autowired
    RepositorioUsuarios repositorio;

    @Override
    public List<Usuario> getAllUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return repositorio.findById(id).get();
    }
    @Override
    public void addUsuario(Usuario usuario) {
        repositorio.save(usuario);
    }

    @Override
    public void removeUsuario(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        repositorio.save(usuario);
    }
    
    @Override
    public Usuario getByEmail(String email) {
        return repositorio.findByEmail(email);
    }
}
