import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { FooterTodo } from './footers/footer-todo/footer-todo';
import { HeaderUsuario } from './headers/header-usuario/header-usuario';
import { HeaderVeterinario } from './headers/header-veterinario/header-veterinario';
import { PantallaEntrada } from './principal/pantalla-entrada/pantalla-entrada';
import { Login } from './login/login/login';
import { FormsModule } from '@angular/forms';
import { MascotaTablaComponent } from './mascotas/mascota-tabla/mascota-tabla.component';
import { MascotaFormularioComponent } from './mascotas/mascota-formulario/mascota-formulario.component';
import { MascotaDetalle } from './mascotas/mascota-detalle/mascota-detalle/mascota-detalle';
import { ServiceCards } from './utils/cards/service-cards/service-cards';
import { FormsCard } from './utils/cards/forms-card/forms-card';
import { InfoCards } from './utils/cards/info-cards/info-cards';

@NgModule({
  declarations: [
    App,
    FooterTodo,
    HeaderUsuario,
    HeaderVeterinario,
    PantallaEntrada,
    Login,
    MascotaTablaComponent,
    MascotaFormularioComponent,
    MascotaDetalle,
    ServiceCards,
    FormsCard,
    InfoCards
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
