import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Entidades } from 'src/Model/entidad.model';
import { TranslateService } from '@ngx-translate/core';
import { IdiomaService } from 'src/app/service/idioma.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-create-atributo',
  templateUrl: './create-atributo.component.html',
  styleUrls: ['./create-atributo.component.css']
})
export class CreateAtributoComponent implements OnInit {

  isId = false;
  ismodificadorExtra = false;
  isUpdatable = true;
  isNullable = true;
  isgeneratedValue = false;
  issequenceGenerator = false;
  istipoGenerador = false;
  istransiente = false;
  length = 200;


  onIdName = 'id';
  onModificadorExtra = '';
  ontipoDato = '';
  onatributoName = '';
  onnameColum: string = '';
  onsequenseName = 'CAMBIAR';
  onnameSequenceTable = 'CANBIAR';
  oninitialValue = 1;
  onallocationSize = 1000;


  firstForm: FormGroup;

  tipoModificadorList: string[] = ['private', 'public', 'protected'];
  tipoDatoList: string[] = ['Integer', 'Long', 'Double', 'String', 'Chat', 'Byte', 'Boolean', 'Date', 'Object'];
  tipoGeneratedValorList: string[] = ['AUTO', 'SEQUENCE'];
  modificadorExtrat = 'static';

  tipoBase: number = 1;
  editar = false;
  enviarAtributo = true;
  showAtributo = false;

  entidad: Entidades = new Entidades;
  atributo: Atributo = new Atributo;
  atributosList: Array<Atributo> = new Array<Atributo>();
  // entidadRef: Array<Entidades> = new Array<Entidades>();

  ontipoModificador = this.tipoModificadorList[0];
  ontipoGeneratedValor = this.tipoGeneratedValorList[0];

  public activeLang = 'es';
  idio: Observable<string>;

  constructor(
    private dialogRef: MatDialogRef<CreateAtributoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private translate: TranslateService,
    private idiomas: IdiomaService,
    private _formBuilder: FormBuilder) {
    this.translate.setDefaultLang(this.activeLang);
    if (data.edit) {
      this.editar = data.edit;
      this.atributo = data.atributox;
    }

    this.entidad = data.entidad;
    this.atributosList = this.entidad.atributos;
    this.dialogRef.disableClose = true;
    this.tipoBase = data.tipoBase;
    this.onBuilderform();
  }

  ngOnInit() {
    this.editarAtributo(this.atributo);
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });
  }

  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }

  showArtributos() {// creo que no es necesario para atributos +/-
    this.entidad.relaciones.length != 0 ? this.showAtributo = true : this.showAtributo = false;
  }


  onNoClick(): void {
    this.enviarAtributo = true;
    if (!this.editar) {
      let atributo = new Atributo();
      atributo = this.firstForm.value;
      // console.log(typeof atributo.length);
      // tslint:disable-next-line: triple-equals
      if (typeof atributo.length == 'string') {
        const lengthString = `${atributo.length}`;
        // tslint:disable-next-line: radix
        atributo.length = parseInt(lengthString);
      }

      atributo.nombreClase = this.entidad.nombreClase;
      this.atributoCheck(atributo);

      if (this.enviarAtributo) {
        this.dialogRef.close(atributo);
      } else {
        alert('No se puede guardar una relacion ya existente');
       }

    } else {
      this.enviarAtributo = true;
      this.atributo = this.firstForm.value;
       // console.log(typeof atributo.length);
      // tslint:disable-next-line: triple-equals
      if (typeof this.atributo.length == 'string') {
        const lengthString = `${this.atributo.length}`;
        // tslint:disable-next-line: radix
        this.atributo.length = parseInt(lengthString);
      }
      this.atributo.nombreClase = this.entidad.nombreClase;

      if (this.enviarAtributo) {
        this.dialogRef.close(this.atributo);
      } else {
        alert('No se puede guardar una relacion ya existente');
      }
    }
  }


  atributoCheck(atributox: Atributo) {
    this.atributosList.forEach(y => {
      if (y.atributoName === atributox.atributoName) { this.enviarAtributo = false; }
    });
  }



  onClose() { this.dialogRef.close(); }

  onBuilderform() {

    this.firstForm = this._formBuilder.group({
      sId: [this.isId, Validators.required],
      idName: ['id', Validators.required],
      modificadorExtra: [this.modificadorExtrat, Validators.required],
      ismodificadorExtra: [this.ismodificadorExtra, Validators.required],
      tipoModificador: [this.ontipoModificador, Validators.required],
      tipoDato: [this.ontipoDato, Validators.required],
      atributoName: [this.onatributoName, Validators.required],
      nameColum: [this.onnameColum, Validators.required],
      atributoUpdatable: [this.isUpdatable, Validators.required],
      atributoNullable: [this.isNullable, Validators.required],
      length: [this.length, Validators.required],
      generatedValue: [this.isgeneratedValue, Validators.required],
      tipoGeneratedValor: [this.ontipoGeneratedValor, Validators.required],
      sequenceGenerator: [this.issequenceGenerator, Validators.required],
      tipoGenerador: [this.istipoGenerador, Validators.required],
      sequenseName: [this.onsequenseName, Validators.required],
      nameSequenceTable: [this.onnameSequenceTable, Validators.required],
      initialValue: [this.oninitialValue, Validators.required],
      allocationSize: [this.onallocationSize, Validators.required],
      transiente: [this.istransiente, Validators.required],

    });
    // this.firstForm.valueChanges.subscribe(console.log);
  }

  sequense() { this.issequenceGenerator = this.firstForm.value.sequenceGenerator; }

  modificadorExtra() { this.ismodificadorExtra = this.firstForm.value.ismodificadorExtra; }

  editarAtributo(atributo: Atributo) { if (this.data.edit) { this.mapperRelacion(atributo); } }

  mapperRelacion(atributo: Atributo) {
    this.isId = atributo.sId;
    this.ismodificadorExtra = atributo.ismodificadorExtra;
    this.isUpdatable = atributo.atributoUpdatable;
    if (this.data.edit) { this.ontipoModificador = atributo.tipoModificador; }
    if (this.data.edit) { this.ontipoGeneratedValor; }
    this.isNullable = atributo.atributoNullable;
    this.isgeneratedValue = atributo.generatedValue;
    this.issequenceGenerator = atributo.sequenceGenerator;
    this.istipoGenerador = atributo.tipoGenerador;
    this.istransiente = atributo.transiente;
    this.length = atributo.length;
    this.onIdName = atributo.idName;
    this.onModificadorExtra = atributo.modificadorExtra;
    this.ontipoDato = atributo.tipoDato;
    this.onatributoName = atributo.atributoName;
    this.onnameColum = atributo.nameColum;
    this.onsequenseName = atributo.sequenseName;
    this.onnameSequenceTable = atributo.nameSequenceTable;
    this.oninitialValue = atributo.initialValue;
    this.onallocationSize = atributo.allocationSize;
    this.onBuilderform();
  }

  escribir(valor) {

    if (this.tipoBase === 2) {
      this.onnameColum = valor.toUpperCase();
    } else {
      this.onnameColum = valor;
    }
    // console.log('this.tipoBase', this.tipoBase);

  }










}

