import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../../service/usuario/usuario-service';
import { Usuario } from '../../model/usuario/usuario';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../service/login/login-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  standalone: false
})
export class LoginComponent implements OnInit{
    credenciales = {
    email: '',
    password: ''
  };

  activeTab: 'cliente' | 'veterinario' = 'cliente';
  
  mensajeError: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router, private loginService: LoginService) {}

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
    
    this.loginService.login(this.credenciales.email, this.credenciales.password).subscribe({
      next: (response) => {
        var respuestas=response.split(',');
        var user = respuestas[0];
        var id = respuestas[1];
        if (user === 'cliente') {
          this.usuarioService.getUsuarioByMail(this.credenciales.email).subscribe(data =>{
            const usuario = data;
          localStorage.setItem('id', id);
          localStorage.setItem('tipoUsuario', 'cliente');
          this.router.navigate(['/usuarios',usuario?.id]);
          });
        } else if (user === 'veterinario') {
          localStorage.setItem('id', id);
          localStorage.setItem('tipoUsuario', 'veterinario');
          this.router.navigate(['/usuarios']);
        }
        
      },
      error: (err) => {
        this.mensajeError = 'Correo o contraseña incorrectos.';
        console.error('Error de autenticación:', err);
      }
    }
    );
      
      
  }
}
