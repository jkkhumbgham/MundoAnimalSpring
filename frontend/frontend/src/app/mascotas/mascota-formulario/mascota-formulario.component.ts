import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MascotasService } from '../../service/mascota/mascota-service';
import { Mascota } from '../../model/mascota/mascota';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-mascota-formulario',
  standalone: false,
  templateUrl: './mascota-formulario.component.html',
  styleUrl: './mascota-formulario.component.css',
  encapsulation: ViewEncapsulation.None
})

export class MascotaFormularioComponent implements OnInit { 
  mascota: Mascota = Mascota.crearVacia();

  constructor (private mascotasService: MascotasService,
               private route: ActivatedRoute,
               private router: Router
  ) { }

  ngOnInit(): void {
  const id = this.route.snapshot.paramMap.get('id');
  console.log(id);
  if (id) {
    this.mascotasService.getMascotaById(+id).subscribe(mascota => {
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