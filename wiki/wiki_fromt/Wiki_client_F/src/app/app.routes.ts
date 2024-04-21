import { Routes } from '@angular/router';
import { InicioComponent } from './componets/inicio/inicio.component';
import { DashboarComponent } from './componets/dashboar/dashboar.component';

export const routes: Routes = [
    {path: 'inicio', component: InicioComponent},
    {path: 'dashboar', component: DashboarComponent},
    {path: '', component: InicioComponent } 
];
