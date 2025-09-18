import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Login } from './login/login/login';
import { MascotaTablaComponent } from './mascotas/mascota-tabla/mascota-tabla.component';
import { MascotaFormularioComponent } from './mascotas/mascota-formulario/mascota-formulario.component';
import { PantallaEntrada } from './principal/pantalla-entrada/pantalla-entrada';
import { MascotaDetalle } from './mascotas/mascota-detalle/mascota-detalle/mascota-detalle';

const routes: Routes = [
  { path: '', component: PantallaEntrada},

  { path: 'login', component: Login },

  { path: 'mascotas', component: MascotaTablaComponent },

  { path: 'mascotas/new', component: MascotaFormularioComponent },

  { path: 'mascotas/editar/:id', component: MascotaFormularioComponent },

  { path:'mascota/:id', component: MascotaDetalle},

  { path: '**', redirectTo: 'pantalla-entrada' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
