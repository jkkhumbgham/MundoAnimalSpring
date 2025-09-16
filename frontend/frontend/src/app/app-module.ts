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

@NgModule({
  declarations: [
    App,
    FooterTodo,
    HeaderUsuario,
    HeaderVeterinario,
    PantallaEntrada,
    Login
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
