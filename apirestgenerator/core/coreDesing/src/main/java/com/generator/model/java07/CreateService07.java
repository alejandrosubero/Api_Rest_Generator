package com.generator.model.java07;


import com.generator.core.interfaces.FileCreateService;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.back.AttributePojo;
import com.generator.core.pojos.back.Creador;
import com.generator.core.pojos.back.EntityPojo;
import com.generator.core.pojos.back.RelationshipPojo;
import com.generator.core.pojos.notas.AnotacionesJava;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.List;


public class CreateService07 {


	private FileCreateService fileCreateService;
	
	private Creador creador;
	private ArchivoBaseDatosPojo archivo;
    private String packageNames;
    private List<EntityPojo> entidades;

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
            for (EntityPojo entidad : this.entidades) {
                if(entidad.getIsEntity()) {
                    logger.info("Inicia la creacion de Servicio 07" + entidad.getNombreClase());
                    this.createService(entidad);
                }
            }
        }
    }



    private  void createService(EntityPojo entidad ) throws InterruptedException {

        StringBuffer sb2 = new StringBuffer("\r\n");
        String cadenaOriginal="";
        String atributoName = "";
        String datoTipo = "";

        List <AttributePojo> listAtributos = entidad.getAtributos();
        String nameOfClass =entidad.getNombreClase()+"Service";
        logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

        for (AttributePojo atributoID : entidad.getAtributos()) {
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

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
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
     
        for (AttributePojo atributos : listAtributos) {
        	
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

        
        for (AttributePojo atributos : listAtributos) {
        
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


        for (RelationshipPojo relacion : entidad.getRelaciones()) {
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
//        fileCreateService.createFileClassJava(nameOfClass, "service",sb2, this.creador.directionForJava());

    }
   
}

