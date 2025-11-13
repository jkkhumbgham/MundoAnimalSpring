package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Role;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioVet;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioRoles;
import com.example.demo.repositorio.RepositorioUsuarioVet;
import com.example.demo.repositorio.RepositorioUsuarios;

@Service
public class CustomUserDetilService implements UserDetailsService {

    @Autowired
    private RepositorioUsuarioVet repositorioUsuarios;

    @Autowired
    private RepositorioRoles repositorioRoles;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioVet userDB = repositorioUsuarios.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );

        UserDetails userDetails = new User(userDB.getUsername(), userDB.getPassword(), mapGrantedAuthorities(userDB.getRoles()));
        return userDetails;
    }

    private Collection<GrantedAuthority> mapGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UsuarioVet mapUsuario(Usuario user){
        UsuarioVet ret = new UsuarioVet();
        ret.setUsername(user.getEmail());
        ret.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roles = repositorioRoles.findByName("usuario").get();
        ret.setRoles(List.of(roles));
        return ret;

    }    

    public UsuarioVet mapVeterinario(Veterinario user){
        UsuarioVet ret = new UsuarioVet();
        ret.setUsername(user.getEmail());
        ret.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roles = repositorioRoles.findByName("veterinario").get();
        ret.setRoles(List.of(roles));
        return ret;
    }
}
