import { Routes } from '@angular/router';
import { InicioComponent } from './componets/inicio/inicio.component';
import { DashboarComponent } from './componets/dashboar/dashboar.component';
import { AddUserComponent } from './componets/add-user/add-user.component';

export const routes: Routes = [
    {path: 'wiki/hello', component: InicioComponent},
    {path: 'dashboar', component: DashboarComponent},
    {path:'addUser',component:AddUserComponent},
    {path: '', component: InicioComponent } 
];
