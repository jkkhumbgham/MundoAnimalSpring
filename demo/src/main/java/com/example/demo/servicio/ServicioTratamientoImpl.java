package com.example.demo.servicio;

import com.example.demo.entidades.Mascota;
import com.example.demo.entidades.Medicamento;
import com.example.demo.entidades.Tratamiento;
import com.example.demo.entidades.Veterinario;
import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioMedicamento;   
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioVeterinarios;

import lombok.val;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ServicioTratamientoImpl implements ServicioTratamiento {

    private final RepositorioTratamiento repoTratamiento;
    private final RepositorioMascotas repoMascotas;
    private final RepositorioMedicamento repoMedicamento;     
    private final RepositorioVeterinarios repoVeterinarios;

    public ServicioTratamientoImpl(RepositorioTratamiento repoTratamiento,
                                   RepositorioMascotas repoMascotas,
                                   RepositorioMedicamento repoMedicamento,   
                                   RepositorioVeterinarios repoVeterinarios) {
        this.repoTratamiento = repoTratamiento;
        this.repoMascotas = repoMascotas;
        this.repoMedicamento = repoMedicamento;               
        this.repoVeterinarios = repoVeterinarios;
    }

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
        t.getMedicamentos().add(med);

        return repoTratamiento.save(t);
    }

    @Override
    public List<Tratamiento> listarPorMascota(Long mascotaId) {
        return repoTratamiento.findByMascotaId(mascotaId);
    }

    @Override
    public void eliminar(Long id) {
        repoTratamiento.deleteById(id);
    }
}
