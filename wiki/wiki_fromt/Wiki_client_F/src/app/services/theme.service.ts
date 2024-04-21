import { DOCUMENT } from '@angular/common';
import { Inject, Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  constructor(@Inject(DOCUMENT) private document: Document) { }

  activeTheme: string = 'dark';
  themeSignal = signal<string>("dark");

  getTheme() {
    return this.activeTheme;
  }

  setTheme(theme: string) {
    let themelink = document.getElementById('app-theme') as HTMLLinkElement;
    if (themelink) {
      themelink.href = theme + '.css';
    }
    this.activeTheme = theme;
  }

  setThemeSignal(theme: string) {
    this.themeSignal.set(theme);
  }
  updateThemeSignal() {
    this.themeSignal.update(value => (value == "dark" ? "light" : "dark"));
  }

}
