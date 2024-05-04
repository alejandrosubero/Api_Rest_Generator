import { Component, ViewChild, OnInit, Inject, ElementRef } from '@angular/core';
import { Entidades } from 'src/Model/entidad.model';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { EntidadService } from 'src/app/services/entidad.service';
import { saveAs } from 'file-saver';
import { NgxSpinnerService } from 'ngx-spinner';

//import 'acemodes';
import 'brace';
import 'brace/mode/sql';
import 'brace/index';
import 'brace/theme/eclipse';


@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrls: ['./code.component.css']
})

export class CodeComponent implements OnInit {

  jsonArea = '';
  ace: any;
  download = false;
  showAtributo = false;
  showRelaciones = false;
  showFiller = false;

  entidad: Entidades;
  jsonValor: Object;
  relacion: Relacion
  // entity: Entidades = new Entidades();
  grupoEntidadesPojosList: Array<Entidades> = new Array<Entidades>();
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  atributosList: Array<Atributo> = new Array<Atributo>();
  relacionList: Array<Relacion> = new Array<Relacion>();
  databaseTipoVal: number;  // mejorar el uso de este atributo

  // @ViewChild('drawer', { static: false }) codess: ElementRef;

  constructor(private servicesEntidad: EntidadService, private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.generateNEWJson();
  }

  generateNEWJson() {
    this.generateArchivo();
    this.jsonValor = this.archivo;
    // this.proyecto = JSON.parse(JSON.stringify(this.archivo));
  }



  generateArchivo() {
    this.archivo = new ArchivoBaseDatosPojo();
    this.archivo.autor = '';
    this.archivo.user = '';
    this.archivo.context = '';
    this.archivo.proyectoName = '';
    this.archivo.packageNames = '';
    this.archivo.description = '';
    this.archivo.wihtSegurity = false; // spring segurity o no
    this.archivo.dataBase = true; // true o false
    this.archivo.databaseTest = true; // usar databade test y Database
    this.archivo.databaseName = ''; // nombre de base de datos
    this.archivo.tipoDatabase = 1; // oracle = 2, Mysql = 1, h2 = 3.
    this.archivo.nativeMysql = false; // usar generador nativo de mysql
    this.archivo.javaVersion = 11;
    this.archivo.entidades = new Array<Entidades>();
    this.jSonInFormater();
  }

  get code() { return JSON.stringify(this.archivo, null, 2); }

  set code(v) {
    try {
      this.archivo = JSON.parse(v);
      this.jsonValor = this.archivo;
    } catch (e) {
      console.log('error occored while you were typing the JSON');
    }
  }

  onChange() {
    this.archivo = JSON.parse(this.jsonArea);
    this.jsonValor = this.archivo;
    this.grupoEntidadesPojosList = this.archivo.entidades;
    this.ValidRelacion();
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
  }

  ValidRelacion() {
    if (this.grupoEntidadesPojosList.length >= 2) { this.showRelaciones = true; }
  }

  ValidAtributo() {
    if (this.grupoEntidadesPojosList.length > 0) { this.showAtributo = true; }
  }

  createEntidad() {
    const entidad = new Entidades();
    entidad.isEntity = true;
    entidad.nombreClase = 'entidad';
    entidad.nombreTabla = '';
    entidad.paquete = 'entitys';
    entidad.atributos = new Array<Atributo>();
    entidad.relaciones = new Array<Relacion>();

    this.createAtributoInicial(entidad);
    this.grupoEntidadesPojosList.push(entidad);
    this.archivo.entidades = this.grupoEntidadesPojosList;
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
    this.jSonInFormater();
  }

  createPojo() {
    let entidad = new Entidades();
    entidad.isEntity = false;
    entidad.nombreClase = 'pojo';
    entidad.nombreTabla = '';
    entidad.paquete = 'pojo';
    entidad.atributos = new Array<Atributo>();
    entidad.relaciones = new Array<Relacion>();
    this.createAtributoInicial(entidad);
    this.grupoEntidadesPojosList.push(entidad);
    this.archivo.entidades = this.grupoEntidadesPojosList;
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
    this.jSonInFormater();
  }

  createAtributoInicial(entidad: Entidades) {
    let id = new Atributo();
    //   id.nombreClase = this.entidad.nombreClase; //este no esta en atributo
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
    entidad.atributos.push(id);
  }


  createAtributo() {
    let id: Atributo = new Atributo();
    id.nombreClase = this.entidad.nombreClase;
    id.sId = false;
    id.idName = 'id';
    id.modificadorExtra = 'static';
    id.ismodificadorExtra = false;
    id.tipoModificador = 'private';
    id.tipoDato = '';
    id.atributoName = '';
    id.nameColum = '';
    id.length = 25;
    id.generatedValue = false;
    id.tipoGeneratedValor = 'AUTO';
    id.sequenceGenerator = false;
    id.tipoGenerador = false;
    id.atributoNullable = true;
    id.atributoUpdatable = true;
    id.sequenseName = 'id_se';
    id.nameSequenceTable = 'mat_id';
    id.initialValue = 25;
    id.allocationSize = 1000;
    id.transiente = false;
    this.entidad.atributos.push(id);
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
    this.jSonInFormater();
  }

  entidadSelecte(entidad) {
    this.ValidAtributo();
    this.entidad = entidad;
  }


  createAtributo2() {
    let atributo: Atributo = new Atributo();
    this.entidad.atributos.push(atributo);
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
    this.jSonInFormater();
  }


  basetipo(namecolum): string {
    let names = '';
    if (this.databaseTipoVal === 2) { names = namecolum.toUpperCase(); } else { names = namecolum }
    return names;
  }

  generateNewRelacion() {
    let relacion: Relacion = new Relacion();
    relacion.mappedByRelacion = false;
    relacion.bidireccional = false;
    relacion.joinColumn = false;
    relacion.fetchType = false;
    relacion.isJoinTable = false;
    relacion.jointabaleTipo = false;
    relacion.orphanRemoval = false;
    relacion.mappedBy = '';
    relacion.nameClassRelacion = '';
    relacion.nameRelacion = '';
    relacion.relation = 'OneToOne';
    relacion.fetchTypes = 'FetchType.EAGER';
    relacion.joinColumnName = '';
    relacion.joinColumnNameReferencedColumnName = '';
    relacion.JoinTableName = '';
    relacion.joinColumnName2 = '';
    this.entidad.relaciones.push(relacion);
    // this.servicesEntidad.updateArchivoPojo(this.archivo);
    this.jSonInFormater();
  }

  // ======================================================= //
  onEnviar() {
    this.spinner.show();
    console.log('*JSOM* ', JSON.stringify(this.archivo));
    this.servicesEntidad.postP(this.archivo).subscribe((x: any) => {
      // console.log('RESPUESTA DEL SERVIDO =>> ', x);
      if (x) {
        this.download = true;
        this.spinner.hide();
      } else {
        console.log(x);
      }
    });
  }

  // ======================================================== //
  ondownload() {
    const name = this.archivo.proyectoName;
    this.servicesEntidad.download(name).subscribe(blob => {
      saveAs(blob, name + '.zip');
      this.download = false;
    });
  }


  // ============================================================ //
  jSonInFormater() {
    // tslint:disable-next-line: prefer-const
    // tslint:disable-next-line: one-variable-per-declaration
    let textoJSON = JSON.stringify(this.archivo), objeto;
    objeto = JSON.parse(textoJSON);
    const textoFormateado = JSON.stringify(objeto, undefined, 2);
    this.jsonArea = textoFormateado;
  }



}
