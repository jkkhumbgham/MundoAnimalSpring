import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Admin } from '../../service/admin/admin';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
})
export class Dashboard {
  resumen: Array<{ mascotasActivas: number; tratamientosActivos: number }> = [];
  topMeds: Array<{ nombre: string; total: number }> = [];
  stock: Array<{ nombre: string; stock: number }> = [];
  ventas = { medicinasVendidas: 0, unidadesVendidas: 0, ingresos: 0, ganancia: 0 };
  pagina = 1;
  totalPaginas = 5;

  constructor(private service: Admin) {}

   ngOnInit(): void {
    this.cargarResumen();

    this.cargarTopMeds();
    this.cargarStock();
    this.cargarGanancias();
  }

  cargarResumen(): void {
    this.service.getResumen().subscribe({
      next: (res) => this.resumen = res,
      error: (err) => console.error('Error al cargar resumen', err)
    });
  }

  cargarTopMeds(): void {
    this.service.getTopMeds().subscribe({
      next: (lista) => this.topMeds = lista || [],
      error: (err) => console.error('Error al cargar top meds', err)
    });
  }

  cargarStock(): void {
    this.service.getStock().subscribe({
      next: (lista) => {this.stock = lista || []
        this.pagina = 1;
      },
      error: (err) => console.error('Error al cargar stock', err)
    });
  }

  cargarGanancias(): void {
    this.service.getGanancias().subscribe({
      next: (res) => this.ventas = res,
      error: (err) => console.error('Error al cargar ganancias', err)
    });
  }

  Top(): number {
    return this.topMeds.length ? Math.max(...this.topMeds.map(x => x.total || 0)) : 1;
  }
   get Paginas(): number {
    const n = this.stock?.length || 0;
    return Math.max(1, Math.ceil(n / this.totalPaginas));
  }

  get Pagina() {
    const data = this.stock || [];
    const start = (this.pagina - 1) * this.totalPaginas;
    return data.slice(start, start + this.totalPaginas);
  }

  siguiente() {
    if (this.pagina < this.Paginas) this.pagina++;
  }

  anterior() {
    if (this.pagina > 1) this.pagina--;
  }
}
