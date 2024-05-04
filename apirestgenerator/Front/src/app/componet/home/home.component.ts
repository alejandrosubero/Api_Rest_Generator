import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { IdiomaService } from 'src/app/service/idioma.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  public activeLang = 'es';
  idio:Observable<string>;

  constructor(private translate: TranslateService, private idiomas: IdiomaService) {
    this.translate.setDefaultLang(this.activeLang);
   }

  ngOnInit() {
    this.idiomas.subject$.subscribe(x=>{
        this.cambiarLenguaje(x);
    });

  }

  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }
}
