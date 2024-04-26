export class EntityRespone {
    
    error: string;
    mensaje: string;
    entidades: Array<any>;

    constructor() {
        this.error = "";
        this.mensaje = "";
        this.entidades = new Array<any>();
    }

    
}