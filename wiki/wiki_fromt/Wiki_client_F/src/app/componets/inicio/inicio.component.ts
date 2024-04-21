import { Component, inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CardModule, InputTextModule, CommonModule, FormsModule, ButtonModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.scss'
})
export class InicioComponent {
  isUserInSystenService: UserService = inject(UserService);
  value: string | undefined;
}
