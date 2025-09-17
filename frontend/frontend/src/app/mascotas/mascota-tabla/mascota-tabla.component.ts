import { Component, OnInit } from '@angular/core';
import { Mascota } from '../mascota';
import { MascotasService } from '../mascotas.service';

@Component({
  selector: 'app-mascota-tabla',
  standalone: false,
  templateUrl: './mascota-tabla.component.html',
  styleUrl: './mascota-tabla.component.css'
})
export class MascotaTablaComponent implements OnInit {
  mascotas: Mascota[] = [];

  constructor(private mascotasService: MascotasService) { }

  ngOnInit(): void {
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

  onDelete(id: number | undefined): void {
    if (id !== undefined) {
      this.mascotasService.deleteMascota(id).subscribe(() => {
        this.getAllMascotas();
      });
    }
  }
}