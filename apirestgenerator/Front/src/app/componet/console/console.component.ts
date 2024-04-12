import { Component, OnInit, ElementRef, ViewChild, Directive, AfterViewInit } from '@angular/core';
import { Entidades } from 'src/Model/entidad.model';
import { Relacion } from 'src/Model/relacion.model';
import { ArchivoBaseDatosPojo } from 'src/Model/archivo.model';
import { Atributo } from 'src/Model/atributo.model';
import { ConsolaService } from 'src/app/services/consola.service';
import { Help } from 'src/Model/hepl.model';
import { ConsolaEntidadService } from 'src/app/services/consola-entidad.service';

@Component({
  selector: 'app-console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.css']
})
// @Directive({ selector: 'input[appFocus]', })
export class ConsoleComponent implements OnInit {

  showAtributo = false;
  showRelaciones = false;
  showFiller = false;
  entidad: Entidades;
  jsonValor: Object;
  relacion: Relacion;
  // entity: Entidades = new Entidades();

  grupoEntidadesPojosList: Array<Entidades> = new Array<Entidades>();
  atributosList: Array<Atributo> = new Array<Atributo>();
  relacionList: Array<Relacion> = new Array<Relacion>();

  databaseTipoVal: number;  // mejorar el uso de este atributo
  value = '';
  consoleHistory: string[] = [];
  entidades: Array<any> = new Array<any>();
  Atributo: Array<any> = new Array<any>();
  archivo: ArchivoBaseDatosPojo;
  helpAll: Help = new Help();

  @ViewChild('box', { static: false }) auto: ElementRef;
  //@ViewChild('drawer', { static: false }) codess: ElementRef;

  constructor(private consola: ConsolaService, private entidadService: ConsolaEntidadService) { }

  ngOnInit(): void {
    setTimeout(() => { // this will make the execution after the above boolean has changed
      this.auto.nativeElement.focus();
    }, 0);
  }

  onEnter(value: string) {
    this.auto.nativeElement.value = null;
    let valuer = value.replace(/ /g, '');
    this.consoleHistory.push(value);
    this.operacion(valuer);
  }


  operacion(consol: string) {

    var arrayDeCadenas = consol.split('--');

    //====================================================================================================================//
    if (arrayDeCadenas[0] === '-g') {
      if (arrayDeCadenas[1] == '-entidad' || arrayDeCadenas[1] == '-ENTIDAD' || arrayDeCadenas[1] == '-Entidad') {
        let entidad = new Entidades();
        entidad = this.entidadService.generateEntidad(arrayDeCadenas);
        this.archivo.entidades.push(entidad);
        console.log('entidad', this.archivo);
      }
      if (arrayDeCadenas[1] == '-pojo' || arrayDeCadenas[1] == '-POJO' || arrayDeCadenas[1] == '-Pojo') {
        console.log('pojo', arrayDeCadenas);
      }
      if (arrayDeCadenas[1] == '-proyect' || arrayDeCadenas[1] == '-PROYECT' || arrayDeCadenas[1] == '-Proyect') {
        this.archivo = new ArchivoBaseDatosPojo();
        if (arrayDeCadenas.length > 10) {
          this.archivo = this.consola.generateArchivo(arrayDeCadenas);
          console.log('consola ob', this.archivo);
        }
        if (arrayDeCadenas.length <= 5) {
          this.archivo = this.consola.generateArchivoBase(arrayDeCadenas);
          console.log('consola ob', this.archivo);
        }
      }
      if (arrayDeCadenas[1] == '-g' || arrayDeCadenas[1] == '-G' || arrayDeCadenas[1] == '-generate'
        || arrayDeCadenas[1] == '-Generate' || arrayDeCadenas[1] == '-GENERATE' || arrayDeCadenas[1] == '-generar'
        || arrayDeCadenas[1] == '-Generar' || arrayDeCadenas[1] == '-GENERAR') {
        this.consola.onEnviar();
      }
      if (arrayDeCadenas[1] == '-d' || arrayDeCadenas[1] == '-D' || arrayDeCadenas[1] == '-download'
        || arrayDeCadenas[1] == '-Download' || arrayDeCadenas[1] == '-DOWNLOAD' || arrayDeCadenas[1] == '-descarga'
        || arrayDeCadenas[1] == '-Descarga' || arrayDeCadenas[1] == '-DESCARGA') {
        this.consola.ondownload();
      }

    } // fin
    //====================================================================================================================//
    if (arrayDeCadenas[1] === '-add' || arrayDeCadenas[1] == '-ADD' || arrayDeCadenas[1] == '-Add') {

      if (arrayDeCadenas[2] == 'proyect' || arrayDeCadenas[2] == 'PROYECT' || arrayDeCadenas[2] == 'Proyect') {
        let archi = this.consola.getArchivoAdd(this.archivo, arrayDeCadenas);
        this.archivo = archi;
        console.log('consola ADD', this.archivo);
      }
      if (arrayDeCadenas[2] == 'entidad' || arrayDeCadenas[2] == 'ENTIDAD' || arrayDeCadenas[2] == 'Entidad') {
        let entidad = new Entidades();
        this.archivo.entidades.forEach(x => {

          console.log(arrayDeCadenas[3], '<---->', x.nombreClase);

          if (x.nombreClase === arrayDeCadenas[3]) {
            entidad = this.entidadService.entidadAdd(arrayDeCadenas);
            console.log('entro la entidad respuesta', entidad);
            x.nombreClase = entidad.nombreClase;
            x.nombreTabla = entidad.nombreTabla;
          }
          console.log('entidad', this.archivo);
        });
      }
    }

    if (arrayDeCadenas[1] === '-helpAll' || arrayDeCadenas[1] == 'help' || arrayDeCadenas[1] == '-help'
      || arrayDeCadenas[1] == '-helpall' || arrayDeCadenas[1] == '-Helpall'
      || arrayDeCadenas[1] == '-HelpAll' || arrayDeCadenas[1] == '-HELPALL'
      || arrayDeCadenas[1] == '-ha') {
      this.helpAll.helpAll.forEach(x => {
        this.consoleHistory.push(x);
      });
    }

    if (arrayDeCadenas[1] === '-helpP' || arrayDeCadenas[1] == '-helpp' || arrayDeCadenas[1] == '-HelpP'
      || arrayDeCadenas[1] == '-hp' || arrayDeCadenas[1] == '-HELPP' || arrayDeCadenas[1] == 'hp') {
      this.helpAll.helpP.forEach(x => {
        this.consoleHistory.push(x);
      });
    }

    if (arrayDeCadenas[1] == '-clear' || arrayDeCadenas[1] == '-CLEAR' || arrayDeCadenas[1] == '-cl'
      || arrayDeCadenas[1] == '-Clear' || arrayDeCadenas[1] == 'clear') {
      this.consoleHistory = [];
    }

  }
}



  // -g ---help
  // -g ---proyect --alejandro
  // -g ---entidad --alejandro
  // -g ---pojo --alejandro

  // -g  -G  -generate -Generate -GENERATE -generar -Generar -GENERAR  
  // -d  -D -download -Download -DOWNLOAD -descarga  -Descarga -DESCARGA
// -g ---g 
// -g ---d

  // -add --atributo --casa --string
  // -add --atributo --casa, masorca, mascota --string, int, long
  // -add --relation --conquien  --nombre   --tipo 
  // -add --relation --conquien, conquien, conquien --nombre1, nombre2, nombre3 --tipo1, tipo2, tipo3
  // -add --proyect  --se adiciona la configuraciones faltantes

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


//**datos del script para generar un proyecto */
// -g ---proyect --proyectoName  --autor  --user  --context  --packageNames  --description  --wihtSegurity --dataBase  --databaseTest  --nativeMysql 
//  --javaVersion  --databaseName  --tipoDatabase --createCapaPojoForEntitys  --getPostCreateTool   --archivosManamentTool


//***ejemplo del script para el procyecto hay que mejorar los cambios */
//-g  ---proyect --proyecto --aleja --ale --protex --com.protex.valor --valor del proyecto --false --true --true --false --1.8 --databaseName  --1 --true  --true --true


// -g  ---proyect --proyecto --aleja --ale 
// --protex 
// --com.protex.valor 
// --valor del proyecto 
// --false --true --true --false --1.8 
// --databaseName  
// --1 
// --true  
// --true 
// --true




//****************************************************************************************************************************************************//

  // this.value = this.value.replace(/ /g, '').charAt(0).toUpperCase()+value.replace(/ /g, '').slice(1);
  // var nombres = ['Rita', 'Pedro', 'Miguel', 'Ana', 'Vanesa'];
  // var masculinos = nombres.slice(1, 3);

  // masculinos contiene ['Pedro','Miguel']
  // addHero(newHero: string) {
  //   if (newHero) {
  //     this.heroes.push(newHero);
  //   }
  // }

