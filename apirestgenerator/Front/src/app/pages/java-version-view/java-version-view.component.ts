import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { IdiomaService } from 'src/app/service/idioma.service';
import { EntidadService } from 'src/app/services/entidad.service';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';

@Component({
  selector: 'app-java-version-view',
  templateUrl: './java-version-view.component.html',
  styleUrls: ['./java-version-view.component.css']
})
export class JavaVersionViewComponent implements OnInit, OnDestroy {

  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  private readonly unsubscribe$: Subject<void> = new Subject();
  public activeLang = 'es';
  isEdit = false;
  firstFormGroup: any;

  public sprintVersionCapaPojo: Array<string> = [
    '1.5.22.RELEASE', '2.2.6.RELEASE', '2.3.11.RELEASE', '2.3.12.BUILD-SNAPSHOT',
    '2.4.6', '2.4.7-SNAPSHOT', '2.5.0', '2.5.1-SNAPSHOT'
  ];
  javaversionListCapaPojo: number[] = [1.7, 1.8, 11, 14];


  javaversionlist: number[] = [];
  sprintVersion: Array<string> = [];

  javaVersionVar;
  springBootVersionVar;

  constructor(private dialogRef: MatDialogRef<JavaVersionViewComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private translate: TranslateService,
              private idiomas: IdiomaService,
              private servicioEntidad: EntidadService) {

    this.translate.setDefaultLang(this.activeLang);
    this.isEdit = false;

    this.sprintVersion = this.sprintVersionCapaPojo;
    this.javaversionlist = this.javaversionListCapaPojo;

  }

  ngOnInit(): void {
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });

    this.servicioEntidad.articulo$.pipe(takeUntil(this.unsubscribe$)).subscribe(x => {
      this.archivo = x;
      this.setSprinAndJavaVersionList();
      console.log('this.archivOngOnInit', this.archivo);
    });
  }

  onBack() {
    this.saveChange();
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

  onBuilderform() {
    this.firstFormGroup = this._formBuilder.group({
      javaVersion: [this.springBootVersionVar, Validators.required],
      springBootVersion: [this.javaVersionVar, Validators.required],
    });

    this.firstFormGroup.get('javaVersion').valueChanges.subscribe(X => {
      console.log('load ...');

      if (this.firstFormGroup.get('javaVersion').value === 1.7) {
        console.log('ingreso ...');
        this.firstFormGroup.get('springBootVersion').setValue(this.sprintVersion[0]);
      }

     // tslint:disable-next-line: max-line-length
      if (this.firstFormGroup.get('javaVersion').value !== 1.7 && this.firstFormGroup.get('springBootVersion').value === this.sprintVersion[0]) {
         this.firstFormGroup.get('springBootVersion').setValue('');
      }

    });
    
  }


  setSprinAndJavaVersionList() {
    if (this.archivo.springBootVersion != null || this.archivo.springBootVersion !== undefined) {
      this.javaVersionVar = this.archivo.springBootVersion;
      this.springBootVersionVar = this.archivo.javaVersion;
    } else {
      this.javaVersionVar = this.sprintVersion[1];
      this.springBootVersionVar = this.javaversionlist[1];
    }
    this.onBuilderform();
  }

  saveChange() {
    this.archivo.springBootVersion = this.firstFormGroup.get('springBootVersion').value;
    this.archivo.javaVersion = this.firstFormGroup.get('javaVersion').value;
    this.isEdit = true;
    this.servicioEntidad.updateArchivoPojo(this.archivo);
    console.log('************  this.archivo save java version', this.archivo);
  }




}
