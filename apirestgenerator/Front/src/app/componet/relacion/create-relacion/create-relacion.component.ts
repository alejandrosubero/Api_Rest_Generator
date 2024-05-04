import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { Relacion } from 'src/Model/relacion.model';
import { MatDialogRef, MAT_DIALOG_DATA, } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Entidades } from 'src/Model/entidad.model';
import { Atributo } from 'src/Model/atributo.model';

@Component({
  selector: 'app-create-relacion',
  templateUrl: './create-relacion.component.html',
  styleUrls: ['./create-relacion.component.css']
})
export class CreateRelacionComponent implements OnInit {

  firstForm: FormGroup;
  atributo: Atributo;

  isMappedByRelacion: boolean = false;
  isBidireccional: boolean = false;
  isJoinColumn: boolean = false;
  isFetchType: boolean = false;
  isIsJoinTable: boolean = false;
  isJointabaleTipo: boolean = false;
  isOrphanRemoval: boolean = false;

  // onMappedBy = '';
  // onNameClassRelacion = '';
  // onNameRelacion = '';
  // onRelation = '';
  // onFetchTypes = '';
  // onJoinColumnName = '';
  // onJoinColumnNameReferencedColumnName = '';
  // onJoinTableName = '';
  // onJoinColumnName2 = '';

  editar = false;
  enviarRelacion = true;
  showRelacion = false;

  relationList: string[] = ['OneToOne', 'OneToMany', 'ManyToOne', 'ManyToMany'];
  fetchTypesList: string[] = ['FetchType.LAZY', 'FetchType.EAGER'];
  cascadeTypeList: string[] = ['ALL', 'PERSIST', 'MERGE', 'REMOVE', 'REFRESH', 'DETACH', 'REPLICATE', 'SAVE_UPDATE', 'LOCK'];
  entidadList: Array<Entidades> = new Array<Entidades>();
  entidadRef: Array<Entidades> = new Array<Entidades>();
  relacionesLista: Array<Relacion> = new Array<Relacion>();

  entidad: Entidades = new Entidades();
  relacion: Relacion = new Relacion();


  constructor(
    private dialogRef: MatDialogRef<CreateRelacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _formBuilder: FormBuilder) {

    if (data.editarRelacion) {
      this.editar = data.editarRelacion;
      this.relacion = data.relaxion1;
    }
    this.entidad = data.entidad;
    this.entidadRef = data.entidades;
    this.entidadList = this.entidadRef.filter(x => x != this.entidad);
    this.dialogRef.disableClose = true;
    this.onBuilderform();
  }


  ngOnInit() {
    this.showRelaciones();
    this.editarRelacion(this.relacion);
  }


  showRelaciones() {
    this.entidad.relaciones.length != 0 ? this.showRelacion = true : this.showRelacion = false;
  }


  relacionesFilter(relaxion: Relacion) {
    debugger
    let entidadChek: Array<Entidades> = this.entidadRef.filter(x => x == this.entidad);
    entidadChek.forEach(x => {
      x.relaciones.forEach(re => {
        if (relaxion.nameClassRelacion === re.nameClassRelacion && !this.editar) {
          this.enviarRelacion = false;
        } else { this.enviarRelacion = true; }
      });
    });
  }



  onSave(): void {
     if (!this.editar) {
      let relacion = new Relacion();
      relacion = this.firstForm.value;
      this.relacionesFilter(relacion)
      if (this.enviarRelacion) {
        this.dialogRef.close(relacion);
      } else { alert('No se puede guardar una relacon ya existente'); }

    } else {
      this.relacion = this.firstForm.value;
      this.relacionesFilter(this.relacion)
      if (this.enviarRelacion) {
        this.dialogRef.close(this.relacion);
      } else { alert('No se puede guardar una relacon ya existente'); }
    }
  }

  onClose() { this.dialogRef.close(); }

  onBuilderform() {
    this.firstForm = this._formBuilder.group({
      nameClassRelacionar: [this.entidad.nombreClase, Validators.required],
      mappedByRelacion: [this.relacion.mappedByRelacion, Validators.required],
      mappedBy: [this.relacion.mappedBy, Validators.required],
      bidireccional: [this.relacion.bidireccional, Validators.required],
      nameClassRelacion: [this.relacion.nameClassRelacion, Validators.required],
      nameRelacion: [this.relacion.nameRelacion, Validators.required],
      relation: [this.relacion.relation, Validators.required],
      joinColumn: [this.relacion.joinColumn, Validators.required],
      fetchType: [this.relacion.fetchType, Validators.required],
      fetchTypes: [this.relacion.fetchTypes, Validators.required],
      joinColumnName: [this.relacion.joinColumnName, Validators.required],
      isJoinTable: [this.relacion.isJoinTable, Validators.required],
      jointabaleTipo: [this.relacion.jointabaleTipo, Validators.required],
      joinColumnNameReferencedColumnName: [this.relacion.joinColumnNameReferencedColumnName, Validators.required],
      JoinTableName: [this.relacion.JoinTableName, Validators.required],
      joinColumnName2: [this.relacion.joinColumnName2, Validators.required],
      cascadeType: [this.relacion.cascadeType, Validators.required],
      orphanRemoval: [this.relacion.orphanRemoval, Validators.required],
    });
    // this.firstForm.valueChanges.subscribe();
    this.firstForm.get('fetchType').valueChanges.subscribe(x => {
      this.isFetchType = this.firstForm.get('fetchType').value;
    });

  }


  isJoinTable() { this.relacion.isJoinTable = this.firstForm.value.isJoinTable; }
  
  mappedByRelacion() {
   //  this.relacion.mappedByRelacion = this.firstForm.get('mappedByRelacion').value; 
     this.isMappedByRelacion = this.firstForm.get('mappedByRelacion').value;
    }

    // (change)="fetchType()"
  fetchType() { this.relacion.fetchType = this.firstForm.value.fetchType; }

  editarRelacion(relacion: Relacion) { 
    if (this.data.editarRelacion) { 
      this.onBuilderform(); 
    } 
  }





}


