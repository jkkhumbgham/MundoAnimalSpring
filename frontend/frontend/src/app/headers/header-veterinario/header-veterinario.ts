import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-veterinario',
  standalone: false,
  templateUrl: './header-veterinario.html',
  styleUrls: ['./header-veterinario.css']
})
export class HeaderVeterinario implements OnInit {
  rol: string = '';
  loggedIn: boolean = false;
  id: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.rol = localStorage.getItem('tipoUsuario') ?? '';
    this.id = localStorage.getItem('id') ?? '';
    this.loggedIn = !!localStorage.getItem('id');
  }

  logout(): void {
    localStorage.clear();
    this.loggedIn = false;
    this.router.navigate(['/login']);
  }
}
