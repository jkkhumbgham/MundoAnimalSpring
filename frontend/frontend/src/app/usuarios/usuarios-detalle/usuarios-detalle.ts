import { Component } from '@angular/core';
import { Mascota } from '../../model/mascota/mascota';
import { MascotasService } from '../../service/mascota/mascota-service';
import { Usuario } from '../../model/usuario/usuario';
import { UsuarioService } from '../../service/usuario/usuario-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-usuarios-detalle',
  standalone: false,
  templateUrl: './usuarios-detalle.html',
  styleUrl: './usuarios-detalle.css'
})
export class UsuariosDetalle {
   mascotas: Mascota[] = [];
   usuario: Usuario = Usuario.crearVacio();
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';

  constructor(private mascotasService: MascotasService,
              private route: ActivatedRoute,
              private router: Router,
              private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.cargarMascotas();
    this.cargarUsuario();
  }

  cargarUsuario(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.usuarioService.getUsuarioById(+id!).subscribe(data => {
      this.usuario = data!;
    })
  }

  cargarMascotas(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.mascotasService.getMascotasUser(+id!).subscribe(data => {
      this.mascotas = data;
    });
  }

  eliminar(mascota: Mascota): void {
    this.mascotasService.softDeleteMascota(mascota);
  }

  agregarMascota(): void {
    this.router.navigate(['/mascotas/new', this.usuario.id])
  }
}
