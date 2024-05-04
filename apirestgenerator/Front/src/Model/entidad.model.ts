import { Atributo } from './atributo.model';
import { Relacion } from './relacion.model';

export class Entidades {

    isEntity: boolean;
    nombreClase: string;
    nombreTabla: string;
    paquete: string;
    delete:boolean;
    atributos: Array<Atributo>;
    relaciones: Array<Relacion>;
    isSerializable:boolean;
  

    constructor() {
        this.isEntity = true;
        this.delete = false;
        this.nombreClase = '';
        this.nombreTabla = '';
        this.paquete = 'entitys';
        this.atributos = new Array<Atributo>();
        this.relaciones = new Array<Relacion>();
        this.isSerializable = false;
    }
}

