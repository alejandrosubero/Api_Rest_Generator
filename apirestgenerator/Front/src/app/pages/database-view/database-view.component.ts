import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { IdiomaService } from 'src/app/service/idioma.service';
import { EntidadService } from 'src/app/services/entidad.service';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { Database } from 'src/Model/database.model';

@Component({
  selector: 'app-database-view',
  templateUrl: './database-view.component.html',
  styleUrls: ['./database-view.component.css']
})
export class DatabaseViewComponent implements OnInit, OnDestroy {

  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  private readonly unsubscribe$: Subject<void> = new Subject();
  public activeLang = 'es';
  isEdit = false;

  firstFormGroup: FormGroup;
  build = false;
  dataBaseUse = true;
  databaseTest:boolean;
  nativeMysql:boolean;
  databaseNameVal = '';
  tipoDatabase:number; //this.dataBaselist[1].tipoDatabase
  nativeMysqlVal = false;
  databaseTestVal = true;

  dataBaselist: Database[] = [
    { databaseName: 'oracle', tipoDatabase: 2 },
    { databaseName: 'Mysql', tipoDatabase: 1 },
    { databaseName: 'h2', tipoDatabase: 3 },
    { databaseName: 'SQL_Server', tipoDatabase: 4 }
  ];


  constructor(private dialogRef: MatDialogRef<DatabaseViewComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private translate: TranslateService,
              private idiomas: IdiomaService,
              private servicioEntidad: EntidadService) {

    this.translate.setDefaultLang(this.activeLang);
    this.isEdit = false;
  }

  ngOnInit(): void {
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });

    this.servicioEntidad.articulo$.pipe(takeUntil(this.unsubscribe$)).subscribe(x => {
      this.archivo = x;
      console.log('this.archivOngOnInit', this.archivo);

      if (this.archivo.databaseName != null || this.archivo.databaseName != undefined) {
        this.updateDatabase();
      }
      this.builderform();
    });
  }

  onBack() {
    if (this.firstFormGroup.valid) {
      this.saveChange();
    } else {
      alert('FALTAN DATOS POR AGREGAR');
    }
    this.dialogRef.close(true);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }

  builderform() {
    this.build = true;
    this.firstFormGroup = this._formBuilder.group({
      dataBase: [this.dataBaseUse, Validators.required],
      databaseTest: [this.databaseTest],
      nativeMysql: [this.nativeMysql],
      databaseNames: [this.databaseNameVal, Validators.required],
      tipoDatabase: [this.tipoDatabase, Validators.required]
    });
  }


  setforntValues() {
    if (this.build) {
      this.databaseTest = this.firstFormGroup.value.databaseTest;
      this.nativeMysql = this.firstFormGroup.value.nativeMysql;
    }
  }


  updateDatabase() {
    this.dataBaseUse = this.archivo.dataBase;
    this.databaseTest = this.archivo.databaseTest;
    this.nativeMysql = this.archivo.nativeMysql;
    this.databaseNameVal = this.archivo.databaseName;
    this.tipoDatabase = this.archivo.tipoDatabase;
  }


  saveChange() {
    this.archivo.dataBase = this.firstFormGroup.value.dataBase;
    this.archivo.nativeMysql = this.firstFormGroup.value.nativeMysql;
    this.archivo.databaseTest = this.firstFormGroup.value.databaseTest;
    this.archivo.tipoDatabase = this.firstFormGroup.value.tipoDatabase;
    this.archivo.databaseName = this.databaseNameVal;
    this.isEdit = true;
    this.servicioEntidad.updateArchivoPojo(this.archivo);
    console.log('************  this.archivo save java version', this.archivo);
  }


  databaseNameValor() {
    if (this.firstFormGroup.value.tipoDatabase == 2) {
      this.databaseNameVal = this.firstFormGroup.value.databaseNames.toUpperCase();
    } else {
      this.databaseNameVal = this.firstFormGroup.value.databaseNames.toLowerCase();
    }
  }




}
