export class Atributo {

    nombreClase: string; //no esta en la base de datos

    sId: boolean; // el dato es tipo id o no lo es true / false
    idName: string; // = "id";
    modificadorExtra: string; // static, final
    ismodificadorExtra: boolean;
    tipoModificador; // private, public, protected 
    tipoDato; // Integer, Long, Double, String, Chat, Byte
    atributoName: string;
    nameColum: string;
    atributoUpdatable: boolean;
    atributoNullable: boolean;
    length: number;
    generatedValue: boolean;
    tipoGeneratedValor: string; // strategy = GenerationType.AUTO, GenerationType.SEQUENCE
    sequenceGenerator: boolean; // este es mas profundo de usar
    tipoGenerador: boolean; // tipo de generador de sequencia
    sequenseName: string;
    nameSequenceTable: string;
    initialValue: number;
    allocationSize: number;
    transiente: boolean; // @Transient
    // addSizeCheck: boolean; // @Size(min = 3, max = 255)
    // minSizeCheck: number;
    // maxSizeCheck: number;


    constructor() {

        this.idName = 'id';
    }

}

