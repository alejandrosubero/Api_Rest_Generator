package com.Generator.apirest.modelo.back.java07;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.RelacionPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

import com.Generator.apirest.services.builders.FileCreateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Scope("singleton")
@Component
public class CreateService07 {

	@Autowired
	private FileCreateService fileCreateService;
	
	private Creador creador;
	private ArchivoBaseDatosPojo archivo;
    private String packageNames;
    private List<EntidadesPojo> entidades;

    private AnotacionesJava anotacionesJava = new AnotacionesJava();
    protected static final Log logger = LogFactory.getLog(CreateService07.class);
    
//    // private String description;
//    private String proyectoName;
  
//    private String barra = "";
//    private int relantizar2 = SleepRelantizer.RELANTIZERB;
    

    public void startCreacion(ArchivoBaseDatosPojo archivo, Creador creador) {

        //this.proyectoName = archivo.getProyectoName();
    	this.creador = creador;
        this.entidades = archivo.getEntidades();
        this.packageNames = archivo.getPackageNames();
        this.archivo = archivo;
        this.anotacionesJava.activateAnotacionesJava(archivo);
        try {
            this.create();
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
            	e.printStackTrace();
        }
    }


    private void create() throws InterruptedException {

        logger.info("inicia la creacion de los servicios07");
        if (this.entidades.size() > 0) {
            for (EntidadesPojo entidad : this.entidades) {
                if(entidad.getIsEntity()) {
                    logger.info("Inicia la creacion de Servicio 07" + entidad.getNombreClase());
                    this.createService(entidad);
                }
            }
        }
    }



    private  void createService(EntidadesPojo entidad ) throws InterruptedException {

        StringBuilder sb2 = new StringBuilder("\r\n");
        String cadenaOriginal="";
        String atributoName = "";
        String datoTipo = "";

        List <AtributoPojo> listAtributos = entidad.getAtributos();
        String nameOfClass =entidad.getNombreClase()+"Service";
        logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

        for (AtributoPojo atributoID : entidad.getAtributos()) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        sb2.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sb2.append("package " + packageNames + ".service ;\r\n"); 
        sb2.append("\r\n");
        sb2.append( "import java.util.Date;"+"\r\n");
        sb2.append("\r\n");
        sb2.append("import java.util.ArrayList;");
        sb2.append("\r\n");
        sb2.append("import java.util.List;");
        sb2.append("\r\n");
        sb2.append("import " + packageNames + "." + entidad.getPaquete()+"."+ entidad.getNombreClase() + ";");

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion()+";" +"\r\n");
        }
        sb2.append("\r\n");
        sb2.append("\r\n");
        sb2.append("\r\n");
        sb2.append("public interface "+nameOfClass+"{\r\n ");
        sb2.append("\r\n");

        if(this.archivo.getMethodManager().isMethodfindById()) {
        	sb2.append("		public " + entidad.getNombreClase() + " findById"+ "("+ datoTipo+ " id);"+"\r\n");
        }
	   	
        if(this.archivo.getMethodManager().isMetohdSave()) {
        	sb2.append("		public boolean saveOrUpdate"+entidad.getNombreClase()+"("+entidad.getNombreClase()+" "+entidad.getNombreClase().toLowerCase()+");"+"\r\n");
        }
	    
        if(this.archivo.getMethodManager().isMethodgetAll()) {
        	sb2.append("		public List<"+entidad.getNombreClase()+"> getAll"+entidad.getNombreClase()+"();"+"\r\n");
        }
	    
        if(this.archivo.checkAtributos(entidad)) {
		sb2.append("		public List<" + entidad.getNombreClase() + ">  search(String search);"+"\r\n");
        }
     
        for (AtributoPojo atributos : listAtributos) {
        	
            if (!atributos.getsId()) {
                cadenaOriginal = atributos.getAtributoName();
                String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
                String restoDeLaCadena = cadenaOriginal.substring(1);
                atributoName = primeraLetra + restoDeLaCadena;
                if(this.archivo.getMethodManager().isMethodFindByOrLoop()) {
                sb2.append("		public " + entidad.getNombreClase() + "  findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");"+"\r\n");
                sb2.append("\r\n");
                }
            }
        }

        
        for (AtributoPojo atributos : listAtributos) {
        
            if (!atributos.getsId()) {
                cadenaOriginal = atributos.getAtributoName();
                String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
                String restoDeLaCadena = cadenaOriginal.substring(1);
                atributoName = primeraLetra + restoDeLaCadena;
                if(this.archivo.getMethodManager().isMethodContaining()) {
	                sb2.append("		public List<" + entidad.getNombreClase() + ">  findBy" + atributoName + "Containing(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");"+"\r\n");
	                sb2.append("\r\n");
                }
            }
        }
       
        
        if (entidad.getDelete()) {
            sb2.append("		public boolean delete"+entidad.getNombreClase()+"("+datoTipo+" id);"+ "\r\n");
        }
      
        sb2.append("\r\n");


        for (RelacionPojo relacion : entidad.getRelaciones()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
            	 if(this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional()) {
                sb2.append("		public List<"+entidad.getNombreClase()+">  findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ");"+"\r\n");
            	 }
            }else{
            	  if(this.archivo.getMethodManager().isMethodContainingRelacion()) {
            		  sb2.append("		public List<"+entidad.getNombreClase()+">  findByRelacion"+relacion.getNameClassRelacion() +"("+ relacion.getNameClassRelacion() + " " + relacion.getNameClassRelacion().toLowerCase() + ");"+"\r\n");
            	  }
            }
        }
        
        sb2.append("}"+"\r\n");
        sb2.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        fileCreateService.createFileClassJava(nameOfClass, "service",sb2, this.creador.directionForJava());   
       // fileCreateService.createFileClassJavaNoAddres(nameOfClass, "service",sb2); 
    }
   
}

