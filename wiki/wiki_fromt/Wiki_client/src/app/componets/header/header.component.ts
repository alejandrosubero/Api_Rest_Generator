import { Component, OnInit, inject } from '@angular/core';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { InputSwitchModule } from 'primeng/inputswitch';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ThemeService } from '../../services/theme.service';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [ToolbarModule, ButtonModule, InputSwitchModule, FormsModule, InputTextModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  checked: boolean = true;
  selectedTheme:string='dark';
  themeService: ThemeService = inject(ThemeService);
  
  ngOnInit(): void {
   this.themeService.setTheme(this.selectedTheme);

  }

  onThemeChame(theme:string): void {
    this.selectedTheme = theme;
    this.themeService.setTheme(theme);
  }

}
