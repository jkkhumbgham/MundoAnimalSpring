package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidades.Mascota;
import com.example.demo.servicio.ServicioMascotaImpl;



@Controller
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired
    ServicioMascotaImpl servicioMascotas;
    //mostrar una mascota
    /*
    @GetMapping("/{id}")
public String verDetalleMascota(@PathVariable int id, Model model) {
    Mascota mascota = servicioMascotas.getMascotaById(id);
    if(mascota!= null){
        model.addAttribute("mascota", mascota);
        return "mascota-detalle"; // nombre del template
    }else {
        return "redirect:/mascotas";
    }
}
     */
    @GetMapping("/{id}")
    public String verDetalleMascota(@PathVariable("id") int id, Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascota-detalle"; 
        } else {
            return "redirect:/mascotas"; 
        }
    }

     
    //http://localhost:8080/Mascotas
    /*@GetMapping("/Mascotas")
    public String MostrarMascotas(Model model){
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas";
    } */

    //http://localhost:8080/Mascotas-Tabla
    @GetMapping("")
    public String MostrarMascotasTabla(Model model){
        model.addAttribute("mascotas", servicioMascotas.getAllMascotas());
        return "mascotas-tabla";
    }
    /* 
    @GetMapping("/delete/{id}")
    public String eliminarMascota(@PathVariable Integer id){
        servicioMascotas.deleteMascota(id);
        return "redirect:/mascotas";

    }
*/
    //Añadir mascota en formulario
   /*  @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {

   
        model.addAttribute("mascota", new Mascota(0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0));
        return "nuevo_paciente";
    }
*/

@GetMapping("/agregar")
public String agregarMascota(@RequestParam("duenoId") int duenoId, Model model) {
    Mascota mascota = new Mascota();
    mascota.setDueñoid(duenoId); // asigna automáticamente el dueño
    model.addAttribute("mascota", mascota);
    return "nuevo_paciente";
}

    //Guardar nueva mascota
    @PostMapping("/add")
    public String agregarMascota(@ModelAttribute("mascota")Mascota mascota) {
        //TODO: process POST request
        servicioMascotas.saveMascota(mascota);
        return "redirect:/mascotas";
    }

    //Editar mascota
    @GetMapping("/update/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Mascota mascota = servicioMascotas.getMascotaById(id);
        model.addAttribute("mascota", mascota);
        return "nuevo_paciente"; // usas la misma vista del registro
    }

    @PostMapping("/update")
    public String actualizarMascota(@ModelAttribute("mascota") Mascota mascota) {
        servicioMascotas.saveMascota(mascota);
        return "redirect:/mascotas";
}

@PostMapping("/delete/{id}")
public String eliminarMascotas(@PathVariable Integer id){
    servicioMascotas.deleteMascota(id);
    return "redirect:/mascotas";
}


}
