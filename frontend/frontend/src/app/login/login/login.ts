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
      console.log("CLICKKK");
      this.loginService.login(this.credenciales.email, this.credenciales.password).subscribe({
        next: (response) => {
          console.log(response);
          // The loginService already stores token, role, and id in localStorage
          const role = response.role;
          const id = response.id;
          
          if (role === 'usuario') {
            // Map 'usuario' to 'cliente' for frontend compatibility
            localStorage.setItem('tipoUsuario', 'cliente');
            this.usuarioService.getUsuarioByMail(this.credenciales.email).subscribe(data => {
              const usuario = data;
              this.router.navigate(['/usuarios', usuario?.id]);
            });
          } else if (role === 'veterinario') {
            this.router.navigate(['/usuarios']);
          } else if (role === 'admin') {
            this.router.navigate(['/veterinarios']);
          } else {
            this.mensajeError = 'Correo o contraseña incorrectos.';
          }
        },
        error: err => {
          console.error('Error de autenticación Revisado:', {
            status: err.status,
            url: err.url,
            message: err.message,
            error: err.error
          });
          this.mensajeError = 'Correo o contraseña incorrectos.';
        }
      });
    }
}