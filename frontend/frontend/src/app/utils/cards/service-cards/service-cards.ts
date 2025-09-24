import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-service-cards',
  standalone: false,
  templateUrl: './service-cards.html',
  styleUrl: './service-cards.css'
})
export class ServiceCards {
  @Input() img: string = '';
  @Input() titulo: string = '';
  @Input() texto: string = '';
}
