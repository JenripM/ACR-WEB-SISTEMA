import { Routes } from '@angular/router';

// ui
import { AppBadgeComponent } from './badge/badge.component';
import { AppChipsComponent } from './chips/chips.component';
import { AppListsComponent } from './lists/lists.component';
import { AppMenuComponent } from './menu/menu.component';
import { AppTooltipsComponent } from './tooltips/tooltips.component';
import { AppTrabajadoresComponent } from './trabajadores/trabajadores.component';
import { AppTrabajadorRegistrarComponent } from './trabajadores/registrar/registrar.component';
import { CasosRegistrarComponent } from './casosjuridicos/registrar/registrar.component';
import { CasosEditarComponent } from './casosjuridicos/editar/editar.component';
import { CargoComponent } from './cargo/cargo.component';
import { EditarComponent } from './trabajadores/editar/editar.component';
import { EliminarComponent } from './trabajadores/eliminar/eliminar.component';
import { RolesUsuarioComponent } from './roles-usuario/roles-usuario.component';
import { CasosjuridicosComponent } from './casosjuridicos/casosjuridicos.component';
import { AsignaciontareasComponent } from './asignaciontareas/asignaciontareas.component';
import { DocumentosComponent } from './documentos/documentos.component';
import { CorrespondenciaComponent } from './correspondencia/correspondencia.component';
import { CasosEliminarComponent } from './casosjuridicos/eliminar/eliminar.component';

export const UiComponentsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'badge',
        component: AppBadgeComponent,
      },
      {
        path: 'trabajadores',
        component: AppTrabajadoresComponent,
      },
      {
        path: 'trabajadores/registrar',
        component: AppTrabajadorRegistrarComponent,
      },
      {
        path: 'trabajadores/editar/:id',
        component: EditarComponent,
      },
      {
        path: 'trabajadores/eliminar/:id',
        component: EliminarComponent,
      },
      {
        path: 'cargo',
        component: CargoComponent,
      },
      {
        path: 'casosjuridicos',
        component: CasosjuridicosComponent,
      },
      {
        path: 'casosjuridicos/registrar',
        component: CasosRegistrarComponent,
      },
      {
        path: 'casosjuridicos/editar/:id',
        component: CasosEditarComponent,
      },
      {
        path: 'casosjuridicos/eliminar/:id',
        component: CasosEliminarComponent,
      },
      {
        path: 'asignaciontareas',
        component: AsignaciontareasComponent,
      },
      {
        path: 'documentos',
        component: DocumentosComponent,
      },
      {
        path: 'correspondencia',
        component: CorrespondenciaComponent,
      },
      {
        path: 'roles-usuario',
        component: RolesUsuarioComponent,
      },
    ],
  },
];
