import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MascotasService } from '../../service/mascota/mascota-service';
import { Mascota } from '../../model/mascota/mascota';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../../model/usuario/usuario';
import { UsuarioService } from '../../service/usuario/usuario-service';


@Component({
  selector: 'app-mascota-formulario',
  standalone: false,
  templateUrl: './mascota-formulario.component.html',
  styleUrl: './mascota-formulario.component.css',
  encapsulation: ViewEncapsulation.None
})

export class MascotaFormularioComponent implements OnInit { 
  mascota: Mascota = Mascota.crearVacia();
  usuario: Usuario = Usuario.crearVacio();
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';
  id: string = localStorage.getItem('id') || '';
  constructor (private mascotasService: MascotasService,
               private route: ActivatedRoute,
               private router: Router,
               private usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
  const id = this.route.snapshot.paramMap.get('id');
  console.log(id);
  
  if (this.router.url.includes('editar')) {
    this.mascotasService.getMascotaById(+id!).subscribe(mascota => {
      if (mascota) {
        if (typeof mascota.fechaNacimiento === 'string') {
          mascota.fechaNacimiento = new Date(mascota.fechaNacimiento);
        }
        if (typeof mascota.ultimavisita === 'string') {
          mascota.ultimavisita = new Date(mascota.ultimavisita);
        }

        this.mascota = mascota;
      } else {
        this.router.navigate(['/mascotas']);
      }
    });
  }else if(this.router.url.includes('new')){
    this.usuarioService.getUsuarioById(+id!).subscribe(data => {
      this.mascota.dueno = data!;
      console.log(this.mascota.dueno);
    })
  }
}


  guardarMascota(): void {
    if (this.mascota.id) {
      
      this.mascotasService.updateMascota(this.mascota);

      console.log('Mascota actualizada:', this.mascota);
    } else {
      this.mascotasService.addMascota(this.mascota);
      console.log('Mascota creada:', this.mascota);
    }
  }
  agregarVacuna() {
    this.mascota.vacunas!.push('');
  }

  eliminarVacuna(index: number) {
    this.mascota.vacunas!.splice(index, 1);
  }

  agregarAlergia() {
    this.mascota.alergias!.push('');
  }

  eliminarAlergia(index: number) {
    this.mascota.alergias!.splice(index, 1);
  }


}