import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { Entidades } from 'src/Model/entidad.model';
import { EntidadComponent } from '../entidad/entidad.component';
import { AtributoComponent } from '../atributo/atributo.component';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { RelacionComponent } from '../relacion/relacion.component';
import { Database } from 'src/Model/database.model';
import { EntidadService } from 'src/app/services/entidad.service';

import { saveAs } from 'file-saver';

// import 'acemodes';
// import 'brace';
// import 'brace/mode/sql';
// import 'brace/mode/JSON';
// import 'brace/index';
// import 'brace/theme/eclipse';


import { ToolClassPojo } from 'src/Model/tool-class-pojo .model';
import { NgxSpinnerService } from 'ngx-spinner';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ToolPanelComponent } from '../tool-panel/tool-panel.component';
import { Observable } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { IdiomaService } from 'src/app/service/idioma.service';
import { MethodManager } from 'src/Model/methodManager.model';
import { SaveService } from 'src/Model/save.model';
import { CapaPojo } from 'src/Model/capapojo.model';



@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  jsonArea = '';
  private _code: any;
  save: SaveService = new SaveService();
  ace: any;
  isLinear = false;
  isEntidad = true;
  isAtributo = false;
  isRelacion = false;
  download = false;
  useTools = false;
  methoddefaultValue: boolean = true;
  methodManager: MethodManager = new MethodManager();

  firstFormGroup: FormGroup;

  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();
  entidadesList: Array<Entidades> = new Array<Entidades>();
  atributosList: Array<Atributo> = new Array<Atributo>();
  relacionList: Array<Relacion> = new Array<Relacion>();
  ListEntidadesFinal: Array<Entidades> = new Array<Entidades>();

  dataBaselist: Database[] = [
    { databaseName: 'oracle', tipoDatabase: 2 },
    { databaseName: 'Mysql', tipoDatabase: 1 },
    { databaseName: 'h2', tipoDatabase: 3 },
    { databaseName: 'SQL_Server', tipoDatabase: 4 }
  ];

  public sprintVersion: Array<string> = [];

  public sprintVersionCapaPojo: Array<string> = [
    '2.2.6.RELEASE', '2.3.11.RELEASE', '2.3.12.BUILD-SNAPSHOT',
    '2.4.6', '2.4.7-SNAPSHOT', '2.5.0', '2.5.1-SNAPSHOT'
  ];

  public sprintVersionCapa07: Array<string> = [
    '1.5.22.RELEASE'
  ];

  javaversionListCapaPojo: number[] = [1.8, 11, 14];
  javaversionListCapa07: number[] = [1.7];
  javaversionlist: number[] = [];

  dataBaseUse = true;
  createCapaPojoForEntitys = true;
  createCapaJavaBase7 = false;
  wihtSegurityVal = false;
  nativeMysqlVal = false;
  databaseTestVal = true;
  packageNamesVal = 'com.';
  contexVa = '';
  databaseNameVal = '';
  databaseTipoVal: number;
  jsonValor: Object;
  proyecto: string;
  versionPrograma: string = '1.0.0.0';
  artifac = '';
  paqueteValid = false;
  public validFormPackage = false;

  artifacts: string = '';
  autor = '';
  user = '';
  proyectoName = '';
  packageNames = '';
  description = '';
  wihtSegurity = '';
  databaseTest = '';
  nativeMysql = '';
  build: boolean = false;
  modelT: string;

  @ViewChild(EntidadComponent, { static: false }) entidadRef: EntidadComponent;
  @ViewChild(AtributoComponent, { static: false }) atributiRef: AtributoComponent;
  @ViewChild(RelacionComponent, { static: true }) relacionRef: RelacionComponent;

  listTools: ToolClassPojo = new ToolClassPojo();

  public activeLang = 'es';
  idio: Observable<string>;

  constructor(private _formBuilder: FormBuilder,
    private servicesEntidad: EntidadService,
    private spinner: NgxSpinnerService,
    private dialog: MatDialog,
    private translate: TranslateService,
    private idiomas: IdiomaService
  ) {
    this.translate.setDefaultLang(this.activeLang);
    this.oncargaArchivo();
    this.setSprinAndJavaVersionList();
  }


  ngOnInit() {
    this.onBuilderform();
    this.idiomas.subject$.subscribe(x => {
      this.cambiarLenguaje(x);
    });
  }

  public cambiarLenguaje(lang) {
    this.activeLang = lang;
    this.translate.use(lang);
  }

  setSprinAndJavaVersionList() {
    if (this.archivo.capaPojo.createCapaJavaBase7 && this.archivo.javaVersion != 1.7) {
      this.sprintVersion = this.sprintVersionCapa07.slice();
      this.javaversionlist = this.javaversionListCapa07.slice();
      // this.firstFormGroup.value.javaVersion = this.javaversionlist[0];
      // this.firstFormGroup.value.springBootVersion = this.sprintVersion[0];
      this.setforntValues();
    } else {
      this.sprintVersion = this.sprintVersionCapaPojo.slice();
      this.javaversionlist = this.javaversionListCapaPojo.slice();
      this.setforntValues();
    }
    this.onBuilderform();
    this.guardarCambios();
  }

  setforntValues() {
    if (this.build) {
      this.autor = this.firstFormGroup.value.autor;
      this.user = this.firstFormGroup.value.user;
      this.proyectoName = this.firstFormGroup.value.proyectoName;
      this.packageNames = this.firstFormGroup.value.packageNames;
      this.description = this.firstFormGroup.value.description;
      this.wihtSegurity = this.firstFormGroup.value.wihtSegurity;
      this.databaseTest = this.firstFormGroup.value.databaseTest;
      this.nativeMysql = this.firstFormGroup.value.nativeMysql;
      this.artifacts = this.firstFormGroup.value.artifact;
    }
  }



  oncargaArchivo() {
    if (this.archivo) {
      this.archivo.entidades === undefined ? this.archivo.entidades = new Array<Entidades>() : this.entidadesList = this.archivo.entidades;
    } else {
      this.archivo = new ArchivoBaseDatosPojo();
      this.archivo.entidades = new Array<Entidades>();
      this.archivo.capaPojo = new CapaPojo();
      this.archivo.capaPojo.modelT = '';
      // console.log(this.archivo);
      this.entidadesList = this.archivo.entidades;
    }
  }


  onBuilderform() {
    this.build = true;
    this.firstFormGroup = this._formBuilder.group({
      autor: [this.autor, Validators.required],
      user: [this.user, Validators.required],
      context: [this.contexVa, Validators.required],
      proyectoName: [this.proyectoName, Validators.required],
      packageNames: [this.packageNames, [Validators.required]],
      description: [this.description, Validators.required],
      wihtSegurity: [this.wihtSegurity],
      dataBase: [this.dataBaseUse, Validators.required],
      databaseTest: [this.databaseTest],
      nativeMysql: [this.nativeMysql],
      javaVersion: [this.javaversionlist[0], Validators.required],
      springBootVersion: [this.sprintVersion[0], Validators.required],
      databaseNames: [this.databaseNameVal, Validators.required],
      tipoDatabase: [this.dataBaselist[1].tipoDatabase, Validators.required],
      artifact: [this.artifacts, Validators.required],
      prograntVersion: [this.versionPrograma],


      // verificar si se requiere database separado del tipo o solo el tipo
      // entidades: ['', Validators.required], // este hay que colocarlo en un array de formGroup
    });

    this.firstFormGroup.valueChanges.subscribe(x => {
      this.validPackageFormation();
      // this.databaseasig();
      this.validFormAndPackage();
    });
  }



  validFormAndPackage() {
    if (this.firstFormGroup.valid && this.paqueteValid) {
      this.validFormPackage = true;
    } else {
      this.validFormPackage = false;
    }
  }

  entidadLista(event: Entidades) {
    this.isAtributo = true;
    // this.entidadesList.push(event);
  }


  onEntidadListaUpdateBorrada(entityNew: Array<Entidades>) {
    this.entidadesList = new Array<Entidades>();
    this.entidadesList = entityNew;
    if (this.entidadesList.length >= 2) {
      this.isRelacion = true;
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
    // if(this.artifac.length == 0){
    //   this.artifac = this.firstFormGroup.value.proyectoName.replace(/ /g, '');
    // }
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

  validPackageFormation() {
    let packaget = this.firstFormGroup.value.packageNames;
    let splitted = packaget.split('.');
    if (splitted.length == 3 && splitted[0].length > 0 && splitted[1].length > 0 && splitted[2].length > 0) {
      this.paqueteValid = true;
    }
  }

  databaseNameValor() {
    if (this.firstFormGroup.value.dataBase == 2) {
      this.databaseNameVal = this.firstFormGroup.value.databaseNames.toUpperCase();
    } else {
      this.databaseNameVal = this.firstFormGroup.value.databaseNames.toLowerCase();
    }
  }

  databaseasig() {
    this.databaseTipoVal = this.firstFormGroup.value.tipoDatabase;
  }

  onResetJson() {
    // this.ListEntidadesFinal = new Array<Entidades>();
  }




  generatePojosOfEntity(isEntity: boolean, nombreClase: string, nombreTabla: string,
    atributos: Array<Atributo>, relaciones: Array<Relacion>): Entidades {
    let en = new Entidades();
    en.isEntity = false;
    en.nombreClase = nombreClase + 'Pojo';
    en.nombreTabla = nombreTabla;
    en.paquete = 'pojo';
    en.atributos = atributos;
    en.relaciones = this.generarPojosRelaciones(relaciones);
    return en;
  }

  generarPojosRelaciones(relaciones: Array<Relacion>) {
    const relac: Array<Relacion> = new Array<Relacion>();
    relaciones.forEach(x => {
      let relax: Relacion = new Relacion();
      relax = this.generarPojosRelacion(x.mappedByRelacion, x.mappedBy,
        x.bidireccional, x.nameClassRelacionar, x.nameClassRelacion,
        x.nameRelacion, x.relation, x.joinColumn, x.fetchType, x.fetchTypes,
        x.joinColumnName, x.isJoinTable, x.jointabaleTipo, x.joinColumnNameReferencedColumnName,
        x.JoinTableName, x.joinColumnName2, x.cascadeType, x.orphanRemoval);
      relac.push(relax);
    });
    return relac;
  }


  generarPojosRelacion(mappedByRelacion: boolean,
    mappedBy: string,
    bidireccional: boolean,
    nameClassRelacionar: string,
    nameClassRelacion: string,
    nameRelacion: string,
    relation: string,
    joinColumn: boolean,
    fetchType: boolean,
    fetchTypes: string,
    joinColumnName: string,
    isJoinTable: boolean,
    jointabaleTipo: boolean,
    joinColumnNameReferencedColumnName: string,
    JoinTableName: string,
    joinColumnName2: string,
    cascadeType: string,
    orphanRemoval: boolean): Relacion {

    let rela: Relacion = new Relacion();
    rela.mappedByRelacion = mappedByRelacion;
    rela.mappedBy = mappedBy;
    rela.bidireccional = bidireccional;
    rela.nameClassRelacionar = nameClassRelacionar + 'Pojo';
    rela.nameClassRelacion = nameClassRelacion + 'Pojo';
    rela.nameRelacion = nameRelacion;
    rela.relation = relation;
    rela.joinColumn = joinColumn;
    rela.fetchType = fetchType;
    rela.fetchTypes = fetchTypes;
    rela.joinColumnName = joinColumnName;
    rela.isJoinTable = isJoinTable;
    rela.jointabaleTipo = jointabaleTipo;
    rela.joinColumnNameReferencedColumnName = joinColumnNameReferencedColumnName;
    rela.JoinTableName = JoinTableName;
    rela.joinColumnName2 = joinColumnName2;
    rela.cascadeType = cascadeType;
    rela.orphanRemoval = orphanRemoval;
    return rela;
  }


  llenadoEntityListPojos() {
    this.entidadesList.forEach(x => {
      let endt: Entidades = this.generatePojosOfEntity(x.isEntity, x.nombreClase, x.nombreTabla,
        x.atributos, x.relaciones);
      this.ListEntidadesFinal.push(x);
      this.ListEntidadesFinal.push(endt);
    });
  }

  //TODO: IS NESESARY SAVE THE DATA DINAMICAL AND DON'T USE A NEW ArchivoBaseDatosPojo BECASUS YOU LOSE WORK A DATA 
  guardarCambios() {
    this.archivo = new ArchivoBaseDatosPojo();
    this.archivo = this.firstFormGroup.value;
    if (this.archivo.toolClassPojo === null || this.archivo.toolClassPojo === undefined) {
      this.archivo.toolClassPojo = new ToolClassPojo();
    }
    if (this.archivo.capaPojo === null || this.archivo.capaPojo == undefined) {
      this.archivo.capaPojo = new CapaPojo();
    }
    this.archivo.entidades = this.entidadesList;
    this.archivo.toolClassPojo = this.listTools;
    this.archivo.capaPojo.createCapaPojoForEntitys = this.createCapaPojoForEntitys;
    this.archivo.capaPojo.createCapaJavaBase7 = this.createCapaJavaBase7;
    this.archivo.capaPojo.modelT = this.modelT;
    this.archivo.isToolActive = this.useTools;
    // this.archivo.entidades = this.ListEntidadesFinal;
    this.archivo.methoddefaultValue = this.methoddefaultValue;
    this.archivo.methodManager = this.methodManager;
    this.archivo.springBootVersion = this.firstFormGroup.value.springBootVersion;
    // console.log('relese ', this.firstFormGroup.value);
  }

  // NOTA: TODO: HAY QUE HACER QUE AL AGREGAR UN JSON TDOS LOS CAMPOS SE LLENEN CON EL VALOR DEL JSON.
  get code() { return JSON.stringify(this.archivo, null, 2); }

  set code(v) {
    try {
      this.archivo = JSON.parse(v);
      this.jsonValor = this.archivo;
      this.chargeJson(this.archivo);
    } catch (e) {
      console.log('error occored while you were typing the JSON');
    };
  }

  chargeJson(archivo: ArchivoBaseDatosPojo) {

    let listaAtributos = new Array<Atributo>();
    let valor = 0;
    archivo.entidades.forEach(x => {
      if (x !== undefined && x !== null) {
        valor++;
        x.atributos.forEach(y => { listaAtributos.push(y); });
      }
    });

    this.firstFormGroup.get('autor').setValue(archivo.autor);
    this.firstFormGroup.get('user').setValue(archivo.user);
    this.firstFormGroup.get('context').setValue(archivo.context);
    this.firstFormGroup.get('proyectoName').setValue(archivo.proyectoName);
    this.firstFormGroup.get('packageNames').setValue(archivo.packageNames);
    this.firstFormGroup.get('description').setValue(archivo.description);
    this.firstFormGroup.get('wihtSegurity').setValue(archivo.wihtSegurity);
    this.firstFormGroup.get('javaVersion').setValue(archivo.javaVersion);
    this.firstFormGroup.get('sprintVersion').setValue(archivo.springBootVersion);
    this.firstFormGroup.get('dataBase').setValue(archivo.dataBase);
    this.firstFormGroup.get('nativeMysql').setValue(archivo.nativeMysql);
    this.firstFormGroup.get('databaseTest').setValue(archivo.databaseTest);
    this.firstFormGroup.get('databaseName').setValue(archivo.dataBase);
    this.firstFormGroup.get('tipoDatabase').setValue(archivo.tipoDatabase);
    this.firstFormGroup.get('artifact').setValue(archivo.artifact);
    this.firstFormGroup.get('prograntVersion').setValue(archivo.prograntVersion);

    this.createCapaPojoForEntitys = archivo.capaPojo.createCapaPojoForEntitys;
    this.createCapaJavaBase7 = archivo.capaPojo.createCapaJavaBase7;
    this.entidadesList = archivo.entidades;
    this.modelT = archivo.capaPojo.modelT;

    this.entidadRef.entidadLista = this.entidadesList;
    this.entidadRef.updateDataSource(this.entidadRef.entidadLista);
    this.isAtributo = true;
    this.isRelacion = true;
  }



  onEnviar() {
    this.spinner.show();
    this.archivo.wihtSegurity = false;
    this.archivo.capaPojo.savePojo = this.save.controlSave();
    console.log('*JSOM* ', JSON.stringify(this.archivo));
    this.servicesEntidad.postP(this.archivo).subscribe((x: any) => {
      // console.log('RESPUESTA DEL SERVIDO =>> ', x);
      if (x) {
        this.download = true;
        this.spinner.hide();
      } else {
        console.log(x);
      }
    }, err => {
      console.log('Something went wrong!');
      this.spinner.hide();
      alert('Something went wrong!');
    });
  }

  ondownload() {
    let name = this.archivo.proyectoName;
    this.servicesEntidad.download(name).subscribe(blob => {
      saveAs(blob, name + '.zip');
      this.download = false;
    });
  }



  onEntidadCupdateContent(entyRec: Array<Entidades>, entyOrig: Array<Entidades>) { }
  onEntidadAtributo(atriRec: Array<Atributo>, atriOrig: Array<Atributo>) { }

  updateLista(entidad) {
    this.entidadesList.forEach(x => {
      //  console.log(entidad); //estaba borrado y no se si funciona sin esta funcion
      //  console.log(this.entidadesList);
    });
  }


  openDialogTools(): void {
    let editar = false;
    if (this.archivo.toolClassPojo) { editar = true; } else { editar = false; }
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '85%';
    dialogConfig.height = '85%';
    dialogConfig.data = {
      atributosTool: this.archivo,
      edit: editar,
    };
    const dialogRef = this.dialog.open(ToolPanelComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((tools: ArchivoBaseDatosPojo) => {
      if (tools !== undefined) {
        this.archivo.toolClassPojo = tools.toolClassPojo;
        this.archivo.isToolActive = tools.isToolActive;
        this.archivo.capaPojo.createCapaPojoForEntitys = tools.capaPojo.createCapaPojoForEntitys;
        this.archivo.capaPojo.createCapaJavaBase7 = tools.capaPojo.createCapaJavaBase7;
        this.modelT = tools.capaPojo.modelT;
        this.archivo.capaPojo.modelT = this.modelT;
        this.methoddefaultValue = tools.methoddefaultValue;
        this.methodManager = tools.methodManager;
        this.listTools = tools.toolClassPojo;
        this.useTools = tools.isToolActive;
        this.createCapaJavaBase7 = tools.capaPojo.createCapaJavaBase7;
        this.createCapaPojoForEntitys = tools.capaPojo.createCapaPojoForEntitys;
        this.setSprinAndJavaVersionList();
      }
    });
  }

  generateJson() {
    // this.llenadoEntityListPojos();
    this.guardarCambios();
    this.jSonInFormater();
    // this.proyecto = JSON.parse(JSON.stringify(this.archivo));
    this.jsonValor = this.archivo;
    console.log(this.jsonValor);
  }

  jSonInFormater() {
    // tslint:disable-next-line: prefer-const
    // tslint:disable-next-line: one-variable-per-declaration
    let textoJSON = JSON.stringify(this.archivo), objeto;
    objeto = JSON.parse(textoJSON); // Teniendo un objeto...
    const textoFormateado = JSON.stringify(objeto, undefined, 2); // Lo convertimos a JSON formateado con 2 espacios
    this.jsonArea = textoFormateado;
  }

  onChange() {
    try {
      this.archivo = JSON.parse(this.jsonArea);
      this.chargeJson(this.archivo);
      this.servicesEntidad.updateArchivoPojo(this.archivo);
    } catch (e) {
      console.log('error occored while you were typing the JSON');
    }
  }



}
