import { Tratamiento } from "../tratamiento/tratamiento";
import { Usuario } from "../usuario/usuario";

export class Mascota {
    id?: number;
    dueno?: Usuario;
    tratamientos?: Tratamiento[];
    vacunas: string[];
    alergias: string[];
    observaciones: string;
    foto: string;
    nombre: string;
    especie: string;
    raza: string;
    sexo: string;
    estado: string;
    ultimavisita: Date;
    fechaNacimiento: Date;
    peso: number;
    microchipID?: number;
    estadoOriginal?: String;

  constructor(
    id: number, 
    vacunas: string[], 
    alergias: string[], 
    observaciones: string, 
    foto: string, 
    nombre: string, 
    especie: string, 
    raza: string, 
    sexo: string, 
    estado: string, 
    ultimavisita: Date, 
    fechaNacimiento: Date, 
    peso: number, 
    microchipID: number
) {
    this.id = id
    this.vacunas = vacunas
    this.alergias = alergias
    this.observaciones = observaciones
    this.foto = foto
    this.nombre = nombre
    this.especie = especie
    this.raza = raza
    this.sexo = sexo
    this.estado = estado
    this.ultimavisita = ultimavisita
    this.fechaNacimiento = fechaNacimiento
    this.peso = peso
    this.microchipID = microchipID
    this.estadoOriginal = ""
  }    

    
  static crearVacia(): Mascota {
    return new Mascota(
      0, [], [], '', '', '', '', '', '', '', 
      new Date(), new Date(), 0, 0
    );
  }  
}
