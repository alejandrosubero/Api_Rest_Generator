import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { MethodManager } from 'src/Model/methodManager.model';
import { ToolClassPojo } from 'src/Model/tool-class-pojo .model';

@Component({
  selector: 'app-tool-panel',
  templateUrl: './tool-panel.component.html',
  styleUrls: ['./tool-panel.component.css']
})
export class ToolPanelComponent implements OnInit {

  tools: ToolClassPojo;
  isChecked1: boolean;
  isChecked2: boolean;
  isChecked3: boolean;
  isChecked4: boolean;
  isChecked5: boolean;
  isChecked6: boolean;
  isChecked7: boolean;
  isChecked8: boolean;
  isChecked9: boolean;
  isChecked10: boolean;
  isChecked11: boolean;
  isChecked12: boolean;
 

  isMethoddefaultValue: boolean;
  isMethodFindByOrLoop: boolean;
  isMethodfindById: boolean;
  isMetohdSave: boolean;
  isMethodgetAll: boolean;
  isMethodDelete: boolean;
  isMethodUpdate: boolean;
  isMethodsaveOrUpdate: boolean;
  isMethodContaining: boolean;
  isMethodContainingRelacion: boolean;
  isMethodContainingRelacionNoBiDirectional: boolean;
  methodd: MethodManager;
  nameModeloPojo:string;

  editar = false;
  capaPojoForEntitys: boolean;
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();

  constructor(private dialogRef: MatDialogRef<ToolPanelComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    if (data.edit) {
      this.archivo = data.atributosTool;
      this.editar = data.edit;
      this.tools = data.atributosTool.toolClassPojo;
      this.isMethoddefaultValue = this.archivo.methoddefaultValue;
      this.methodd = this.archivo.methodManager;
      this.capaPojoForEntitys = data.atributosTool.createCapaPojoForEntitys;

    } else {
      this.tools = new ToolClassPojo();
      this.methodd = new MethodManager();
      this.capaPojoForEntitys = data.atributosTool.createCapaPojoForEntitys;
      if (data.atributosTool != null) { this.archivo = data.atributosTool; }

    }

  }

  ngOnInit() {
    // console.log('capaPojoForEntitys', this.archivo);
    this.startChargue();
  }


  startChargue() {
    this.nameModeloPojo ='';
    this.isChecked1 = this.tools.getPostCreateTool;
    this.isChecked2 = this.tools.archivosManamentTool;
    this.isChecked3 = this.tools.serverTcp;
    this.isChecked4 = this.tools.serverUdp;
    this.isChecked5 = this.tools.socketClientTcp;
    this.isChecked6 = this.tools.socketClientUdp;
    this.isChecked7 = this.tools.converterHex;
    this.isChecked8 = this.tools.leerExcel;
    this.isChecked9 = this.tools.crearExcel;
    this.isChecked10 = this.archivo.isToolActive;
    this.isChecked11 = this.archivo.capaPojo.createCapaPojoForEntitys;
    this.isChecked12 = this.archivo.capaPojo.createCapaJavaBase7;

    this.isMethoddefaultValue = this.archivo.methoddefaultValue;
    this.isMethodFindByOrLoop = this.methodd.methodFindByOrLoop;
    this.isMethodfindById = this.methodd.methodfindById;
    this.isMetohdSave = this.methodd.metohdSave;
    this.isMethodgetAll = this.methodd.methodgetAll;
    this.isMethodDelete = this.methodd.methodDelete;
    this.isMethodUpdate = this.methodd.methodUpdate;
    this.isMethodsaveOrUpdate = this.methodd.methodsaveOrUpdate;
    this.isMethodContaining = this.methodd.methodContaining;
    this.isMethodContainingRelacion = this.methodd.methodContainingRelacion;
    this.isMethodContainingRelacionNoBiDirectional = this.methodd.methodContainingRelacionNoBiDirectional;
  }

  onIsChecked1() { this.tools.getPostCreateTool = this.isChecked1; }
  onIsChecked2() { this.tools.archivosManamentTool = this.isChecked2; }
  onIsChecked3() { this.tools.serverTcp = this.isChecked3; }
  onIsChecked4() { this.tools.serverUdp = this.isChecked4; }
  onIsChecked5() { this.tools.socketClientTcp = this.isChecked5; }
  onIsChecked6() { this.tools.socketClientUdp = this.isChecked6; }
  onIsChecked7() { this.tools.converterHex = this.isChecked7; }
  onIsChecked8() { this.tools.leerExcel = this.isChecked8; }
  onIsChecked9() { this.tools.crearExcel = this.isChecked9; }

  onIsChecked10() {
    this.archivo.isToolActive = this.isChecked10;

    if (!this.isChecked10) {
      this.isChecked1 = false;
      this.isChecked2 = false;
      this.isChecked3 = false;
      this.isChecked4 = false;
      this.isChecked5 = false;
      this.isChecked6 = false;
      this.isChecked7 = false;
      this.isChecked8 = false;
      this.isChecked9 = false;
    }
  }

  onIsChecked11() {
    this.archivo.capaPojo.createCapaPojoForEntitys = this.isChecked11;
    if (this.isChecked11) {
      this.nameModeloPojo = '';
      this.archivo.capaPojo.createCapaJavaBase7 = false;
      this.isChecked12 = false;
    }
  }

  onIsChecked12() {
    this.archivo.capaPojo.createCapaJavaBase7 = this.isChecked12;
    if (this.isChecked12) {
      this.nameModeloPojo = 'Pojo';
      this.isChecked11 = false;
      this.archivo.capaPojo.createCapaPojoForEntitys = false;
    }

  }

  onEmitToolSelection() {
    this.archivo.toolClassPojo = this.tools;
    this.archivo.capaPojo.modelT = this.nameModeloPojo;
    this.dialogRef.close(this.archivo);
  }


  onClose() { this.dialogRef.close(); }



  onIsCheckedDefault() {
    this.archivo.methoddefaultValue = this.isMethoddefaultValue;
    if (this.isMethoddefaultValue) {
      this.isMethodFindByOrLoop = false;
      this.isMethodfindById = false;
      this.isMetohdSave = false;
      this.isMethodgetAll = false;
      this.isMethodDelete = false;
      this.isMethodUpdate = false;
      this.isMethodsaveOrUpdate = false;
      this.isMethodContaining = false;
      this.isMethodContainingRelacion = false;
      this.isMethodContainingRelacionNoBiDirectional = false;
    }
  }

  findByOrLoop() { this.archivo.methodManager.methodFindByOrLoop = this.isMethodFindByOrLoop; }
  findById() { this.archivo.methodManager.methodfindById = this.isMethodfindById; }
  Save() { this.archivo.methodManager.metohdSave = this.isMetohdSave; }
  getAll() { this.archivo.methodManager.methodgetAll = this.isMethodgetAll; }
  deletemethod() { this.archivo.methodManager.methodDelete = this.isMethodDelete; }
  update() { this.archivo.methodManager.methodUpdate = this.isMethodUpdate; }
  saveOrUpdate() { this.archivo.methodManager.methodsaveOrUpdate = this.isMethodsaveOrUpdate; }
  containing() { this.archivo.methodManager.methodContaining = this.isMethodContaining; }
  containingRelacion() { this.archivo.methodManager.methodContainingRelacion = this.isMethodContainingRelacion; }
  containingRelacionNoBiDirectional() { this.archivo.methodManager.methodContainingRelacionNoBiDirectional = this.isMethodContainingRelacionNoBiDirectional; }


}
