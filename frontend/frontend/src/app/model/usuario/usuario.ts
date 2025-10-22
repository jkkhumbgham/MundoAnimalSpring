import { Mascota } from '../mascota/mascota';

export class Usuario {
  id: number;
  nombre: string;
  telefono: string;
  cedula: number;
  email: string;
  password: string;
  foto: string;
  mascotas?: Mascota[];

  constructor(
    id: number,
    nombre: string,
    telefono: string,
    cedula: number,
    email: string,
    password: string,
    foto: string
  ) {
    this.id = id;
    this.nombre = nombre;
    this.telefono = telefono;
    this.cedula = cedula;
    this.email = email;
    this.password = password;
    this.foto = foto;
  }
  static crearVacio(): Usuario {
    return new Usuario(0, '', '', 0, '', '', '');
  }
}
