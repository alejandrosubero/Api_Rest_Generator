// base
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA, enableProdMode } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//components
import { AppComponent } from './app.component';
import { HomeComponent } from './componet/home/home.component';
import { MenuComponent } from './componet/menu/menu.component';
import { EntidadComponent } from './componet/entidad/entidad.component';
import { CreateEntidadComponent } from './componet/entidad/create-entidad/create-entidad.component';
import { AtributoComponent } from './componet/atributo/atributo.component';
import { CreateAtributoComponent } from './componet/atributo/create-atributo/create-atributo.component';
import { RelacionComponent } from './componet/relacion/relacion.component';
import { CreateRelacionComponent } from './componet/relacion/create-relacion/create-relacion.component';
import { MyNavComponent } from './my-nav/my-nav.component';
import { CodeComponent } from './componet/code/code.component';
import { SidebarComponent } from './componet/sidebar/sidebar.component';
import { ClienteComponent } from './componet/cliente/cliente.component';
import { TablaComponent } from './componet/tabla/tabla.component';

import { ToolPanelComponent } from './componet/tool-panel/tool-panel.component';
import { AboutSammaryComponent } from './componet/about-sammary/about-sammary.component';

//naterial
import { MaterialModule } from './material/material.module';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';


//Translation
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

// servicies import and install
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { NgxSpinnerModule, NgxSpinnerService } from "ngx-spinner";
import { NgxJsonViewerModule } from 'ngx-json-viewer';
// import { AceEditorModule } from 'ng2-ace-editor';
import { LayoutModule } from '@angular/cdk/layout';


// my on services
import { ConsolaService } from './services/consola.service';
import { EntidadService } from './services/entidad.service';
import { IdiomaService } from './service/idioma.service';
import { QuiensoyComponent } from './componet/about-sammary/quiensoy/quiensoy.component';
import { ExperienciaComponent } from './componet/about-sammary/experiencia/experiencia.component';
import { EducacionComponent } from './componet/about-sammary/educacion/educacion.component';
import { SkillsComponent } from './componet/about-sammary/skills/skills.component';
import { InterestsComponent } from './componet/about-sammary/interests/interests.component';
import { AwardsComponent } from './componet/about-sammary/awards/awards.component';
import { RecomendacionesComponent } from './componet/about-sammary/recomendaciones/recomendaciones.component';
import { ContactameComponent } from './componet/contactame/contactame.component';
import { ConsoleComponent } from './componet/console/console.component';
import { PagesModule } from './pages/pages.module';
import { CodeSimpleComponent } from './componet/code-simple/code-simple.component';
import { HammerModule} from '@angular/platform-browser';
import { APP_BASE_HREF } from '@angular/common';

// static forRoot(): ModuleWithProviders<MaterialModule>


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MenuComponent,
    CodeComponent,
    EntidadComponent,
    CreateEntidadComponent,
    AtributoComponent,
    CreateAtributoComponent,
    RelacionComponent,
    CreateRelacionComponent,
    SidebarComponent,
    ClienteComponent,
    TablaComponent,
    MyNavComponent,
    ConsoleComponent,
    ToolPanelComponent,
    AboutSammaryComponent,
    QuiensoyComponent,
    ExperienciaComponent,
    EducacionComponent,
    SkillsComponent,
    InterestsComponent,
    AwardsComponent,
    RecomendacionesComponent,
    ContactameComponent,
    CodeSimpleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'}), // silencia los warnies del uso del ngmodule
    FormsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    NgxJsonViewerModule,
    HttpClientModule,
    NgxSpinnerModule,
    PagesModule,
    HammerModule,
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
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [
    CreateEntidadComponent,
    CreateRelacionComponent,
    CreateAtributoComponent,
    ToolPanelComponent,
  ],
  providers: [
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: false } },
    ConsolaService,
    NgxSpinnerService,
    IdiomaService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
