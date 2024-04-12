import { Injectable } from '@angular/core';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { Entidades } from 'src/Model/entidad.model';
import { Atributo } from 'src/Model/atributo.model';

@Injectable({
  providedIn: 'root'
})
export class ConsolaAtributoService {

  arrayDeCadenas: string[];
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();


  constructor() { }


  createAtributoInicial(): Atributo {
    let id = new Atributo();
    //   id.nombreClase = this.entidad.nombreClase; //este no esta en atributo
    id.sId = true;
    id.idName = 'id';
    id.modificadorExtra = 'static';
    id.ismodificadorExtra = false;
    id.tipoModificador = 'private';
    id.tipoDato = 'Long';
    id.atributoName = id.idName;
    id.nameColum = 'Name';
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
    return id;
  }

  

}
