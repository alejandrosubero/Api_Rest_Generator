import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './componet/home/home.component';
import { CodeComponent } from './componet/code/code.component';
import { ClienteComponent } from './componet/cliente/cliente.component';
import { ConsoleComponent } from './componet/console/console.component';
import { ToolPanelComponent } from './componet/tool-panel/tool-panel.component';
import { AboutSammaryComponent } from './componet/about-sammary/about-sammary.component';
import { PanelComponent } from './pages/panel/panel.component';
import { ArquitecturaComponent } from './pages/arquitectura/arquitectura.component';
import { DatabaseViewComponent } from './pages/database-view/database-view.component';
import { JavaVersionViewComponent } from './pages/java-version-view/java-version-view.component';
import { ManagerMethodsComponent } from './pages/manager-methods/manager-methods.component';
import { ManagerToolComponent } from './pages/manager-tool/manager-tool.component';
import { SeguridadComponent } from './pages/seguridad/seguridad.component';
import { ProyectoDataComponent } from './pages/proyecto-data/proyecto-data.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'cliente', component: ClienteComponent },
  { path: 'code', component: CodeComponent },
  { path: 'consola', component: ConsoleComponent },
  { path: 'paneltool', component: ToolPanelComponent },
  { path: 'about', component: AboutSammaryComponent },
  { path: 'panel', component: PanelComponent },
  { path: 'arqutecture', component: ArquitecturaComponent },
  { path: 'database', component: DatabaseViewComponent },
  { path: 'javaVersion', component: JavaVersionViewComponent },
  { path: 'managerMethods', component: ManagerMethodsComponent },
  { path: 'managerTool', component: ManagerToolComponent },
  { path: 'seguridad', component: SeguridadComponent },
  { path: 'proyectoData', component: ProyectoDataComponent },
  { path: '', component: HomeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
