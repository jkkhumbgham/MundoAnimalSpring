import { Tratamiento } from "../tratamiento/tratamiento";

export class Medicamento {
    id: number;
    nombre: string;
    precio: number;
    unidades: number;
    tratamientos?: Tratamiento[]

  constructor(
    id: number, 
    nombre: string, 
    precio: number, 
    unidades: number
) {
    this.id = id
    this.nombre = nombre
    this.precio = precio
    this.unidades = unidades
  }    
}
