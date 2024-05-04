import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { IdiomaService } from '../service/idioma.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ContactameComponent } from '../componet/contactame/contactame.component';

@Component({
  selector: 'app-my-nav',
  templateUrl: './my-nav.component.html',
  styleUrls: ['./my-nav.component.css']
})
export class MyNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private idiomas: IdiomaService, private dialog: MatDialog,) {}


  idioma(valor){
    this.idiomas.cambioIdioma(valor);
   // alert(valor);
  }

  contactMeDialogTools(): void {
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
