import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VeterinarioService } from '../../../service/veterinario/veterinario-service';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, Subscription } from 'rxjs';
import { Mascota } from '../../../model/mascota/mascota';

@Component({
  selector: 'app-ver-mascotas-tratadas',
  templateUrl: './ver-mascotas-tratadas.html',
  styleUrls: ['./ver-mascotas-tratadas.css'],
  standalone: false
})
export class VerMascotasTratadas implements OnInit {

  private sub?: Subscription;
  mascotas: Mascota[] = [];
  public searchControl = new FormControl('');
  mascotasFiltradas: Mascota[] = [];
  vetId:number = 0;
  constructor(
    private route: ActivatedRoute,
    private veterinarioService: VeterinarioService,
    private router: Router,
  ) {}
  

  ngOnInit(): void {
    this.vetId = Number(this.route.snapshot.paramMap.get('id'));
    this.sub = this.searchControl.valueChanges
          .pipe(debounceTime(250), distinctUntilChanged())
          .subscribe(() => this.aplicarFiltro());
    this.cargarMascotas();
  }

  cargarMascotas(): void {
    this.veterinarioService.obtenerMascotasTratadas(this.vetId).subscribe({
      next: (data) => {
        this.mascotas = data;
        console.log('Mascotas tratadas:', data);
        this.mascotasFiltradas = [...this.mascotas];
      },
      error: (err) => {
        console.error('Error al cargar mascotas tratadas', err);
      }
    });
  }

  private aplicarFiltro(): void {
    const q = this.normalizar(this.searchControl.value ? this.searchControl.value : '');
    if (!q) {
      this.mascotasFiltradas = [...this.mascotas];
      return;
    }
    this.mascotasFiltradas = this.mascotas.filter(m => {
      const nombre = this.normalizar(m.nombre);
      return nombre.includes(q);
    });
  }

  normalizar(s: string): string {
    return s.trim().toLowerCase();
  }
}
