import { Injectable } from '@angular/core';
import { Entidades } from 'src/Model/entidad.model';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { ConsolaAtributoService } from './consola-atributo.service';

@Injectable({
  providedIn: 'root'
})
export class ConsolaEntidadService {

private arrayDeCadenas: string[];
private  entidad = new Entidades();

  constructor(private atributoService: ConsolaAtributoService) { }

  // -g ---entidad --entityName
  private createEntidad(arrayDeCadenas: string[]): Entidades {
    let entidad = new Entidades();
    entidad.nombreClase = arrayDeCadenas[2];
    entidad.nombreTabla = arrayDeCadenas[2];
    entidad.atributos.push(this.atributoService.createAtributoInicial());
    return entidad;
  }

    // -g ---entidad --entityName --tableName
  private createEntidadTable(arrayDeCadenas: string[]): Entidades {
    let entidad = new Entidades();
    entidad.nombreClase = arrayDeCadenas[2];
    entidad.nombreTabla = arrayDeCadenas[3];
    entidad.atributos.push(this.atributoService.createAtributoInicial());
    return entidad;
  }

  generateEntidad(arrayDeCadenas: string[]): Entidades {
    let entidad = new Entidades();
    if (arrayDeCadenas.length > 3) {
      entidad = this.createEntidadTable(arrayDeCadenas);
    } else {
      entidad = this.createEntidad(arrayDeCadenas);
    }
    return entidad;
  }

  //'---add --entidad --entityNameactual --entityName --Etiname',
  entidadAdd(arrayDeCadenas: string[]): Entidades {
    this.ajustesEntidad(arrayDeCadenas);
    return this.entidad;
  }


 private ajustesEntidad(arrayDeCadenas: string[]) {
    switch (arrayDeCadenas[4]) {
      case 'tableName': {
        this.entidad.nombreTabla = arrayDeCadenas[5];
        break;
      }
      case 'entityName': {
        this.entidad.nombreClase = arrayDeCadenas[5];
        break;
      }
      default: { break; }
    }
  }

}

