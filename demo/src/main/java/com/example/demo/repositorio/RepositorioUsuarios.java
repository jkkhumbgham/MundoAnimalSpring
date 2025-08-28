package com.example.demo.repositorio;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

import com.example.demo.entidades.Usuario;


@Repository
public class RepositorioUsuarios {

    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public RepositorioUsuarios(){
        // Inicializar algunos usuarios de ejemplo
        usuarios.put(1, new Usuario(1, "Andres Garcia", "123456789", "juan@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(1)));
        usuarios.put(2, new Usuario(2, "Alvaro Morata", "987654321", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(2)));
        usuarios.put(3, new Usuario(3, "Juan Castro", "555555555", "pablo@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(3)));
        usuarios.put(4, new Usuario(4, "Omar Perez", "444444444", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(4)));
        usuarios.put(5, new Usuario(5, "Luis Manuel Seijas", "333333333", "lucia@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(5)));
        usuarios.put(6, new Usuario(6, "Pablo Hernandez", "222222222", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(6)));
        usuarios.put(7, new Usuario(7, "Andres Colmenares", "111111111", "lucia@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(7)));
        usuarios.put(8, new Usuario(8, "Esteban Lopez", "666666666", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(8)));
        usuarios.put(9, new Usuario(9, "Simba", "999999999", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(9)));
        usuarios.put(10, new Usuario(10, "Estefania Alba", "000000000", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(10)));
        usuarios.put(11, new Usuario(11, "Margarita Castro", "888888888", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", RepositorioMascotas.getMascotasByUsuario(11)));
    }

    public Collection<Usuario> getAllUsuarios() {
        return usuarios.values();
    }
    public Usuario getUsuarioById(int id) {
        return usuarios.get(id);
    }
    public void addUsuario(Usuario usuario) {
        if (usuario.getId() == null) {
            int tam=usuarios.size();
            int lastId = usuarios.get(tam).getId();
            usuario.setId(lastId+1);
            usuarios.put(usuario.getId(), usuario);
        }else{
            usuario.setMascotas(RepositorioMascotas.getMascotasByUsuario(usuario.getId()));
            usuarios.put(usuario.getId(), usuario);
        }
    }
    public Usuario getUsuarioEmail(String email) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }
    public void removeUsuario(int id) {
        usuarios.remove(id);
    }
}
