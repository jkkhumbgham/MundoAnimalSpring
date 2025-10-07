import { Component, OnInit } from '@angular/core';
import { Mascota } from '../../../model/mascota/mascota';
import { MascotasService } from '../../../service/mascota/mascota-service';
import { ActivatedRoute, Router } from '@angular/router';

import { TratamientoService } from '../../../service/tratamiento/tratamiento-service';
import { Tratamiento } from '../../../model/tratamiento/tratamiento';
import { MedicamentoService } from '../../../service/medicamento/medicamento-service';
import { VeterinarioService } from '../../../service/veterinario/veterinario-service';
import { Medicamento } from '../../../model/medicamento/medicamento';

@Component({
  selector: 'app-mascota-detalle',
  standalone: false,
  templateUrl: './mascota-detalle.html',
  styleUrl: './mascota-detalle.css'
})
export class MascotaDetalle implements OnInit {
  // ---- Mascota ----
  mascota: Mascota = Mascota.crearVacia();

  // ---- Tratamientos (UI) ----
  tratamientos: Tratamiento[] = [];
  medicamentos: Medicamento[] = [];
  veterinarios: any[] = [];

  nombreTrat = '';
  medicamentoId = 0;
  veterinarioId = 0;

  guardando = false;
  errorMsg = '';
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';

  constructor(
    private mascotasService: MascotasService,
    private route: ActivatedRoute,
    private router: Router,
    private tratSrv: TratamientoService,
    private medSrv: MedicamentoService,
    private vetSrv: VeterinarioService,
  ) {}

  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.mascotasService.getMascotaById(+id).subscribe(mascota => {
        if (mascota) {
          this.mascota = mascota;
          console.log('Mascota cargada en detalle:', mascota);
        } else {

          this.router.navigate(['/mascotas']);
          return;
        }
        this.mascota = mascota;
        // cargar tablas auxiliares y tratamientos cuando ya tenemos la mascota
        this.cargarAuxiliares();
        this.cargarTratamientos();
      });
    }
  }

  // ---- Helpers Mascota ----
  edadMascota(): number {
    return this.mascotasService.getEdad(this.mascota.fechaNacimiento!);
  }

  // ---- Tratamientos ----
  cargarTratamientos(): void {
    if (!this.mascota.id) return;
    this.tratSrv.porMascota(this.mascota.id).subscribe({
      next: d => this.tratamientos = d,
      error: e => console.error('Error al cargar tratamientos', e)
    });
  }

  cargarAuxiliares(): void {
    this.medSrv.listar().subscribe({
      next: d => this.medicamentos = d,
      error: e => console.error('Error cargando medicamentos', e)
    });
    this.vetSrv.listar().subscribe({
      next: d => this.veterinarios = d,
      error: e => console.error('Error cargando veterinarios', e)
    });
  }

  crearTratamiento(): void {
    this.errorMsg = '';
    if (!this.nombreTrat || !this.medicamentoId || !this.veterinarioId) {
      this.errorMsg = 'Completa nombre, medicamento y veterinario.';
      return;
    }
    if (!this.mascota.id) return;

    this.guardando = true;
    this.tratSrv.crear(this.mascota.id, this.veterinarioId, this.medicamentoId, this.nombreTrat)
      .subscribe({
        next: _ => {
          this.guardando = false;
          this.nombreTrat = '';
          this.medicamentoId = 0;
          this.veterinarioId = 0;
          this.cargarTratamientos();
        },
        error: e => {
          this.guardando = false;
          this.errorMsg = e?.error?.message ?? 'Error al guardar';
        }
      });
  }

  eliminarTratamiento(id: number): void {
    if (!confirm('¿Eliminar tratamiento?')) return;
    this.tratSrv.eliminar(id).subscribe(() => this.cargarTratamientos());
  }
}
