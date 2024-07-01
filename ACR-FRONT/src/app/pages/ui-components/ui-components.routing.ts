import { Routes } from '@angular/router';

// ui
import { AppBadgeComponent } from './badge/badge.component';
import { AppChipsComponent } from './chips/chips.component';
import { AppListsComponent } from './lists/lists.component';
import { AppMenuComponent } from './menu/menu.component';
import { AppTooltipsComponent } from './tooltips/tooltips.component';
import { AppTrabajadoresComponent } from './trabajadores/trabajadores.component';
import { AppTrabajadorRegistrarComponent } from './trabajadores/registrar/registrar.component';
import { CargoComponent } from './cargo/cargo.component';
import { EditarComponent } from './trabajadores/editar/editar.component';
import { EliminarComponent } from './trabajadores/eliminar/eliminar.component';
import { RolesUsuarioComponent } from './roles-usuario/roles-usuario.component';

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
        path: 'lists',
        component: AppListsComponent,
      },
      {
        path: 'menu',
        component: AppMenuComponent,
      },
      {
        path: 'tooltips',
        component: AppTooltipsComponent,
      },
      {
        path: 'roles-usuario',
        component: RolesUsuarioComponent,
      },
    ],
  },
];
 