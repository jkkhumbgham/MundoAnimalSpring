import { Mascota } from '../mascota/mascota';

export class Usuario {
  id: number;
  nombre: string;
  telefono: string;
  cedula: number;
  email: string;
  contrasena: string;
  foto: string;
  mascotas?: Mascota[];

  constructor(
    id: number,
    nombre: string,
    telefono: string,
    cedula: number,
    email: string,
    contrasena: string,
    foto: string
  ) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.cedula = cedula;
    this.email = email;
    this.contrasena = contrasena;
    this.foto = foto;
  }
}
