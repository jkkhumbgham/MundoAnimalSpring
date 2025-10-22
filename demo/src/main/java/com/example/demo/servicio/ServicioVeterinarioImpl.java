package com.example.demo.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioVeterinarios;

@Service
public class ServicioVeterinarioImpl implements ServicioVeterinario {
    // declaracion repos necesarios
    @Autowired
    RepositorioVeterinarios repositorio;

    @Autowired
    private RepositorioTratamiento repositorioTratamiento;

    //definicion de metodos
    // metodo para obtener todos los veterinarios
    @Override
    public List<Veterinario> getAllVeterinarios() {
        return repositorio.findAll();
    }
// metodo para obtener veterinario por id
    @Override
    public Veterinario getVeterinarioById(Long id) {
        return repositorio.findById(id).get();
    }
    // metodo para agregar veterinario
    @Override
    public void addVeterinario(Veterinario veterinario) {
        repositorio.save(veterinario);
    }
// metodo para eliminar veterinario
    @Override
    public void removeVeterinario(Long id) {
        repositorio.deleteById(id);
    }
// metodo para el soft delete
    @Override
    public void softdeleteById(Long id) {
    Veterinario veterinario = repositorio.findById(id).orElse(null);
    if (veterinario != null) {
        if ("inactivo".equalsIgnoreCase(veterinario.getEstado())) {
            // Restaurar el estado original
            repositorio.updateEstadoById(id, veterinario.getEstadoOriginal());
        } else {
            // Guardar el estado actual antes de poner inactivo
            veterinario.setEstadoOriginal(veterinario.getEstado());
            repositorio.save(veterinario);
            repositorio.updateEstadoById(id, "inactivo");
        }
    }
}
    // metodo para actualizar veterinario
    @Override
    public void updateVeterinario(Veterinario veterinario) {
        repositorio.save(veterinario);
    }
    // metodo para buscar veterinario por email
    @Override
    public Veterinario getByEmail(String email) {
        return repositorio.findByEmail(email);
    }

    // metodo para obtener mascotas tratadas por veterinario
    @Override
    public List<Mascota> obtenerMascotasTratadasPorVeterinario(Long idVeterinario) {
        List<Tratamiento> tratamientos = repositorioTratamiento.findAllByVeterinario_Id(idVeterinario);
        return tratamientos.stream()
                .map(Tratamiento::getMascota)
                .distinct()
                .collect(Collectors.toList());
    }
}
