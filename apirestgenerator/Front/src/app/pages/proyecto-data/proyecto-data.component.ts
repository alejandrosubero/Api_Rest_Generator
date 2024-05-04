import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { IdiomaService } from 'src/app/service/idioma.service';
import { EntidadService } from 'src/app/services/entidad.service';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';

@Component({
  selector: 'app-proyecto-data',
  templateUrl: './proyecto-data.component.html',
  styleUrls: ['./proyecto-data.component.css']
})
export class ProyectoDataComponent implements OnInit, OnDestroy {


  firstFormGroup: FormGroup;
  artifacts: string = '';
  autor = '';
  user = '';
  proyectoName = '';
  packageNames = '';
  description = '';
  packageNamesVal = 'com.';
  contexVa = '';
  proyecto: string;
  versionPrograma: string = '1.0.0.0';
  artifac = '';
  public validFormPackage = false;
  paqueteValid = false;
  public activeLang = 'es';
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  private readonly unsubscribe$: Subject<void> = new Subject();
  isEdit = false;
  jsonArea = '';

  constructor(private dialogRef: MatDialogRef<ProyectoDataComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private translate: TranslateService,
              private idiomas: IdiomaService, private servicioEntidad: EntidadService,
              private router: Router,
              ) {
              this.translate.setDefaultLang(this.activeLang);
              this.isEdit = false;
  }

  ngOnInit(): void {
    this.onBuilderform();
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });
    console.log('this.archivOngOnInit', this.archivo)
    this.servicioEntidad.articulo$.pipe(takeUntil(this.unsubscribe$)).subscribe(x => {
      this.archivo = x;
      if (!this.isEdit && this.archivo.packageNames != null
          && this.archivo.packageNames !== ''
          && this.archivo.packageNames !== 'com.') {
        this.editChange();
      }
    });
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
      autor: [this.autor, Validators.required],
      user: [this.user, Validators.required],
      context: [this.contexVa, Validators.required],
      proyectoName: [this.proyectoName, Validators.required],
      packageNames: [this.packageNames, [Validators.required]],
      description: [this.description, Validators.required],
      artifact: [this.artifacts, Validators.required],
      prograntVersion: [this.versionPrograma]
    });
    this.firstFormGroup.valueChanges.subscribe(x => {
      this.validPackageFormation();
      this.validFormAndPackage();
    });


  }


  saveChange() {
    this.archivo.proyectoName = this.firstFormGroup.get('proyectoName').value;
    this.archivo.prograntVersion = this.firstFormGroup.get('prograntVersion').value;
    this.archivo.description = this.firstFormGroup.get('description').value;
    this.archivo.artifact = this.firstFormGroup.get('artifact').value;
    this.archivo.context = this.firstFormGroup.get('context').value;
    this.archivo.packageNames = this.firstFormGroup.get('packageNames').value;
    this.archivo.autor = this.firstFormGroup.get('autor').value;
    this.archivo.user = this.firstFormGroup.get('user').value;

    this.isEdit = true;
    this.servicioEntidad.updateArchivoPojo(this.archivo);
    console.log('this.archivo', this.archivo);
  }


  editChange() {
    this.isEdit = true;
    this.artifac = this.archivo.artifact;
    this.contexVa = this.archivo.context;
    this.packageNamesVal = this.archivo.packageNames;
    this.autor = this.archivo.autor;
    this.user = this.archivo.user;
    this.proyectoName = this.archivo.proyectoName;
    this.description = this.archivo.description;
    this.versionPrograma = this.archivo.prograntVersion;
    this.onBuilderform();
  }


  onEmitToolSelection() {
    this.saveChange();
    this.dialogRef.close(true);
  }


  onClose() { this.dialogRef.close(false); }



  onBack() {
    this.saveChange();
    this.dialogRef.close(true);
   // this.router.navigateByUrl("/panel");
  }


  validPackageFormation() {
    let packaget = this.firstFormGroup.value.packageNames;
    let splitted = packaget.split('.');
    if (splitted.length == 3 && splitted[0].length > 0 && splitted[1].length > 0 && splitted[2].length > 0) {
      this.paqueteValid = true;
    }
  }


  validFormAndPackage() {
    if (this.firstFormGroup.valid && this.paqueteValid) {
      this.validFormPackage = true;
    } else {
      this.validFormPackage = false;
    }
  }


  paqueteName() {
    let p = this.firstFormGroup.value.context.replace(/ /g, '');
    let inicialVal = 'com.' + p + '.' + this.artifac;
    this.packageNamesVal = inicialVal.toLowerCase();
  }

  paqueteNameCotex() {
    let p = this.contexVa;
    if (p.length == 0) {
      p = this.firstFormGroup.value.proyectoName.replace(/ /g, '');
    }
    let inicialVal = 'com.' + p + '.' + this.artifac;
    this.packageNamesVal = inicialVal.toLowerCase();
  }


  paqueteNameArtifac() {
    let p = this.firstFormGroup.value.context.replace(/ /g, '');
    let inicialVal = 'com.' + p + '.' + this.artifac;
    this.packageNamesVal = inicialVal.toLowerCase();
  }


  completePakagueName() {
    setTimeout(() => {
      if (this.contexVa.length == 0) {
        this.contexVa = this.firstFormGroup.value.proyectoName.replace(/ /g, '');
      }
      if (this.artifac.length == 0) {
        this.contexVa = this.firstFormGroup.value.proyectoName.replace(/ /g, '');
      }
    }, 800);
  }


  paqueteedid() {
    let packageNamesVal1 = this.packageNamesVal.toLowerCase();
    this.firstFormGroup.get('packageNames').setValue(this.packageNamesVal.toLowerCase());
    let splitted = packageNamesVal1.split('.');
    this.contexVa = splitted[1];
    this.artifac = splitted[2];
  }


  test() {
    this.saveChange();
    // tslint:disable-next-line: one-variable-per-declaration
    let textoJSON = JSON.stringify(this.archivo), objeto;

    // Teniendo un objeto...
    objeto = JSON.parse(textoJSON);

    // Lo convertimos a JSON formateado con 2 espacios
    const textoFormateado = JSON.stringify(objeto, undefined, 2);

    // Imprimimos
   //  document.write('<pre>' + textoFormateado + '</pre>');
    this.jsonArea = textoFormateado;
//textArea.html = true;
// textArea.htmlText = "<font color='#990000'>1.- Pregunta</font>";
  }


}
