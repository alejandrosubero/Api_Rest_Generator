import { Routes } from '@angular/router';
import { InicioComponent } from './componets/inicio/inicio.component';
import { DashboarComponent } from './componets/dashboar/dashboar.component';
import { AddUserComponent } from './componets/add-user/add-user.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    {path: 'wiki/hello', component: InicioComponent},
    {path:'addUser',component:AddUserComponent},
    {path: 'dashboar', component: DashboarComponent, canActivate:[authGuard]},
    {path: '', component: InicioComponent } 
];
