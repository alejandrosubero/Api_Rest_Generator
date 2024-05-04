import { Component, OnInit, ViewChild, Input, OnDestroy, Output, EventEmitter } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { TableLllenado } from 'src/Model/tableLlenado.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CreateAtributoComponent } from './create-atributo/create-atributo.component';
import { Atributo } from 'src/Model/atributo.model';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { Entidades } from 'src/Model/entidad.model';
import { EntidadComponent } from '../entidad/entidad.component';
import { ClienteComponent } from '../cliente/cliente.component';
import { Observable, Subject, Subscription } from 'rxjs';
import { EntidadService } from 'src/app/services/entidad.service';
import { TranslateService } from '@ngx-translate/core';
import { IdiomaService } from 'src/app/service/idioma.service';


@Component({
  selector: 'app-atributo',
  templateUrl: './atributo.component.html',
  styleUrls: ['./atributo.component.css']
})

export class AtributoComponent implements OnInit {

  entidadObservable$ = new Observable<Entidades>();
  nombreSucrip: Subscription;
  atributoTrue = false;
  dataTabla: TableLllenado = new TableLllenado();
  entity: Entidades = new Entidades();
  atributo: Atributo = new Atributo();

  displayedColumns: string[] = ['clase', 'nameColum', 'atributoName', 'tipoDato', 'aciones'];
  dataSource: MatTableDataSource<Atributo> = new MatTableDataSource<Atributo>();

  atributoLista: Array<Atributo> = new Array<Atributo>();
  databaseTipoVa = 1;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Output() cambioAtributo = new EventEmitter<Entidades>();
  @Input() databaseTipoVal: number;

  public activeLang = 'es';
  idio: Observable<string>;

  constructor(private dialog: MatDialog,
    private translate: TranslateService,
    private idiomas: IdiomaService,
    private entidadServicio: EntidadService) {
    this.translate.setDefaultLang(this.activeLang);
  }

  ngOnInit() {

//  this.entidadServicio.entidad$.subscribe(x => {
//   this.atributoTrue = true;
//       console.log('entidad recibida: ', x)
//     });
    
    this.dataSource.data = this.atributoLista;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.databaseTipoVa = this.databaseTipoVal;

   // console.log('En atributos la atributoLista==>', this.atributoLista);

    this.nombreSucrip = this.entidadServicio.nombreEvento$.subscribe(x => {
      this.atributoTrue = true;
      this.entity = x;
      this.atributoLista = this.entity.atributos;
      if (this.atributoLista.length == 0) { this.createAtributoInicial(); }
      this.updateDataSource();
      // this.entidadServicio.relacionEvento$.emit(this.entity);
    //  console.log('ATRIBUTO LISTA emitida ===>', this.atributoLista);
    });

    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });

  }


  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }


  createAtributoInicial() {
    let id = new Atributo();
    id.nombreClase = this.entity.nombreClase; //este no esta en atributo
    id.sId = true;
    id.idName = 'id';
    id.modificadorExtra = 'static';
    id.ismodificadorExtra = false;
    id.tipoModificador = 'private';
    id.tipoDato = 'Long';
    id.atributoName = id.idName;
    id.nameColum = this.basetipo(id.idName);
    id.length = 25;
    id.generatedValue = false;
    id.tipoGeneratedValor = 'AUTO';
    id.sequenceGenerator = true;
    id.tipoGenerador = false;
    id.atributoNullable = false;
    id.atributoUpdatable = true;
    id.sequenseName = 'id_secunce';
    id.nameSequenceTable = 'mat_id';
    id.initialValue = 25;
    id.allocationSize = 1000;
    id.transiente = false;
    this.entity.atributos.push(id);
  }

  basetipo(namecolum): string {
    let names = '';
    if (this.databaseTipoVal === 2) { names = namecolum.toUpperCase(); } else { names = namecolum }
    return names;
  }


  openDialog(row?: Atributo): void {

    let editar = false;
    if (row) { editar = true; } else { editar = false; }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.position = { left: `${rect.left}px`, top: `${rect.bottom - 50}px` };
    dialogConfig.width = '90%';
    dialogConfig.height = '80%';

    dialogConfig.data = {
      tipoBase: this.databaseTipoVal,
      atributos: this.atributoLista,
      entidad: this.entity,
      edit: editar,
      atributox: row
    };
    let atributoListTemporal: Array<Atributo> = this.atributoLista.filter(x => x != row);
    const dialogRef = this.dialog.open(CreateAtributoComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((val: Atributo) => {

      if (val !== undefined) {
        if (!editar) {
          this.atributoLista.push(val);
          this.onNewUpdateAtributo();
       //   console.log("Dialog output in Atributo 1:", this.entity);

        } else {
          this.atributoLista = atributoListTemporal;
          this.atributoLista.push(val);
          this.onNewUpdateAtributo();
        //  console.log("Dialog output in Atributo 2:", this.entity);
        }
      }
    });
  }

  onNewUpdateAtributo() {
    this.entity.atributos = this.atributoLista;
    this.dataSource.data = this.entity.atributos;
    this.cambioAtributo.emit(this.entity);
   // console.log('this.entity', this.entity)
  }


  onDeleteAtributo(row) {
   // console.log('acciones', row);
    let erraseList: Array<Atributo> = this.atributoLista.filter(x => x != row);
    this.atributoLista = erraseList;
    this.onNewUpdateAtributo();
  }


  updateDataSource() {
    this.dataSource.data = this.atributoLista;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  updateTableAtributo(datos: Entidades) {

   // console.log('updateTable(datos: Entidades)', datos);
    // this.entity = datos;
    // this.atributoLista = datos.atributos;
    // this.dataSource.data = this.at 
  }


  ngOnDestroy() {
    this.nombreSucrip.unsubscribe();

  }



}



