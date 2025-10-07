
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { Usuario } from '../../model/usuario/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/usuarios'; 

  constructor(private http: HttpClient) {}

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>('http://localhost:8080/usuarios');
  }

  getUsuarioById(id: number): Observable<Usuario | undefined> {
      const usuario = this.http.get<Usuario>(`http://localhost:8080/usuarios/find/${id}`);
      return usuario;
    }
  
    addUsuario(usuario: Usuario) {
      console.log(usuario);
      this.http.post<Usuario>(`http://localhost:8080/usuarios/agregar`, usuario).subscribe();
    }
  
    deleteUsuario(id: number) {
      console.log(id);
      this.http.delete(`http://localhost:8080/usuarios/delete/${id}`).subscribe();
      
    }

    updateUsuario(usuario: Usuario) {
        this.http.put<Usuario>(`http://localhost:8080/usuarios/editar/${usuario.id}`, usuario).subscribe();
      }
    

  
  
  login(email: string, password: string): Observable<Usuario> {
    const datosLogin = { email: email, contraseña: password }; 
  
    return this.http.post<Usuario>(`${this.apiUrl}/login`, datosLogin); 
  }
}