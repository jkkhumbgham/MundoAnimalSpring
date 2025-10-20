import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VeterinarioService } from '../../../service/veterinario/veterinario-service';

@Component({
  selector: 'app-ver-mascotas-tratadas',
  templateUrl: './ver-mascotas-tratadas.html',
  styleUrls: ['./ver-mascotas-tratadas.css']
})
export class VerMascotasTratadas implements OnInit {

  idVeterinario!: number;
  mascotas: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private veterinarioService: VeterinarioService
  ) {}

  ngOnInit(): void {
    this.idVeterinario = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarMascotas();
  }

  cargarMascotas(): void {
    this.veterinarioService.obtenerMascotasTratadas(this.idVeterinario).subscribe({
      next: (data) => {
        this.mascotas = data;
        console.log('Mascotas tratadas:', data);
      },
      error: (err) => {
        console.error('Error al cargar mascotas tratadas', err);
      }
    });
  }

}
