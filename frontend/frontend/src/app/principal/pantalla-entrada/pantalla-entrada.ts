import { Component } from '@angular/core';

@Component({
  selector: 'app-pantalla-entrada',
  standalone: false,
  templateUrl: './pantalla-entrada.html',
  styleUrl: './pantalla-entrada.css'
})
export class PantallaEntrada {
  rol: string = '';

  ngOnInit(): void {
    this.rol = localStorage.getItem('rol') ?? '';
  }
}
