import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

// Si tienes clase con constructor, importa tu modelo:
import { Veterinario } from '../../model/veterinario/veterinario';
import { Router } from '@angular/router';

const BASE = 'http://localhost:8080/veterinarios';

@Injectable({ providedIn: 'root' })
export class VeterinarioService {
  constructor(private http: HttpClient,private router: Router) {}


  listar(): Observable<Veterinario[]> {
    return this.http.get<any[]>(BASE).pipe(
      map(items => items.map(o => {
      
        const v = new Veterinario(o.id, o.nombre, o.email, o.password, o.cedula, o.especialidad, o.foto, o.estado, o.estadoOriginal);
        if ('email' in o)        (v as any).email = o.email;
        if ('cedula' in o)       (v as any).cedula = o.cedula;
        if ('especialidad' in o) (v as any).especialidad = o.especialidad;
        if ('foto' in o)         (v as any).foto = o.foto;
        if ('estado' in o)       (v as any).estado = o.estado;
        if ('estadoOriginal' in o)       (v as any).estadoOriginal = o.estadoOriginal;
        return v;
      }))
    );
  }

  obtener(id: number): Observable<Veterinario> {
    return this.http.get<any>(`${BASE}/find/${id}`).pipe(
      map(o => {
        const v = new Veterinario(o.id, o.nombre, o.email, o.password, o.cedula, o.especialidad, o.foto, o.estado, o.estadoOriginal);
        if ('email' in o)        (v as any).email = o.email;
        if ('cedula' in o)       (v as any).cedula = o.cedula;
        if ('especialidad' in o) (v as any).especialidad = o.especialidad;
        if ('foto' in o)         (v as any).foto = o.foto;
        if ('estado' in o)       (v as any).estado = o.estado;
        if ('estadoOriginal' in o)       (v as any).estadoOriginal = o.estadoOriginal;
        return v;
      })
    );
  }


      addVeterinario(veterinario: Veterinario) {
        console.log(veterinario);
        this.http.post<Veterinario>(`http://localhost:8080/veterinarios/agregar`, veterinario).subscribe({
          next:()=>console.log("Veterinario guardado en backend"),
          complete: ()=> this.router.navigate(['/veterinarios'])
        });
      }

      deleteVeterinario(id: number): Observable<any> {
        console.log(id);
        return this.http.delete(`http://localhost:8080/veterinarios/delete/${id}`);
      }

      updateVeterinario(veterinario: Veterinario) {
          this.http.put<Veterinario>(`http://localhost:8080/veterinarios/editar/${veterinario.id}`, veterinario).subscribe({
            next:()=>console.log("Veterinario actualizado en backend"),
            complete: ()=> this.router.navigate(['/veterinarios'])
          });
        }

     softDeleteVeterinario(veterinario: Veterinario) {
      if (veterinario.estado!.toLowerCase() === 'inactivo') {
        veterinario.estado = veterinario.estadoOriginal !== "" ? String(veterinario.estadoOriginal) : "";
      } else {
        veterinario.estadoOriginal = veterinario.estado;
        veterinario.estado = 'inactivo';
      }
      console.log("SOF",veterinario);
      this.updateVeterinario(veterinario);
    }
}