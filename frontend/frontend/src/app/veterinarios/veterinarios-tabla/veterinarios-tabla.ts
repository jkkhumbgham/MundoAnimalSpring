import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Veterinario } from '../../model/veterinario/veterinario';
import { VeterinarioService } from '../../service/veterinario/veterinario-service';
import { FormControl } from '@angular/forms';
import { Subscription } from 'rxjs/internal/Subscription';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs';

@Component({
  selector: 'app-veterinarios-tabla',
  standalone: false,
  templateUrl: './veterinarios-tabla.html',
  styleUrl: './veterinarios-tabla.css'
})
export class VeterinariosTabla {
  usuarios: Veterinario[] = [];
  rol: string = localStorage.getItem('tipoUsuario') || 'veterinario';
  id: string = localStorage.getItem('id') || '';
   usuariosFiltrados: Veterinario[] = [];
   public searchControl = new FormControl('');
   private sub?: Subscription;

  constructor(private veterinarioService: VeterinarioService, private router: Router) {}

  ngOnInit(): void {
    this.sub = this.searchControl.valueChanges
      .pipe(debounceTime(250), distinctUntilChanged())
      .subscribe(() => this.aplicarFiltro());
    this.cargarUsuarios();
    
  }

  cargarUsuarios() {
    console.log(this.usuarios);
    this.veterinarioService.listar().subscribe(data => {
      this.usuarios = data;
      console.log(this.usuarios);
      this.usuariosFiltrados = [...this.usuarios];
    });
    
     
  }


  private aplicarFiltro(): void {
    const q = this.normalizar(this.searchControl.value ? this.searchControl.value : '');
    if (!q) {
      this.usuariosFiltrados = [...this.usuarios];
      return;
    }
    this.usuariosFiltrados = this.usuarios.filter(u => {
      const nombre = this.normalizar(u.nombre);
      const email  = this.normalizar(u.email);
      return nombre.includes(q) || email.includes(q);
    });
  }

  normalizar(s: string): string {
    return s.trim().toLowerCase();
  }

  eliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este veterinario?')) {
      this.usuarios.splice(id-1,1);
      console.log(id);
      this.veterinarioService.deleteVeterinario(id).subscribe({
        next: () => console.log("Hola"),
        complete: ()=> this.cargarUsuarios()
      })
    }
  }

  softEliminar(id: number) {
    if (confirm('¿Seguro que deseas eliminar este veterinario?')) {
      const veterinario = this.usuarios.find(v => v.id === id);
      if (veterinario) {
        this.veterinarioService.softDeleteVeterinario(veterinario)
      }
    }
  }
}
