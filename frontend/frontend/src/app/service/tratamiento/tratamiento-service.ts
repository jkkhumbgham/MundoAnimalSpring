import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Tratamiento } from '../../model/tratamiento/tratamiento';
import { Medicamento } from '../../model/medicamento/medicamento';

@Injectable({ providedIn: 'root' })
export class TratamientoService {
  private base = 'http://localhost:8080'; 

  constructor(private http: HttpClient) {}

  porMascota(mascotaId: number): Observable<Tratamiento[]> {
    return this.http.get<any[]>(`${this.base}/mascotas/${mascotaId}/tratamientos`).pipe(
      map(arr => arr.map(o => this.toTratamiento(o)))
    );
  }

  crear(mascotaId: number, veterinarioId: number, medicamentoId: number, nombre: string): Observable<Tratamiento> {
    const url = `${this.base}/mascotas/${mascotaId}/tratamientos?medicamentoId=${medicamentoId}&veterinarioId=${veterinarioId}`;
    return this.http.post<any>(url, { nombre }).pipe(map(o => this.toTratamiento(o)));
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/tratamientos/${id}`);
  }

 
  private toTratamiento(o: any): Tratamiento {
    const t = new Tratamiento(o.id, o.nombre);
    if (Array.isArray(o.medicamentos)) {
      t.medicamentos = o.medicamentos.map((m: any) => new Medicamento(m.id, m.nombre, m.precio_venta, m.unidades, m.precio_compra, m.unidades_vendidas));
    }
    t.veterinario = o.veterinario; 
    t.mascota = o.mascota;
    return t;
  }
}
