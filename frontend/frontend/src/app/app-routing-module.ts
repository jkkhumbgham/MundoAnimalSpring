import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login/login';
import { MascotaTablaComponent } from './mascotas/mascota-tabla/mascota-tabla.component';
import { MascotaFormularioComponent } from './mascotas/mascota-formulario/mascota-formulario.component';
import { PantallaEntrada } from './principal/pantalla-entrada/pantalla-entrada';
import { MascotaDetalle } from './mascotas/mascota-detalle/mascota-detalle/mascota-detalle';
import { UsuariosTabla } from './usuarios/usuarios-tabla/usuarios-tabla';
import { UsuariosDetalle } from './usuarios/usuarios-detalle/usuarios-detalle';
import { UsuariosFormulario } from './usuarios/usuarios-formulario/usuarios-formulario';

const routes: Routes = [
  { path: '', component: PantallaEntrada},

  { path: 'login', component: LoginComponent },

  { path: 'mascotas', component: MascotaTablaComponent },

  { path: 'mascotas/new/:id', component: MascotaFormularioComponent },

  { path: 'mascotas/editar/:id', component: MascotaFormularioComponent },

  { path:'mascota/find/:id', component: MascotaDetalle},

  { path: 'usuarios', component: UsuariosTabla },

  { path: 'usuarios/agregar', component: UsuariosFormulario },

  { path: 'usuarios/editar/:id', component: UsuariosFormulario },

  { path: 'usuarios/:id', component: UsuariosDetalle },

  

  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
