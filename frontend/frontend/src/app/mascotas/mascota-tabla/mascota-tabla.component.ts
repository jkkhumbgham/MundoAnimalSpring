import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Mascota } from '../../model/mascota/mascota';
import { MascotasService } from '../../service/mascota/mascota-service';

@Component({
  selector: 'app-mascota-tabla',
  standalone: false,
  templateUrl: './mascota-tabla.component.html',
  styleUrl: './mascota-tabla.component.css',
  encapsulation: ViewEncapsulation.None
})
export class MascotaTablaComponent implements OnInit {
  mascotas: Mascota[] = [];
  constructor(private mascotasService: MascotasService) { }
  rol: string = '';
  ngOnInit(): void {
    this.rol = localStorage.getItem('rol') ?? '';
    this.getAllMascotas();
    this.mascotasService.mascotaAgregada$.subscribe(() => {
      this.getAllMascotas();
    });
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
  this.mascotasService.softDeleteMascota(mascota).subscribe(() => {
        this.getAllMascotas();
  });
}
}
  onDelete(id: number | undefined): void {
    if (id !== undefined) {
      this.mascotasService.deleteMascota(id).subscribe(() => {
        this.getAllMascotas();
      });
    }
  }
}