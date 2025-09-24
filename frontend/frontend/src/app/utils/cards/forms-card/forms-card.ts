import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-forms-card',
  standalone: false,
  templateUrl: './forms-card.html',
  styleUrl: './forms-card.css'
})
export class FormsCard {
  @Input() titulo: string = '';
  @Input() texto: string = '';
  @Input() primerLabel: string = '';
  @Input() segundoLabel: string = '';
  @Input() tercerLabel: string = '';
  @Input() cuartoLabel: string = '';
  @Input() quintoLabel: string = '';
  @Input() primerPlaceholder: string = '';
  @Input() segundoPlaceholder: string = '';
  @Input() tercerPlaceholder: string = '';
  @Input() cuartoPlaceholder: string = '';
  @Input() quintoPlaceholder: string = ''
  @Input() botonTexto: string = '';
}
