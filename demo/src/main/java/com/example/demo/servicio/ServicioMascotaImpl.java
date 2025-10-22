package com.example.demo.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioTratamiento;
@Service
public class ServicioMascotaImpl implements ServicioMascotas {
    //declarcion de repositorios necesarios
    @Autowired
    RepositorioMascotas repositorio;
    
    @Autowired
    private RepositorioTratamiento RepositorioTratamiento;


    //declaracion de metodos

    //metodo para obtener todas las mascotas
    @Override
    public List<Mascota> getAllMascotas() {
        return repositorio.findAll();
    }
    

    //metodo para obtener mascota por id
    @Override
    public Mascota getMascotaById(Long id) {
        return repositorio.findById(id).get();
    }


    //metodo para el soft delete
    @Override
    public void softdeleteById(Long id) {
    Mascota mascota = repositorio.findById(id).orElse(null);
    if (mascota != null) {
        if ("inactivo".equalsIgnoreCase(mascota.getEstado())) {
            // Restaurar el estado original
            repositorio.updateEstadoById(id, mascota.getEstadoOriginal());
        } else {
            // Guardar el estado actual antes de poner inactivo
            mascota.setEstadoOriginal(mascota.getEstado());
            repositorio.save(mascota);
            repositorio.updateEstadoById(id, "inactivo");
        }
    }
}

    

//metodo para eliminar mascota
    @Override
    public void deleteMascota(Long id){
        repositorio.deleteById(id);
    }

    //metodo para agregar mascota
    @Override
    public void addMascota(Mascota mascota){
        repositorio.save(mascota);
    }
    
    //metodo para actualizar mascota
    @Override
    public void updateMascota(Mascota mascota){
        repositorio.save(mascota);
    }

    //metodo para obtener mascotas por dueño
    @Override
    public List<Mascota> getByDueño_Id(Long id) {
        return repositorio.findByDueno_Id(id);
    }

    //metodo para obtener mascotas tratadas por veterinario
    @Override
    public List<Mascota> obtenerMascotasTratadasPorVeterinario(Long idVeterinario) {
        List<Tratamiento> tratamientos = RepositorioTratamiento.findAllByVeterinario_Id(idVeterinario);
        return tratamientos.stream()
                .map(Tratamiento::getMascota)
                .distinct()
                .collect(Collectors.toList());
    }

}
