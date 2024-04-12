import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Entidades } from 'src/Model/entidad.model';
import { EntidadService } from 'src/app/services/entidad.service';
import { Observable } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { IdiomaService } from 'src/app/service/idioma.service';


@Component({
  selector: 'app-create-entidad',
  templateUrl: './create-entidad.component.html',
  styleUrls: ['./create-entidad.component.css']
})
export class CreateEntidadComponent implements OnInit {

  public activeLang = 'es';
  idio: Observable<string>;

  isChecked = true;
  isDeleteChecked = false;
  isSerializableChecke = false;
  firstForm: FormGroup;
  entidad: Entidades;
  paquetelist: string[] = ['entitys', 'pojo'];
  tabla: string = '';
  tipoBase: number = 1;
  entidadName = '';
  editar = false;


  constructor(
    private servicesEntidad: EntidadService,
    private dialogRef: MatDialogRef<CreateEntidadComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private translate: TranslateService,
    private idiomas: IdiomaService,
    private _formBuilder: FormBuilder) {

    this.translate.setDefaultLang(this.activeLang);

    if (data.editarRelacion) {
      this.editar = true;
      this.entidad = data.relaxion1;
      this.isChecked = this.entidad.isEntity;
      this.isSerializableChecke = this.entidad.isSerializable;
      this.entidadName = this.entidad.nombreClase;
      this.tabla = this.entidad.nombreTabla;
      this.entidad.paquete == 'entitys' ? this.paquetelist[0] : this.paquetelist[1];
      this.isDeleteChecked = this.entidad.delete;
    }

    this.dialogRef.disableClose = false;
    this.tipoBase = data.tipoBase;
    this.onBuilderform();
  }


  ngOnInit() {
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });
  }


  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }

  onNoClick(): void {

    if (this.editar) {
      this.entidad.isEntity = this.firstForm.value.isEntity;
      this.entidad.nombreClase = this.servicesEntidad.UppercaseFirs(this.firstForm.value.nombreClase);
      this.entidad.nombreTabla = this.firstForm.value.nombreTabla;
      this.entidad.paquete = this.firstForm.value.paquete.toLowerCase();
      this.entidad.atributos = this.entidad.atributos;
      this.entidad.relaciones = this.entidad.relaciones;
      this.entidad.delete = this.entidad.delete
      this.entidad.isSerializable = this.firstForm.value.isSerializableChecke;
    } else {
      this.entidad = new Entidades();
      this.entidad.delete =  this.firstForm.value.isDelete;
      this.entidad.isEntity = this.firstForm.value.isEntity;
      this.entidad.isSerializable = this.firstForm.value.isSerializableChecke;
      this.entidad.nombreClase = this.servicesEntidad.UppercaseFirs(this.firstForm.value.nombreClase);
      this.entidad.nombreTabla = this.firstForm.value.nombreTabla;
      // this.entidad.paquete = this.firstForm.value.paquete.toLowerCase();
      this.entidad.isEntity ? this.entidad.paquete = this.paquetelist[0] : this.entidad.paquete = this.paquetelist[1];
      this.entidad.atributos = new Array<Atributo>();
      this.entidad.relaciones = new Array<Relacion>();
    }
    // console.log('this.entidad*******', this.entidad );
    this.dialogRef.close(this.entidad);
  }


  onClose() { this.dialogRef.close(); }

  onBuilderform() {
    this.firstForm = this._formBuilder.group({
      isEntity: [this.isChecked, Validators.required],
      isDelete: [this.isDeleteChecked, Validators.required],
      isSerializable: [this.isSerializableChecke, Validators.required],
      nombreClase: [this.entidadName, Validators.required],
      nombreTabla: [this.tabla, Validators.required],
      paquete: [this.paquetelist[0], Validators.required],
      
    });
    // this.firstForm.valueChanges.subscribe(console.log);
  }

  escribir(valor) {
    if (this.tipoBase === 2) {
      this.tabla = valor.toUpperCase();
    } else { this.tabla = valor.toLowerCase(); }
  }



}
