import { Component } from '@angular/core';
import { Usuario } from '../../model/usuario/usuario'; 
import { UsuarioService } from '../../service/usuario/usuario-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuarios-formulario',
  standalone: false,
  templateUrl: './usuarios-formulario.html',
  styleUrl: './usuarios-formulario.css'
})
export class UsuariosFormulario {
  usuario: Usuario = Usuario.crearVacio();
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';
  constructor(private usuarioService: UsuarioService,
              private router: Router,
            private route: ActivatedRoute) {}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.usuarioService.getUsuarioById(+id).subscribe(usuario => {
        if (usuario) {
          this.usuario = usuario;
        } else {
          this.router.navigate(['/usuarios']);
        }
      });
    }
  }

  guardarUsuario(): void {
    if (this.usuario.id !== 0) {
      this.usuarioService.updateUsuario(this.usuario)
    } else {
      this.usuarioService.addUsuario(this.usuario)
    }
  }

}
