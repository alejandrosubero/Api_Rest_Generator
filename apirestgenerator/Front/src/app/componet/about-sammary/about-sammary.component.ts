import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ContactameComponent } from '../contactame/contactame.component';
import { EducacionComponent } from './educacion/educacion.component';
import { ExperienciaComponent } from './experiencia/experiencia.component';
import { InterestsComponent } from './interests/interests.component';
import { QuiensoyComponent } from './quiensoy/quiensoy.component';
import { RecomendacionesComponent } from './recomendaciones/recomendaciones.component';
import { SkillsComponent } from './skills/skills.component';

@Component({
  selector: 'app-about-sammary',
  templateUrl: './about-sammary.component.html',
  styleUrls: ['./about-sammary.component.css']
})
export class AboutSammaryComponent implements OnInit {


  quiensoy = QuiensoyComponent;
  experience = ExperienciaComponent;
  educ = EducacionComponent;
  skills = SkillsComponent;
  interests = InterestsComponent;
  certificayrecomendacion = RecomendacionesComponent;

  constructor(private dialog: MatDialog,) { }

  ngOnInit() {
  }
scrollElemet($element): void { $element.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest' }); }

contactMeDialogTools():void {
    
  const dialogConfig = new MatDialogConfig();
  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;
  
  dialogConfig.width = 'auto';
  dialogConfig.height = 'auto';

  dialogConfig.data = { };
  const dialogRef = this.dialog.open(ContactameComponent, dialogConfig);
  dialogRef.afterClosed().subscribe();
}


}
