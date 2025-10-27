package com.example.demo.servicio;

import java.util.List;

import com.example.demo.entidades.Usuario;

public interface ServicioUsuario {
//metodo para obtener todos los usuarios
    List<Usuario> getAllUsuarios();
    //metodo para obtener usuario por id
    Usuario getUsuarioById(Long id);
    //metodo para agregar usuario
    void addUsuario(Usuario usuario);
    //metodo para eliminar usuario
    void removeUsuario(Long id);
    //metodo para actualizar usuario
    void updateUsuario(Usuario usuario);
    //metodo para buscar usuario por email
    public Usuario getByEmail(String email);
    
}
