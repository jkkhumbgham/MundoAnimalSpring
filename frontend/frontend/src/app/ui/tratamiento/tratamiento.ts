import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TratamientoService } from '../../service/tratamiento/tratamiento-service';
import { Tratamiento } from '../../model/tratamiento/tratamiento';

@Component({
  selector: 'app-tratamiento',
  templateUrl: './tratamiento.html',
  styleUrls: ['./tratamiento.css']
})
export class TratamientoComponent implements OnInit {

  tratamientos: Tratamiento[] = [];
  mascotaId!: number; 

  constructor(
    private route: ActivatedRoute,
    private tratamientoService: TratamientoService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.mascotaId = Number(id);
      console.log('Mascota seleccionada con ID:', this.mascotaId);

      this.tratamientoService.porMascota(this.mascotaId).subscribe({
        next: (data) => {
          this.tratamientos = data;
          console.log('Tratamientos cargados:', data);
        },
        error: (err) => console.error('Error al cargar tratamientos:', err)
      });
    } else {
      console.warn('No se recibió ID de mascota en la URL');
    }
  }
}
