import { Tratamiento } from '../tratamiento/tratamiento';

export class Veterinario {
  id: number;
  nombre: string;
  email: string;
  password: string;
  cedula: number;
  especialidad: string;
  foto: string;
  estado: string;
  estadoOriginal?: string;
  tratamientos?: Tratamiento[];

  constructor(
    id: number,
    nombre: string,
    email: string,
    password: string,
    cedula: number,
    especialidad: string,
    foto: string,
    estado: string,
    estadoOriginal: string
  ) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.password = password;
    this.cedula = cedula;
    this.especialidad = especialidad;
    this.foto = foto;
    this.estado = estado;
    this.estadoOriginal = estadoOriginal;
  }
  static crearVacio(): Veterinario {
    return new Veterinario(0, '', '', '', 0, '', '', '', '');
  }
}
