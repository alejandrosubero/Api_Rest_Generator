import { Component, inject } from '@angular/core';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CoreService } from '../../services/core.service';
import { DevUserService } from '../../services/dev-user.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CardModule, InputTextModule, CommonModule, FormsModule, ButtonModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.scss'
})
export class InicioComponent {
  isUserInSystenService:any;
  name: string='';
  coreService:CoreService = inject(CoreService);
  private devUserService: DevUserService = inject(DevUserService);
  constructor(){}


  onEnterUser(){
    if(this.name !== ''){
      this.devUserService.checkUser(this.name)
    }
  }

}
