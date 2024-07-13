import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

// icons
import { TablerIconsModule } from 'angular-tabler-icons';
import * as TablerIcons from 'angular-tabler-icons/icons';

import { UiComponentsRoutes } from './ui-components.routing';

// ui components
import { AppBadgeComponent } from './badge/badge.component';
import { AppChipsComponent } from './chips/chips.component';
import { AppListsComponent } from './lists/lists.component';
import { AppMenuComponent } from './menu/menu.component';
import { AppTooltipsComponent } from './tooltips/tooltips.component';
import { MatNativeDateModule } from '@angular/material/core';
import { AppTrabajadoresComponent } from './trabajadores/trabajadores.component';
import { AppTrabajadorRegistrarComponent } from './trabajadores/registrar/registrar.component';
import { CasosRegistrarComponent } from './casosjuridicos/registrar/registrar.component';
import { CasosEditarComponent } from './casosjuridicos/editar/editar.component';
import { CasosEliminarComponent } from './casosjuridicos/eliminar/eliminar.component';
import { CasosjuridicosComponent } from './casosjuridicos/casosjuridicos.component';
import { CargoComponent } from './cargo/cargo.component';
import { MatIconModule } from '@angular/material/icon';
import { EditarComponent } from './trabajadores/editar/editar.component';
import { EliminarComponent } from './trabajadores/eliminar/eliminar.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UiComponentsRoutes),
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TablerIconsModule.pick(TablerIcons),
    MatNativeDateModule,
    AppTrabajadorRegistrarComponent,
    CasosRegistrarComponent,
    CasosEditarComponent,
    CasosEliminarComponent,
    CargoComponent,
    EditarComponent,
    EliminarComponent,
    MatIconModule,
  ],
  declarations: [
    AppBadgeComponent,
    AppChipsComponent,
    AppListsComponent,
    AppMenuComponent,
    AppTooltipsComponent,
    AppTrabajadoresComponent,
    CasosjuridicosComponent,



  ],
})
export class UicomponentsModule {}
