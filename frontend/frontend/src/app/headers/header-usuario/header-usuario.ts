import { Component } from '@angular/core';

@Component({
  selector: 'app-header-usuario',
  standalone: false,
  templateUrl: './header-usuario.html',
  styleUrls: ['./header-usuario.css']
})
export class HeaderUsuario {
  rol: string = '';
  get loggedIn(): boolean {
    console.log(localStorage.getItem('id'));
    return !!localStorage.getItem('id');
  }

  ngOnInit(): void {
    localStorage.setItem('id','')
    this.rol = localStorage.getItem('rol') ?? '';
    
  }
}
