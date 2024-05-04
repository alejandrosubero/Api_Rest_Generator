import { Component, OnInit, ViewChild, Input, EventEmitter, Output } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { TableLllenado } from 'src/Model/tableLlenado.model';
import { CreateEntidadComponent } from './create-entidad/create-entidad.component';
import { Entidades } from 'src/Model/entidad.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { AtributoComponent } from '../atributo/atributo.component';
import { RelacionComponent } from '../relacion/relacion.component';
import { EntidadService } from 'src/app/services/entidad.service';
import { Observable } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { IdiomaService } from 'src/app/service/idioma.service';

@Component({
  selector: 'app-entidad',
  templateUrl: './entidad.component.html',
  styleUrls: ['./entidad.component.css'],
})

export class EntidadComponent implements OnInit {

  entidadTrue = false;


  displayedColumns: string[] = ['nombreTabla', 'nombreClase', 'aciones'];
  dataSource: MatTableDataSource<Entidades> = new MatTableDataSource<Entidades>();

  entidadLista: Array<Entidades> = new Array<Entidades>();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @ViewChild(AtributoComponent, { static: false }) atributiRef: AtributoComponent;
  @ViewChild(RelacionComponent, { static: true }) relacionRef: RelacionComponent;


  @Output() cambioEntidades = new EventEmitter<Array<Entidades>>();
  @Output() cambioEntidad = new EventEmitter<Entidades>();
  @Output() cambioOpen = new EventEmitter<boolean>();
  // @Input() lista: Array<Entidades>;
  @Input() databaseTipoVal: number;
 

  public activeLang = 'es';
  idio: Observable<string>;

  constructor(private dialog: MatDialog,
    private entidadServicio: EntidadService,
    private translate: TranslateService,
    private idiomas: IdiomaService
  ) {
    this.translate.setDefaultLang(this.activeLang);
  }

  ngOnInit() {

  
    this.updateDataSource(this.entidadLista);
    // this.dataSource.data = this.entidadLista;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });
  }

  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }

  openDialog(row?): void {
    let editar: boolean = false;
    if (row) { editar = true; } else { editar = false; }

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.position = { left: `${rect.left}px`, top: `${rect.bottom - 50}px` };
    dialogConfig.width = '80%';
    dialogConfig.height = '500px';

    dialogConfig.data = {
      entidades: this.entidadLista,
      tipoBase: this.databaseTipoVal,
      editarRelacion: editar,
      relaxion1: row
    };

    const dialogRef = this.dialog.open(CreateEntidadComponent, dialogConfig);
    let listaTemporal: Array<Entidades> = this.entidadLista.filter(x => x != row);

    dialogRef.afterClosed().subscribe((val: Entidades) => {

      if (val !== undefined) {
        if (!editar) {

          this.entidadLista.push(val);
          this.cambioEntidad.emit(val);
          this.cambioEntidades.emit(this.entidadLista);
        } else {
          this.entidadLista = new Array<Entidades>();
          this.entidadLista = listaTemporal;
          this.entidadLista.push(val);
          this.cambioEntidades.emit(this.entidadLista);
        }
        console.log(this.entidadLista);
        this.onSelectEntity(val);
        // this.dataSource.data = this.entidadLista;
        this.updateDataSource(this.entidadLista);
      }
    });
  }

  onDeleteRelacion(row) {
    let erraseList: Array<Entidades> = this.entidadLista.filter(x => x != row);
    this.entidadLista = new Array<Entidades>();
    this.entidadLista = erraseList;
    // this.dataSource.data = this.entidadLista;
    this.updateDataSource(this.entidadLista);
    this.cambioEntidades.emit(this.entidadLista);
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) { this.dataSource.paginator.firstPage(); }
  }

  onSelectEntity(row: Entidades) {
    console.log('toca entidad: ', row);
   // this.entidadServicio.entidad$.next(row)
   this.entidadServicio.nombreEvento$.emit(row); 
    }

  updateDataSource(lista: Array<Entidades>) {
    this.dataSource = new MatTableDataSource<Entidades>();
    this.dataSource.data = lista;
  }

}





