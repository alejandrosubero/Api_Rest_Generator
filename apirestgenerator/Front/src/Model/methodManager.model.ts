export class MethodManager {

     methodFindByOrLoop:boolean; // method find for tipe or neme or atributed 	
	 methodfindById:boolean;	
	 metohdSave:boolean;	
	 methodgetAll:boolean;	
	 methodDelete:boolean;	
	 methodUpdate:boolean;	
	 methodsaveOrUpdate:boolean; 	
	 methodContaining:boolean; // metodo contain de atributos de la clase	
	 methodContainingRelacion:boolean; // si hay relacion 
	 methodContainingRelacionNoBiDirectional:boolean; // si hay relacion bideireccional

     constructor() {
		this.methodFindByOrLoop = false; 
		this.methodfindById= false;
		this.metohdSave= false;
		this.methodgetAll= false;
		this.methodDelete= false;
		this.methodUpdate= false;
		this.methodsaveOrUpdate = false;
		this.methodContaining = false;
		this.methodContainingRelacion= false;
		this.methodContainingRelacionNoBiDirectional = false;
	 }


}