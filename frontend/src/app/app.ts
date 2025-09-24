import { Component, signal } from '@angular/core';
import {Colores} from './colores/colores';

@Component({
  selector: 'app-root',
  imports: [Colores],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
 protected readonly secondTitle = signal('Texto secundario')
  mostrarMensaje:boolean = true;

}
