import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DeveloperDTO } from '../../model/developer_dto.model';
import { CoreService } from '../../services/core.service';
import { DevUserService } from '../../services/dev-user.service';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, ButtonModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.scss'
})
export class AddUserComponent {
  lastName:string ='';
  name:string='';
  valid:boolean = false;
  user= new DeveloperDTO();
  devUserService:DevUserService =inject(DevUserService);
  coreService: CoreService = inject(CoreService);

  onSendUser(){
    this.user.setUser(this.name,this.lastName);
    this.devUserService.saveUser(this.user);
  }

  backToInicioPage(){
    this.coreService.navigateToRoute('wiki/hello');
  }

  onCancel(){
    this.backToInicioPage();
  }
}
