import { Component } from '@angular/core';

@Component({
  selector: 'app-header-usuario',
  standalone: false,
  templateUrl: './header-usuario.html',
  styleUrls: ['./header-usuario.css']
})
export class HeaderUsuario {
  rol: string = '';

  ngOnInit(): void {
    this.rol = localStorage.getItem('rol') ?? '';
  }
}
