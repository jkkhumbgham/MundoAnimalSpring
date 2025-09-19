import { Mascota } from "../mascota/mascota";
import { Medicamento } from "../medicamento/medicamento";
import { Veterinario } from "../veterinario/veterinario";

export class Tratamiento {
  id: number;
  nombre: string;
  veterinario?: Veterinario;
  mascota?: Mascota;
  medicamentos?: Medicamento[];

  constructor(
    id: number,
    nombre: string,
  ) {
    this.id = id;
    this.nombre = nombre;
  }
}
