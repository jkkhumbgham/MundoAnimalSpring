import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { FooterTodo } from './footers/footer-todo/footer-todo';
import { HeaderUsuario } from './headers/header-usuario/header-usuario';
import { HeaderVeterinario } from './headers/header-veterinario/header-veterinario';
import { PantallaEntrada } from './principal/pantalla-entrada/pantalla-entrada';
import { LoginComponent } from './login/login/login';
import { FormsModule } from '@angular/forms';
import { MascotaTablaComponent } from './mascotas/mascota-tabla/mascota-tabla.component';
import { MascotaFormularioComponent } from './mascotas/mascota-formulario/mascota-formulario.component';
import { MascotaDetalle } from './mascotas/mascota-detalle/mascota-detalle/mascota-detalle';
import { ServiceCards } from './utils/cards/service-cards/service-cards';
import { FormsCard } from './utils/cards/forms-card/forms-card';
import { InfoCards } from './utils/cards/info-cards/info-cards';
import { HttpClientModule } from '@angular/common/http';
import { UsuariosTabla } from './usuarios/usuarios-tabla/usuarios-tabla';
import { UsuariosDetalle } from './usuarios/usuarios-detalle/usuarios-detalle';
import { UsuariosFormulario } from './usuarios/usuarios-formulario/usuarios-formulario';
import { Tratamiento } from './ui/tratamiento/tratamiento';

@NgModule({
  declarations: [
    App,
    FooterTodo,
    HeaderUsuario,
    HeaderVeterinario,
    PantallaEntrada,
    LoginComponent,
    MascotaTablaComponent,
    MascotaFormularioComponent,
    MascotaDetalle,
    ServiceCards,
    FormsCard,
    InfoCards,
    UsuariosTabla,
    UsuariosDetalle,
    UsuariosFormulario,
    Tratamiento
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
