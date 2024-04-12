import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router, RouterLink } from '@angular/router';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { ArquitecturaComponent } from '../arquitectura/arquitectura.component';
import { DatabaseViewComponent } from '../database-view/database-view.component';
import { JavaVersionViewComponent } from '../java-version-view/java-version-view.component';
import { ManagerMethodsComponent } from '../manager-methods/manager-methods.component';
import { ManagerToolComponent } from '../manager-tool/manager-tool.component';
import { ProyectoDataComponent } from '../proyecto-data/proyecto-data.component';
import { SeguridadComponent } from '../seguridad/seguridad.component';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css']
})
export class PanelComponent implements OnInit {

  public proyectoData = ProyectoDataComponent;
  public JavaVersionView = JavaVersionViewComponent;
  public DatabaseView = DatabaseViewComponent;
  public seguridad = SeguridadComponent;
  public ManagerTool = ManagerToolComponent;
  public ManagerMethods = ManagerMethodsComponent;
  public Arquitectura = ArquitecturaComponent;
 

  barraControlPanel: Array<string> = ['1', '1'];
  activoPanelSuperior: Array<string> = ['1', '1', '1', '1'];
  activoPanelInferior: Array<string> = ['1', '1', '1', '1'];

  colunNumber: Array<number> = [];

 colors: Array<string> = ['color1', 'color2', 'color3', 'color4', 'color5', 'color6', 'color7', 'color8'];
// colors: Array<string> = ['default', 'default', 'default', 'default', 'default', 'default', 'default', 'default'];
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();

  constructor(private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.centerViewControl(this.activoPanelSuperior, 1);
    this.centerViewControl(this.activoPanelInferior, 2);
  }


  goto(valor) {
    this.router.navigateByUrl(valor);
  }


  centerViewControl(activoPanelControl: Array<string>, colun: number) {
    let count = 0;
    const listaActiva = activoPanelControl.filter(x => x === '1');
    count = listaActiva.length;

    switch (count) {
      case 2:
        if (colun === 1) {
          this.colunNumber[0] = 6;
          this.colunNumber[1] = 6;
        } else {
          this.colunNumber[2] = 6;
          this.colunNumber[3] = 6;
        }
        break;
      case 3:
        if (colun === 1) {
          this.colunNumber[0] = 4;
          this.colunNumber[1] = 4;
        } else {
          this.colunNumber[2] = 4;
          this.colunNumber[3] = 4;
        }
        break;
      case 1:
        if (colun === 1) {
          this.colunNumber[0] = 12;
          this.colunNumber[1] = 12;
        } else {
          this.colunNumber[2] = 12;
          this.colunNumber[3] = 12;
        }
        break;
      case 4:
        if (colun === 1) {
          this.colunNumber[0] = 6;
          this.colunNumber[1] = 3;
        } else {
          this.colunNumber[2] = 6;
          this.colunNumber[3] = 3;
        }
        break;
    }
  }


  goToProyecto(componet: any): void {
    let editar = false;
    if (this.archivo.toolClassPojo) { editar = true; } else { editar = false; }
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '85%';
    dialogConfig.height = '85%';
    dialogConfig.data = {
      atributosTool: this.archivo,
      edit: editar,
    };
    const dialogRef = this.dialog.open(componet, dialogConfig);
    dialogRef.afterClosed().subscribe(x => {

      if (x !== undefined) { }
    });
  }

}



