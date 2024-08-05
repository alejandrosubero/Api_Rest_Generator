import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { IdiomaService } from 'src/app/service/idioma.service';
import { ArchitectureService } from 'src/app/services/architecture.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  public activeLang = 'es';
  idio:Observable<string>;

  constructor(private translate: TranslateService, 
    private idiomas: IdiomaService, 
    // private architectureService: ArchitectureService
  ) {
    this.translate.setDefaultLang(this.activeLang);
   }

  ngOnInit() {
    this.idiomas.subject$.subscribe(x=>{
        this.cambiarLenguaje(x);
    });

    // this.architectureService.getIdentifiers().subscribe((x:Array<string>)=>{
    //   this.architectureService.updateEntidad(x);
    //   this.testBe();
    // });
  }

// testBe(){
//   this.architectureService.identifier$.subscribe(value =>{
//     console.log("IDENTTIFIERS", value);
//   });
// }


  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }
}
