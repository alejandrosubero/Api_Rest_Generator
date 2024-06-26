package com.Generator.apirest.notas;

public enum Notas {

/*

https://springdoc.org/


  =========================================================================================================================================== 
*26/03/2021 Modelado Web
* =========================================================================================================================================== 

Notas muy basicas para modelar el objeto a construir.


	COMPONENTE: ELEMENTO QUE EMARCA EL HTML COMPLETO
     
    UN COMPONNTE VA A SER UN MAP con clave = posicion de los componentes internos y valor el componente interno. (Map de objetos)
     
    COMPONENTEINTERNO: va ser un container el cual contiene row pero a su vez pude contener un componente y es una mapa (Map de objetos)

	ROW: este contiene una lista los Objeto  col- organizados por su index pero tambien puede ser un Map clave valor. (Map de objetos) 

	ELEMET: son objetos como input, div, label, checkbox, select, tablas, listas, etc que estan listados dentro de un map y forman parte de un row. 
	
	las clases, interfases, modelos, servicios .ts se deben de generar con el proyecto.
	
	
	como conectar todo: 
	
	iniciamos con las intefaces generadoras que reciben datos.
	
	model:(Archivo)
		atributos
		tipo de dato
		posee constructor [que datos van dentro]
		*EXTENCION .ts
		
	interface: (Archivo)
		datos ?
		*EXTENCION .ts
				
	clase: (Archivo)
		datos?
		*EXTENCION .ts
	
	modulo:(Archivo) *EXTENCION .ts
		datos? [lazy load de los modulos hijos] // replazo del modulo 
		
	servicio:(Archivo) *EXTENCION .ts
		datos?
	
	
	Nota: la parte del componente debe de quedar o permitir en separado crear un componente individual eso si en ese caso que le cree 
			su modulo y modelo de datos de ser necesario. 
	
	
	Componente:
		asociado a una ruta
		asociado a un modulo 
		Direccion relativa
		nombre
		un html => map de objetos [componentes internos] (Archivo) *EXTENCION .html
		un ts => clase entera   (Archivo) => [escucha algun valor, que variables tiene, que modelos tiene] *EXTENCION .ts
		un css => clase entera (Archivo) *EXTENCION .css
		donde es usado (definido por el <app-componente>
		
    ComponentesInterno:   (no es un Archivo)
    	nombre
    	posicion
    	clases [ aparte de container, si es centrada un m-1..5 ]
    	 
    Row: (no es un Archivo)
    	posicion 
    	nombre
    	clase row [mas otra clse que se requiera]
    	style?
    	
	Col: (no es un Archivo)
		relacion con un row
		posicion dentro de un row
		siempre va a ser sx, md lg 
		clase [clases adicionales del col]
		
	Element: (no es un Archivo)
		relacion con un col o si esta relacionado con un componente o componenteInterno o con un col
		posicion
		tipo [div, input, p, select, ... {{}}, ngfor, ngIf, ....]

		
	
	
EJEMPLO:

<div class="container">
  <div class="row">
    <div class="col-6 col-md-6 col-lg-6">elmento  3</div>
    <div class="col-6 col-md-6 col-lg-6">
     <input type="text">
    </div>
  </div>
</div>


*ngIf="condicion" 
*ngFor="let x of List"


<div class="xx xxx xxxx" > </div>


formControlName="modificadorExtra"
required
matInput

[(ngModel)]="xxxxxxxx"
placeholder="xxxxxxxx"
name:"xxx"
id:"xxxx"
#xxxx
type="xxx" 
class="xxx"


	<input name="" id="" # type="" class=""/>

	<input matInput formControlName="modificadorExtra" placeholder="Modificador Extra" [(ngModel)]="modificadorExtrat" required>


<mat-form-field>
	<mat-label>{{ 'Extramodifier' | translate }}</mat-label>
	<input matInput formControlName="modificadorExtra" placeholder="Modificador Extra" [(ngModel)]="modificadorExtrat" required>
</mat-form-field>

 * 
 * 
 * 
 * **/	
	
	
	
	
/*
 
==================================================================================================================
ERRORES Y FALENCIAS ENONTRADOS:
==================================================================================================================

1) en la arquitectura capa pojo hay respuestas del controller que no son manejadas con un Response pojo.(ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);)
2) en las implementaciones hay metodos que retornan una nueva entidad no esta bien ni mal.

public class EntityRespone implements Serializable {
   private String error;
   private String mensaje;
   private List<Object> entidades;
   private List<List<Object>> listOnentidades;
}  

3) no permite crear proyecto limpios sin bases de datos o definir que solo se puede crear con este programa
4) no tiene para crear un atributo tipo lista como un byte[]; o ina List<String> etc.
5) no tien para crear una clase  mail
6) si creas un atributo no tiene para crearle el @value
7) no tiene para adicinar elementos al application-properties
8) no tiene para crear varios ambientes o properties
9) no trabaja bien el mapeo de entidades en bidireccional

	
	
==================================================================================================================
* NOTAS: PIESAS EN TRABAJO
=========================================================================================================================================== 

*Cambiar los pojos por DTO o permitir las dos occiones ... 

* 1) HAY OTRO TEMA QUE ES INCORPORAR LAS CLASES DE SEGURIDAD
* 2) INCORPORAR UNA CLASE DE EMAIL PARA ENVIOS  NOTAS: ver el proyecto que me enviaron de email para ver como cargar adjuntos.
* para la incorporacion de mail son 6 clases a generar mas el app properitis, configuracion,

* PLAN PARA PUBLICAR EL PROYECTO:
* INCORPORAR UN MANEJO DE ERRORES QUE ENVIE LOS MAIL DE LOS ERRORES PRESENTES ASI SE PUEDE MONITOREAR LOR ERRORES
* INCORPORAR LA SEGURIDAD DE LOGUEO Y TOKEN PARA LOS PROYECTOS
* INCORPORAR ROL  Y MOSTRAR PROYECTOS SEGUN PERFIL (CORREO Y CLAVE)
* NOTA: AVERIGUAR EL TEMA DE UN CORREO DE VALIDACION COMO SE HACE.
* INCORPORAR DESCARGA DEL PROYECTO EN JSON PARA LOS CLIENTES
* AÑADIR VERIFICACION DEL NOMBRE DEL PROYECTO ENTRE LOS PROYECTOS DEL CLIENTE Y DAR OCCION DE RENOMBRAR O SOBRE ESCRIBIR
* INCORPORAR LOGICA PARA EDITAR PROYECTOS Y USAR LOS PROYECTOS CREADOS.
* CREAR UN SERVICIO QUE RETORNE EL NUMERO DE PROYECTOS, USUARIOS, GLOBALMENTE.

* */
}


/**
 *
 * // Declaración de un Map (un HashMap) con clave "Integer" y Valor "String". Las claves pueden ser de cualquier tipo de objetos, aunque los más utilizados como clave son los objetos predefinidos de Java como String, Integer, Double ... !!!!CUIDADO los Map no permiten datos atómicos
 * Map<Integer, String> nombreMap = new HashMap<Integer, String>();
 * nombreMap.size(); // Devuelve el numero de elementos del Map
 * nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
 * nombreMap.put(K clave, V valor); // Añade un elemento al Map
 * nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
 * nombreMap.clear(); // Borra todos los componentes del Map
 * nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
 * nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
 * nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
 * nombreMap.values(); // Devuelve una "Collection" con los valores del Map
 *
 * **/

/**
 * 
	@Component:Sustituye la declaración del bean en el xml.
	@Autowired:Sustituye la declaración de los atributos del bean en el xml.
	@Qualifier(«nombreBean»):Sirve para indicar que clase es la que se debe inyectar.
	@Required:Indica si el atributo es obligatorio.
	@Service, @Repository y @Controller:Son estereotipos de @Component y se usan para indicar que la clase sera un servicio 
	(@Service), una clase de acceso a datos (@repository) o un controlador (@Controller).
	@PostConstruct:Ejecuta el metodo con esta anotación despues de crear el objeto.
	@PreDestroy:Ejecuta el metodo con esta anotación antes de destruir el objeto.
	@Scope:Sirve para indicar el ambito en el que se encontrara el bean.
 
	singleton: Se crea una unica instancia del bean para toda la aplicación.
	prototype: Se crea una nueva instacia del bean cada vez.
	request: Se crea una nueva instacia del bean para cada petición HTTP request.
	session: Se crea una nueva instacia del bean por sesión HTTP.
	globalSession: Se crea una nueva instacia del para cada sesión HTTP global.
 * 
 */

/**
*
* // Declaración de un Map (un HashMap) con clave "Integer" y Valor "String". Las claves pueden ser de cualquier tipo de objetos, aunque los más utilizados como clave son los objetos predefinidos de Java como String, Integer, Double ... !!!!CUIDADO los Map no permiten datos atómicos
* Map<Integer, String> nombreMap = new HashMap<Integer, String>();
* nombreMap.size(); // Devuelve el numero de elementos del Map
* nombreMap.isEmpty(); // Devuelve true si no hay elementos en el Map y false si si los hay
* nombreMap.put(K clave, V valor); // Añade un elemento al Map
* nombreMap.get(K clave); // Devuelve el valor de la clave que se le pasa como parámetro o 'null' si la clave no existe
* nombreMap.clear(); // Borra todos los componentes del Map
* nombreMap.remove(K clave); // Borra el par clave/valor de la clave que se le pasa como parámetro
* nombreMap.containsKey(K clave); // Devuelve true si en el map hay una clave que coincide con K
* nombreMap.containsValue(V valor); // Devuelve true si en el map hay un Valor que coincide con V
* nombreMap.values(); // Devuelve una "Collection" con los valores del Map
*
* **/

