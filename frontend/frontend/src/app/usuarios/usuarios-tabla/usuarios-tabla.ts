import { Component } from '@angular/core';
import { UsuarioService } from '../../service/usuario/usuario-service';
import { Usuario } from '../../model/usuario/usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios-tabla',
  standalone: false,
  templateUrl: './usuarios-tabla.html',
  styleUrl: './usuarios-tabla.css'
})
export class UsuariosTabla {
  usuarios: Usuario[] = [];
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.getUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este usuario?')) {
      this.usuarios.splice(id-1,1);
      this.usuarioService.deleteUsuario(id).subscribe({
        next: () => console.log("Hola"),
        complete: ()=> this.cargarUsuarios()
      })
    }
  }

}
