
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { Mascota } from '../../model/mascota/mascota';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MascotasService {
  // Datos quemados para simular la base de datos
 


  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) { }

  getAllMascotas(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>('http://localhost:8080/mascotas');
  }

  getMascotaById(id: number): Observable<Mascota | undefined> {
    return this.http.get<Mascota>(`http://localhost:8080/mascotas/find/${id}`);
  }

  addMascota(mascota: Mascota) {
    
   this.http.post<Mascota>(`http://localhost:8080/mascotas`, mascota).subscribe({
    next:()=> console.log("Mascota guardada en backend"),
    complete: ()=> this.router.navigate(['/usuarios', mascota.dueno?.id])
  });
  }

  deleteMascota(id: number) {
    this.http.delete(`http://localhost:8080/mascotas/delete/${id}`);
    
  }

   softDeleteMascota(mascota: Mascota) {
    if (mascota.estado!.toLowerCase() === 'inactivo') {
      mascota.estado = mascota.estadoOriginal !== "" ? String(mascota.estadoOriginal) : "";
    } else {
      mascota.estadoOriginal = mascota.estado;
      mascota.estado = 'inactivo';
    }
    console.log("SOF",mascota);
    this.updateMascota(mascota);
  }

  updateMascota(mascota: Mascota) {
    
    console.log("🔍 Mascota ANTES del envío:", mascota);
console.log("🧪 JSON.stringify(mascota):", JSON.stringify(mascota));
    this.http.put<Mascota>(`http://localhost:8080/mascotas/editar`, mascota).subscribe({
      next: (res) => console.log("Mascota actualizada en backend:", res),
      error: (err) => console.error("Error actualizando en backend:", err),
      complete: ()=> this.router.navigate(['/usuarios', mascota.dueno?.id])
    });
    
    
  }


  getMascotasUser(id: number): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(`http://localhost:8080/mascotas/dueno/${id}`);
  }

  getEdad(fechaNacimiento: Date | string): number {
  if (!fechaNacimiento) return 0;

  const f = (fechaNacimiento instanceof Date)
    ? fechaNacimiento
    : new Date(fechaNacimiento);

  const hoy = new Date();
  let edad = hoy.getFullYear() - f.getFullYear();
  const mes = hoy.getMonth() - f.getMonth();

  if (mes < 0 || (mes === 0 && hoy.getDate() < f.getDate())) {
    edad--;
  }

  return edad;
}

}
