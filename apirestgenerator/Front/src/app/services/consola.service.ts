import { Injectable } from '@angular/core';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { Entidades } from 'src/Model/entidad.model';
import { EntidadService } from './entidad.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { saveAs } from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class ConsolaService {

  arrayDeCadenas: string[];
  archivo: ArchivoBaseDatosPojo = new ArchivoBaseDatosPojo();

  constructor(private servicesEntidad: EntidadService,
    private spinner: NgxSpinnerService,) { }


  getArchivoAdd(archivos: ArchivoBaseDatosPojo, arrayDeCadenas: string[]): ArchivoBaseDatosPojo {
    this.archivo = archivos;
    this.ajustesProyect(arrayDeCadenas);
    return this.archivo;
  }

  generateArchivoBase(arrayDeCadenas: string[]): ArchivoBaseDatosPojo {
    let archivo = new ArchivoBaseDatosPojo();
    archivo.proyectoName = arrayDeCadenas[2];
    archivo.autor = arrayDeCadenas[3];
    archivo.user = arrayDeCadenas[4];
    archivo.context = arrayDeCadenas[2];
    archivo.packageNames = 'com.' + arrayDeCadenas[2] + '.' + arrayDeCadenas[3];
    archivo.databaseName = arrayDeCadenas[2];
    return archivo;
  }

  generateArchivo(arrayDeCadenas: string[]): ArchivoBaseDatosPojo {
    let archivo = new ArchivoBaseDatosPojo();
    archivo.proyectoName = arrayDeCadenas[2];
    archivo.autor = arrayDeCadenas[3];
    archivo.user = arrayDeCadenas[4];
    archivo.context = arrayDeCadenas[5];
    archivo.packageNames = arrayDeCadenas[6];
    archivo.description = arrayDeCadenas[7];
    archivo.wihtSegurity = this.stringToBoolean(arrayDeCadenas[8]);
    archivo.dataBase = this.stringToBoolean(arrayDeCadenas[9]);
    archivo.databaseTest = this.stringToBoolean(arrayDeCadenas[10]);
    archivo.nativeMysql = this.stringToBoolean(arrayDeCadenas[11]);
    archivo.javaVersion = parseInt(arrayDeCadenas[12]);
    archivo.databaseName = arrayDeCadenas[13];
    archivo.tipoDatabase = parseInt(arrayDeCadenas[14]);
    archivo.capaPojo.createCapaPojoForEntitys = this.stringToBoolean(arrayDeCadenas[15]);
    archivo.toolClassPojo.getPostCreateTool = this.stringToBoolean(arrayDeCadenas[16]);
    archivo.toolClassPojo.archivosManamentTool = this.stringToBoolean(arrayDeCadenas[17]);
    // archivo.entidades = new Array<Entidades>();
    return archivo;
  }

  stringToBoolean(string) {
    switch (string.toLowerCase().trim()) {
      case "true": case "yes": case "1": return true;
      case "false": case "no": case "0": case null: return false;
      default: return Boolean(string);
    }
  }

  ajustesProyect(arrayDeCadenas: string[]) {
    switch (arrayDeCadenas[2]) {
      case 'proyectoName': {
        this.archivo.proyectoName = arrayDeCadenas[3];
        break;
      }
      case 'autor': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      }
      case 'user': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      }
      case 'context': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      }
      case 'packageNames': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      }
      case 'description': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'wihtSegurity': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'dataBase': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'databaseTest': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'nativeMysql': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'javaVersion': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'databaseName': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'tipoDatabase': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'createCapaPojoForEntitys': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'getPostCreateTool': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      } case 'archivosManamentTool': {
        this.archivo.autor = arrayDeCadenas[3];
        break;
      }
      default: {
        break;
      }
    }
  }


// ======================================================= //
onEnviar() {
  this.spinner.show();
  this.servicesEntidad.postP(this.archivo).subscribe((x: any) => {
   // console.log('RESPUESTA DEL SERVIDO =>> ', x);
    if (x) { 
      this.spinner.hide();
     } else {
      console.log(x);
    }
  });
}

// ======================================================== //
ondownload() {
  let name = this.archivo.proyectoName;
  this.servicesEntidad.download(name).subscribe(blob => {
    saveAs(blob, name + '.zip');
  });
}
// ======================================================== //


}


 //**datos del script para generar un proyecto */
  // -g ---proyect --proyectoName  --autor  --user  --context  --packageNames  --description  --wihtSegurity --dataBase  --databaseTest  --nativeMysql 
  //  --javaVersion  --databaseName  --tipoDatabase --createCapaPojoForEntitys  --getPostCreateTool   --archivosManamentTool

  //***ejemplo del script para el procyecto hay que mejorar los cambios */
  // -g  ---proyect --proyecto --aleja --ale --protex --com.protex.valor --valor del proyecto --false --true --true --false --11 --databaseName  --1 --true  --true --true

  // -g  ---proyect --proyecto --aleja --ale
  // -g ---proyect --proyectoName  --autor  --user

  // -add --proyect --autor --ale
  // -add --proyect --proyectoName --codePrueba
  // -add --proyect --autor --ale
  // -add --proyect --user --ale
  // -add --proyect --context --alejandro
  // -add --proyect --packageNames --com.alejandro.codePrueba
  // -add --proyect --description --prueba code
  // -add --proyect --wihtSegurity --false
  // -add --proyect --dataBase --true
  // -add --proyect --databaseTest --true
  // -add --proyect --nativeMysql --false
  // -add --proyect --javaVersion --1.8
  // -add --proyect --databaseName --code
  // -add --proyect --tipoDatabase --1
  // -add --proyect --createCapaPojoForEntitys --true
  // -add --proyect --getPostCreateTool --true
  // -add --proyect --archivosManamentTool --true
