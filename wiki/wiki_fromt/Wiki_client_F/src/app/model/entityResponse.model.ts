import { ResponseObject } from "../interface/response_object";

export class EntityRespone implements ResponseObject{
    
    error: string;
    mensaje: string;
    entidades: Array<any>;

    constructor() {
        this.error = "";
        this.mensaje = "";
        this.entidades = new Array<any>();
    }


}