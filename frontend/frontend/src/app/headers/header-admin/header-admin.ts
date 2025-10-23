import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-admin',
  standalone: false,
  templateUrl: './header-admin.html',
  styleUrl: './header-admin.css'
})
export class HeaderAdmin {
  rol: string = '';
  loggedIn: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.rol = localStorage.getItem('tipoUsuario') ?? '';
    this.loggedIn = !!localStorage.getItem('id');
  }

  logout(): void {
    localStorage.clear();
    this.loggedIn = false;
    this.router.navigate(['/login']);
  }
}
