import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

// Si tienes clase con constructor, importa tu modelo:
import { Veterinario } from '../../model/veterinario/veterinario';

const BASE = 'http://localhost:8080/api/veterinarios';

@Injectable({ providedIn: 'root' })
export class VeterinarioService {
  constructor(private http: HttpClient) {}


  listar(): Observable<Veterinario[]> {
    return this.http.get<any[]>(BASE).pipe(
      // Mapea JSON -> instancia de tu clase (si tu clase tiene otro constructor, ajusta aquí)
      map(items => items.map(o => {
        // Variante segura: crea la instancia con lo mínimo y completa campos opcionales
        const v = new Veterinario(o.id, o.nombre, o.email, o.password, o.cedula, o.especialidad, o.foto);  // <-- si tu ctor exige más args, cámbialo
        if ('email' in o)        (v as any).email = o.email;
        if ('cedula' in o)       (v as any).cedula = o.cedula;
        if ('especialidad' in o) (v as any).especialidad = o.especialidad;
        if ('foto' in o)         (v as any).foto = o.foto;
        return v;
      }))
    );
  }

  obtener(id: number): Observable<Veterinario> {
    return this.http.get<any>(`${BASE}/${id}`).pipe(
      map(o => {
        const v = new Veterinario(o.id, o.nombre, o.email, o.password, o.cedula, o.especialidad, o.foto);
        if ('email' in o)        (v as any).email = o.email;
        if ('cedula' in o)       (v as any).cedula = o.cedula;
        if ('especialidad' in o) (v as any).especialidad = o.especialidad;
        if ('foto' in o)         (v as any).foto = o.foto;
        return v;
      })
    );
  }
}