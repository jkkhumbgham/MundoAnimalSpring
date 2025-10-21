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
    @Autowired
    RepositorioMascotas repositorio;
    
    @Autowired
    private RepositorioTratamiento RepositorioTratamiento;

    @Override
    public List<Mascota> getAllMascotas() {
        return repositorio.findAll();
    }
    

    @Override
    public Mascota getMascotaById(Long id) {
        return repositorio.findById(id).get();
    }

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

    

    @Override
    public void deleteMascota(Long id){
        repositorio.deleteById(id);
    }

    @Override
    public void addMascota(Mascota mascota){
        repositorio.save(mascota);
    }
    
    @Override
    public void updateMascota(Mascota mascota){
        repositorio.save(mascota);
    }

    @Override
    public List<Mascota> getByDueño_Id(Long id) {
        return repositorio.findByDueno_Id(id);
    }

    @Override
    public List<Mascota> obtenerMascotasTratadasPorVeterinario(Long idVeterinario) {
        List<Tratamiento> tratamientos = RepositorioTratamiento.findAllByVeterinario_Id(idVeterinario);
        return tratamientos.stream()
                .map(Tratamiento::getMascota)
                .distinct()
                .collect(Collectors.toList());
    }

}
