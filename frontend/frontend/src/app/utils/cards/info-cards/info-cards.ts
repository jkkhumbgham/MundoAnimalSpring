import { Component, Input } from '@angular/core';

interface CardSection {
  label: string;
  value?: string | Date | number | null;
  items?: string[];   
}
@Component({
  selector: 'app-info-cards',
  standalone: false,
  templateUrl: './info-cards.html',
  styleUrl: './info-cards.css'
})
export class InfoCards {
  
  @Input() titulo: string = '';
  @Input() primerSubtitulo: string = '';
  @Input() segundoSubtitulo: string = '';
  @Input() tercerSubtitulo: string = '';
  @Input() cuartoSubtitulo: string = '';
  @Input() quintoSubtitulo: string = '';
  @Input() sextoSubtitulo: string = '';
  @Input() primerTexto: string = '';
  @Input() segundoTexto: string = ''; 
  @Input() tercerTexto: string = '';
  @Input() cuartoTexto: string|Date = '';
  @Input() quintoTexto: string = '';
  @Input() sextoTexto: string = '';
  @Input() primeraLista: string[] = [];
  @Input() segundaLista: string[] = [];
}
