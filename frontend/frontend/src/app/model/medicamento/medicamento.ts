import { Tratamiento } from '../tratamiento/tratamiento';

export class Medicamento {
  id: number;
  nombre: string;
  precio_venta: number;
  unidades: number;
  tratamientos?: Tratamiento[];
  precio_compra: number;
  unidades_vendidas: number;

  constructor(
    id: number,
    nombre: string,
    precio_venta: number,
    unidades: number,
    precio_compra: number,
    unidades_vendidas: number
  ) {
    this.id = id;
    this.nombre = nombre;
    this.precio_venta = precio_venta;
    this.unidades = unidades;
    this.precio_compra = precio_compra;
    this.unidades_vendidas = unidades_vendidas;
  }
}
