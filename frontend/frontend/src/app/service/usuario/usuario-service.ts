
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { Usuario } from '../../model/usuario/usuario';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/usuarios'; 

  constructor(private http: HttpClient, private router: Router) {}

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>('http://localhost:8080/usuarios');
  }
  getUsuarioByMail(email: string): Observable<Usuario | undefined> {
    return this.http.post<Usuario>(`http://localhost:8080/usuarios/mail`, email);
  }

  getUsuarioById(id: number): Observable<Usuario | undefined> {
      const usuario = this.http.get<Usuario>(`http://localhost:8080/usuarios/find/${id}`);
      return usuario;
    }
  
    addUsuario(usuario: Usuario) {
      console.log(usuario);
      this.http.post<Usuario>(`http://localhost:8080/usuarios/agregar`, usuario).subscribe({
        next:()=>console.log("Usuario guardado en backend"),
        complete: ()=> this.router.navigate(['/usuarios'])
      });
    }
  
    deleteUsuario(id: number): Observable<any> {
      console.log(id);
      return this.http.delete(`http://localhost:8080/usuarios/delete/${id}`);
    }

    updateUsuario(usuario: Usuario) {
        this.http.put<Usuario>(`http://localhost:8080/usuarios/editar/${usuario.id}`, usuario).subscribe({
          next:()=>console.log("Usuario actualizado en backend"),
          complete: ()=> this.router.navigate(['/usuarios'])
        });
      }
    

  
  
  login(email: string, password: string): Observable<Usuario> {
    const datosLogin = { email: email, contraseña: password }; 
  
    return this.http.post<Usuario>(`${this.apiUrl}/login`, datosLogin); 
  }
}