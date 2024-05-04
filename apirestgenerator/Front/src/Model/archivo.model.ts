import { CapaPojo } from './capapojo.model';
import { Entidades } from './entidad.model';
import { MethodManager } from './methodManager.model';
import { ToolClassPojo } from './tool-class-pojo .model';

export class ArchivoBaseDatosPojo {

	capaPojo: CapaPojo;
	nombreClase: string; // no esta en java este atributo de atributos
	autor: string;
	user: string;
	context: string;
	proyectoName: string;
	packageNames: string;
	description: string;
	wihtSegurity: boolean; // spring segurity o no
	dataBase: boolean; // true o false
	databaseTest: boolean; // usar databade test y Database
	databaseName: string; // nombre de base de datos
	tipoDatabase: number; // oracle = 2, Mysql = 1, h2 = 3.
	nativeMysql: boolean; // usar generador nativo de mysql
	javaVersion: number;
	entidades: Array<Entidades>;
	toolClassPojo: ToolClassPojo;
	prograntVersion: string;
	artifact: string;
	isToolActive: boolean;
	methoddefaultValue: boolean;
	methodManager: MethodManager;
	springBootVersion: string;

	constructor() {
		this.capaPojo = new CapaPojo();
		this.prograntVersion = '1.0.0.0'
		this.wihtSegurity = false; // spring segurity o no
		this.dataBase = true;// true o false
		this.databaseTest = true; // usar databade test y Database
		this.tipoDatabase = 1; // oracle = 2, Mysql = 1, h2 = 3.
		this.nativeMysql = false; // usar generador nativo de mysql
		this.javaVersion = 1.8;
		this.toolClassPojo = new ToolClassPojo();
		this.entidades = new Array<Entidades>();
		this.isToolActive = false;
		this.methoddefaultValue = true;
		this.methodManager = new MethodManager();
	}
}

