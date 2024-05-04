import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';


import { CommonModule } from '@angular/common';
import { PanelComponent } from './panel/panel.component';
import { MaterialModule } from '../material/material.module';
import { ProyectoDataComponent } from './proyecto-data/proyecto-data.component';
import { DatabaseViewComponent } from './database-view/database-view.component';
import { JavaVersionViewComponent } from './java-version-view/java-version-view.component';
import { SeguridadComponent } from './seguridad/seguridad.component';
import { ArquitecturaComponent } from './arquitectura/arquitectura.component';
import { ManagerMethodsComponent } from './manager-methods/manager-methods.component';
import { ManagerToolComponent } from './manager-tool/manager-tool.component';
import { HttpClient } from '@angular/common/http';



@NgModule({
  declarations: [
    PanelComponent,
    ProyectoDataComponent,
    DatabaseViewComponent,
    JavaVersionViewComponent,
    SeguridadComponent,
    ArquitecturaComponent,
    ManagerMethodsComponent,
    ManagerToolComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    TranslateModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (http: HttpClient) => {
          return new TranslateHttpLoader(http);
        },
        deps: [HttpClient]
      }
    }),
  ],
  exports: [
    PanelComponent,
  ],
})
export class PagesModule { }
