import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MascotasService } from '../../service/mascota/mascota-service';
import { Mascota } from '../../model/mascota/mascota';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-mascota-formulario',
  standalone: false,
  templateUrl: './mascota-formulario.component.html',
  styleUrl: './mascota-formulario.component.css',
  encapsulation: ViewEncapsulation.None
})

export class MascotaFormularioComponent implements OnInit { 
  mascota: Mascota = new Mascota;

  constructor (private mascotasService: MascotasService,
               private route: ActivatedRoute,
               private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.mascotasService.getMascotaById(+id).subscribe(mascota => {
        if (mascota) {
          this.mascota = mascota;
        } else {
          this.router.navigate(['/mascotas']);
        }
      });
    }
  }

  guardarMascota(): void {
    if (this.mascota.id) {
      this.mascotasService.updateMascota(this.mascota).subscribe(() => {
        this.mascotasService.notificarMascotaAgregada();
        this.router.navigate(['/mascotas']);
      });
    } else {
      this.mascotasService.addMascota(this.mascota).subscribe(() => {
      this.mascotasService.notificarMascotaAgregada();
      this.router.navigate(['/mascotas']);
      });
    }
  }
  

}