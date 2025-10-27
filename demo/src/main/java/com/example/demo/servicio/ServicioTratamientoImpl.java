package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioMedicamento;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioVeterinarios;

@Service
@Transactional
public class ServicioTratamientoImpl implements ServicioTratamiento {
    // declaracion repos necesarios
    @Autowired
    private RepositorioTratamiento repoTratamiento;
    @Autowired
    private RepositorioMascotas repoMascotas;
    @Autowired
    private RepositorioMedicamento repoMedicamento;
    @Autowired
    private RepositorioVeterinarios repoVeterinarios;


    
  
    
//metodos
//metodo para crear tratamiento
    @Override
    public Tratamiento crear(Tratamiento t, Long mascotaId, Long medicamentoId, Long veterinarioId) {
        Mascota mascota = repoMascotas.findById(mascotaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada"));
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicamento no encontrado"));
        Veterinario vet = repoVeterinarios.findById(veterinarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinario no encontrado"));

        t.setMascota(mascota);
        t.setVeterinario(vet);
        var unidades=med.getUnidades();
        var vendidos=med.getUnidades_vendidas();
         if(unidades == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay unidades disponibles");
         }else{
            unidades=unidades-1; 
            vendidos=vendidos+1;
            med.setUnidades(unidades);
            med.setUnidades_vendidas(vendidos);
        }
        if (mascota.getEstado().equals("inactivo")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La mascota no esta activa");
        }
        t.getMedicamentos().add(med);


        System.out.println("Mascota encontrada: " + mascota.getNombre());
        System.out.println("Medicamento encontrado: " + med.getNombre());
        System.out.println("Veterinario encontrado: " + vet.getNombre());
        System.out.println("Tratamiento antes de guardar: " + t);

        return repoTratamiento.save(t);
    }

    //metodo para listar tratamientos por mascota
    @Override
    public List<Tratamiento> listarPorMascota(Long mascotaId) {
        return repoTratamiento.findByMascotaId(mascotaId);
    }

    //metodo para eliminar tratamiento
    @Override
    public void eliminar(Long id) {
        repoTratamiento.deleteById(id);
    }
    //metodo para obtener veterinario por tratamiento
    @Override
    public Veterinario obtenerVeterinarioPorTratamiento(Long tratamientoId) {
        Tratamiento tratamiento = repoTratamiento.findById(tratamientoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratamiento no encontrado"));
        return tratamiento.getVeterinario();
    }
}
