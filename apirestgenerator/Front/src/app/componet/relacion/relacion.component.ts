import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CreateRelacionComponent } from './create-relacion/create-relacion.component';
import { Relacion } from 'src/Model/relacion.model';
import { Entidades } from 'src/Model/entidad.model';
import { Subscription } from 'rxjs';
import { EntidadService } from 'src/app/services/entidad.service';

// import { Atributo } from 'src/Model/atributo.model';
// import { ThrowStmt } from '@angular/compiler';
// import { EntidadComponent } from '../entidad/entidad.component';
// import { TableLllenado } from 'src/Model/tableLlenado.model';


@Component({
  selector: 'app-relacion',
  templateUrl: './relacion.component.html',
  styleUrls: ['./relacion.component.css']
})

export class RelacionComponent implements OnInit {

  entidadTrue = false;
  entity: Entidades = new Entidades;
  entidadLista: Array<Entidades> = new Array<Entidades>();



  displayedColumns: string[] = ['nameClassRelacionar', 'nameClassRelacion', 'relation', 'aciones'];
  dataSource: MatTableDataSource<Relacion> = new MatTableDataSource<Relacion>();
  relacionLista: Array<Relacion> = new Array<Relacion>();

  noSucrip: Subscription;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  @Output() cambioRelacion = new EventEmitter<Entidades>();
  @Input() listaentidades: Array<Entidades>;


  constructor(
    public dialog: MatDialog,
    private entidadServicio: EntidadService) { }


  ngOnInit() {
    this.dataSource.data = this.relacionLista;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.noSucrip = this.entidadServicio.nombreEvento$.subscribe(x => {
      this.entity = x;
      this.relacionLista = this.entity.relaciones;
      if (this.listaentidades.length !== 0) {
        this.entity = x;
        this.entidadLista = this.listaentidades;
      }
      this.updateDataSource();
    });
  }



  openDialog(row?): void {

    let editar: boolean = false;
    if (row) { editar = true; } else { editar = false; }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.position = { left: `${rect.left}px`, top: `${rect.bottom - 50}px` };
    dialogConfig.width = '90%';
    dialogConfig.height = '70%';

    dialogConfig.data = {
      entidades: this.listaentidades,
      entidad: this.entity,
      editarRelacion: editar,
      relaxion1: row
    };

    let listaTemporal: Array<Relacion> = this.entity.relaciones.filter(x => x != row);
    const dialogRef = this.dialog.open(CreateRelacionComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((val: Relacion) => {
      if (val !== undefined) {
        if (!editar) {
          this.entity.relaciones.push(val);
          this.updateNewAndEditRelacion(this.entity);
        } else {
          listaTemporal.push(val);
          this.entity.relaciones = listaTemporal;
          this.updateNewAndEditRelacion(this.entity);
        }
      }
    });

  }

  updateNewAndEditRelacion(entidadd: Entidades) {
    this.relacionLista = this.entity.relaciones;
    this.dataSource.data = this.relacionLista;
    this.cambioRelacion.emit(entidadd);
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) { this.dataSource.paginator.firstPage(); }
  }

  updateTable(datos: Entidades) {
    this.entity = datos;
    this.relacionLista = datos.relaciones;
    this.updateDataSource()
  }

  updateDataSource() {
    this.dataSource.data = this.relacionLista;
    this.dataSource.data = this.relacionLista;
    this.dataSource.paginator = this.paginator;
  }

  onDeleteRelacion(row) {
    let erraseList: Array<Relacion> = this.relacionLista.filter(x => x != row);
    this.relacionLista = erraseList;
    this.entity.relaciones = this.relacionLista;
    this.updateTable(this.entity);
    this.cambioRelacion.emit(this.entity);
  }




}
