import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-usuario',
  standalone: false,
  templateUrl: './header-usuario.html',
  styleUrls: ['./header-usuario.css']
})
export class HeaderUsuario {
  rol: string = '';
  loggedIn: boolean = false;
  constructor(private router: Router) {}
  ngOnInit(): void {
    localStorage.setItem('id','')
    this.rol = localStorage.getItem('rol') ?? '';
    this.loggedIn = !!localStorage.getItem('id');
  }
  logout(): void {
    localStorage.clear();
    this.loggedIn = false;
    this.router.navigate(['/login']);
  }
}
