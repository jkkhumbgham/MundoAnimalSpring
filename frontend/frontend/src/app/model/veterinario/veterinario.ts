import { Tratamiento } from '../tratamiento/tratamiento';

export class Veterinario {
  id: number;
  nombre: string;
  email: string;
  password: string;
  cedula: number;
  especialidad: string;
  foto: string;
  tratamientos?: Tratamiento[];

  constructor(
    id: number,
    nombre: string,
    email: string,
    password: string,
    cedula: number,
    especialidad: string,
    foto: string,
    tratamientos?: Tratamiento[]
  ) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.password = password;
    this.cedula = cedula;
    this.especialidad = especialidad;
    this.foto = foto;
    this.tratamientos = tratamientos;
  }
}
