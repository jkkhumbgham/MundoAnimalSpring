package com.example.demo.servicio;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Usuario;
import com.example.demo.repositorio.RepositorioUsuarios;
@Service
public class ServicioUsuarioImpl implements ServicioUsuario {
    // declaracion repos necesarios
    @Autowired
    RepositorioUsuarios repositorio;


    //definicion de metodos

    //metodo para buscar todos los usuarios
    @Override
    public List<Usuario> getAllUsuarios() {
        return repositorio.findAll();
    }

    //metodo para buscar usuario por id
    @Override
    public Usuario getUsuarioById(Long id) {
        return repositorio.findById(id).get();
    }
    //metodo para agregar usuario
    @Override
    public void addUsuario(Usuario usuario) {
        repositorio.save(usuario);
    }
    //metodo para eliminar usuario

    @Override
    public void removeUsuario(Long id) {
        repositorio.deleteById(id);
    }
//metodo para actualizar usuario
    @Override
    public void updateUsuario(Usuario usuario) {
        repositorio.save(usuario);
    }
    //metodo para buscar usuario por email
    @Override
    public Usuario getByEmail(String email) {
        return repositorio.findByEmail(email);
    }
}
