import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Medicamento } from '../../model/medicamento/medicamento';


const BASE = 'http://localhost:8080/medicamentos';

@Injectable({ providedIn: 'root' })
export class MedicamentoService {
  constructor(private http: HttpClient) {}

  /** Lista todos los medicamentos y mapea JSON -> instancias de la clase Medicamento */
  listar(): Observable<Medicamento[]> {
    return this.http.get<any[]>(BASE).pipe(
      map(items => items.map(o => {
        const m = new Medicamento(o.id, o.nombre, o.precio_venta, o.unidades,o.precio_compra,o.unidades_vendidas);
        if (o.tratamientos) m.tratamientos = o.tratamientos; // opcional, si el backend lo incluye
        return m;
      }))
    );
  }

  /** (Opcional) Obtener un medicamento por id con mapeo a clase */
  obtener(id: number): Observable<Medicamento> {
    return this.http.get<any>(`${BASE}/${id}`).pipe(
      map(o => {
        const m = new Medicamento(o.id, o.nombre, o.precio_venta, o.unidades,o.precio_compra,o.unidades_vendidas);
        if (o.tratamientos) m.tratamientos = o.tratamientos;
        return m;
      })
    );
  }

}
