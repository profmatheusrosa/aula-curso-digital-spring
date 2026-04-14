import { Routes } from '@angular/router';
import { UsuarioList } from './components/usuario-list/usuario-list';
import { UsuarioForm } from './components/usuario-form/usuario-form';

export const routes: Routes = [
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioList },
  { path: 'usuarios/novo', component: UsuarioForm },
  { path: 'usuarios/editar/:uuid', component: UsuarioForm }
];
