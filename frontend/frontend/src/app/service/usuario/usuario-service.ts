
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../../model/usuario/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/usuarios'; 

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<Usuario> {
    const datosLogin = { email: email, contraseña: password }; 
  
    return this.http.post<Usuario>(`${this.apiUrl}/login`, datosLogin); 
  }
}