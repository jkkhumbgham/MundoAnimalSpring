import { Component, OnInit } from '@angular/core';
import { Mascota } from '../../../model/mascota/mascota';
import { MascotasService } from '../../../service/mascota/mascota-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-mascota-detalle',
  standalone: false,
  templateUrl: './mascota-detalle.html',
  styleUrl: './mascota-detalle.css'
})
export class MascotaDetalle implements OnInit { 
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
  edadMascota(): number {
  return this.mascotasService.getEdad(this.mascota.fechaNacimiento!);
}
}
