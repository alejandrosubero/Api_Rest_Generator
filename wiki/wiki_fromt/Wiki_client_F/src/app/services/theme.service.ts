// import { DOCUMENT } from '@angular/common';
import { Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  
  // constructor(@Inject(DOCUMENT) private document: Document) {}

  activeTheme:string ='dark';

  getTeme(){
    return this.activeTheme;
  }

  setTheme(theme: string){
    let themelink = document.getElementById('app-theme') as HTMLLinkElement;

    if(themelink){
      themelink.href = theme + '.css';
    }
    this.activeTheme = theme;

  }
  
}
