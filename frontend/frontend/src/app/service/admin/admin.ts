import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Admin {
  private base = 'http://localhost:8080/admin/dashboard';

  constructor(private http: HttpClient) {}

  getResumen(): Observable<Array<{ mascotasActivas: number; tratamientosActivos: number }>> {
    return this.http
    .get<{ mascotasActivas: number; tratamientosActivos: number }>(`${this.base}/resumen`)
    .pipe(map(res => [res]));
  }

  getTopMeds(): Observable<Array<{ nombre: string; total: number }>> {
    return this.http.get<Array<{ nombre: string; total: number }>>(`${this.base}/top-meds`);
  }

  getStock(): Observable<Array<{ nombre: string; stock: number }>> {
    return this.http.get<Array<{ nombre: string; stock: number }>>(`${this.base}/stock`);
  }

  getGanancias(): Observable<{ medicinasVendidas: number; unidadesVendidas: number; ingresos: number; ganancia: number }> {
    return this.http.get<{ medicinasVendidas: number; unidadesVendidas: number; ingresos: number; ganancia: number }>(
      `${this.base}/ganancias`
    );
  }
}
