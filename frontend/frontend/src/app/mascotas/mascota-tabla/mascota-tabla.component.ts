import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Mascota } from '../../model/mascota/mascota';
import { MascotasService } from '../../service/mascota/mascota-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mascota-tabla',
  standalone: false,
  templateUrl: './mascota-tabla.component.html',
  styleUrl: './mascota-tabla.component.css',
  encapsulation: ViewEncapsulation.None
})
export class MascotaTablaComponent implements OnInit {
  mascotas: Mascota[] = [];
  constructor(private mascotasService: MascotasService, private route: ActivatedRoute) { }
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getAllMascotas();
    })
  }

  getAllMascotas(): void {
    this.mascotasService.getAllMascotas()
      .subscribe(
        (data: Mascota[]) => {
          this.mascotas = data;
        },
        (error) => {
          console.error('Error getting mascotas', error);
        }
      );
  }
  onSoftDelete(mascota: Mascota): void {
    if(mascota !== undefined){
  this.mascotasService.softDeleteMascota(mascota);
}
}
  onDelete(id: number | undefined): void {
    if (id !== undefined) {
      this.mascotas.splice(id,1);
      this.mascotasService.deleteMascota(id);
    }
  }
}