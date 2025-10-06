import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../../service/usuario/usuario-service';
import { Usuario } from '../../model/usuario/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent implements OnInit{
    credenciales = {
    email: '',
    password: ''
  };

  activeTab: 'cliente' | 'veterinario' = 'cliente';
  
  mensajeError: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

    ngOnInit(): void {
    this.mensajeError = '';
  }

  setActiveTab(tab: 'cliente' | 'veterinario'): void {
    this.activeTab = tab;
    this.mensajeError = ''; 
    this.credenciales.email = ''; 
    this.credenciales.password = '';
  }
  login() {
    const tipoUsuario = this.activeTab; 
    
   this.usuarioService.login(this.credenciales.email, this.credenciales.password).subscribe({
      next: (usuario) => {
        console.log('Login exitoso como:', tipoUsuario, usuario);
   
        localStorage.setItem('tipoUsuario', tipoUsuario);
        localStorage.setItem('usuario', JSON.stringify(usuario));
        
        if (tipoUsuario === 'veterinario') {
             this.router.navigate(['/veterinario-dashboard']);
        } else {
             this.router.navigate(['/mascotas']);
        }
      },
      error: (err) => {
        this.mensajeError = 'Correo o contraseña incorrectos.';
        console.error('Error de autenticación:', err);
      }
    });
  }
}
