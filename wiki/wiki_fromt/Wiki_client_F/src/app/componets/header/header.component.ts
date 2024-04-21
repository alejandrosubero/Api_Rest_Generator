import { Component, Input, OnInit, inject } from '@angular/core';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { InputSwitchModule } from 'primeng/inputswitch';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ThemeService } from '../../services/theme.service';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [ToolbarModule, ButtonModule, InputSwitchModule, FormsModule, InputTextModule, ToggleButtonModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  checked: boolean = false;
  selectedTheme:string='dark';
  themeService: ThemeService = inject(ThemeService);
  isUserInSystenService: UserService = inject(UserService);
 
   
  onIcon_i: string = "pi pi-moon";
  offIcon_i: string = "pi pi-pi-sun";
  title = "Wiki Client's";
    
  ngOnInit(): void {
   this.themeService.setTheme(this.selectedTheme);

  }


  onThemeChame(): void {
    let theme = this.themeService.themeSignal();
    this.selectedTheme = theme;
    this.themeService.setTheme(theme);
  }

  toggleTheme() {
    this.themeService.updateThemeSignal();
    this.onThemeChame();
  }

}
