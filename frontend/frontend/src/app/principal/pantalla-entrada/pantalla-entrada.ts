import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-pantalla-entrada',
  standalone: false,
  templateUrl: './pantalla-entrada.html',
  styleUrl: './pantalla-entrada.css',
  encapsulation: ViewEncapsulation.None
})
export class PantallaEntrada {
  rol: string = '';

  ngOnInit(): void {
    this.rol = localStorage.getItem('rol') ?? '';
  }
}
