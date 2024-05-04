import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { IdiomaService } from 'src/app/service/idioma.service';
import { EntidadService } from 'src/app/services/entidad.service';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';

@Component({
  selector: 'app-manager-methods',
  templateUrl: './manager-methods.component.html',
  styleUrls: ['./manager-methods.component.css']
})
export class ManagerMethodsComponent implements OnInit, OnDestroy {

  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  private readonly unsubscribe$: Subject<void> = new Subject();
  public activeLang = 'es';
  isEdit = false;

  constructor(private dialogRef: MatDialogRef<ManagerMethodsComponent>,
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
    console.log('this.archivOngOnInit', this.archivo);
    this.servicioEntidad.articulo$.pipe(takeUntil(this.unsubscribe$)).subscribe(x => {
      this.archivo = x;
    });
  }

  onBack() {
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

}