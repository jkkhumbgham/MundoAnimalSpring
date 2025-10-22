import { Component } from '@angular/core';
import { Veterinario } from '../../model/veterinario/veterinario';
import { VeterinarioService } from '../../service/veterinario/veterinario-service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-veterinarios-formulario',
  standalone: false,
  templateUrl: './veterinarios-formulario.html',
  styleUrl: './veterinarios-formulario.css'
})
export class VeterinariosFormulario {
  usuario: Veterinario = Veterinario.crearVacio();
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';
  id: string = localStorage.getItem('id') || '';
  constructor(private veterinarioService: VeterinarioService,
              private router: Router,
            private route: ActivatedRoute) {}


  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.veterinarioService.obtener(+id).subscribe(veterinario => {
        if (veterinario) {
          this.usuario = veterinario;
        } else {
          this.router.navigate(['/usuarios']);
        }
      });
    }
  }

  guardarUsuario(): void {
    if (this.usuario.id !== 0) {
      this.veterinarioService.updateVeterinario(this.usuario);
    } else {
      this.usuario.estado = 'Activo';
      this.veterinarioService.addVeterinario(this.usuario);
    }
  }
}
