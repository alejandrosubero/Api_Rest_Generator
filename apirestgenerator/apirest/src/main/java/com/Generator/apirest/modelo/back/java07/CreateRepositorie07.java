package com.Generator.apirest.modelo.back.java07;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.RepositoriesServices;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.FileCreateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CreateRepositorie07 {

	@Autowired
	private FileCreateService fileCreateService;

    private Creador creador;
	private ArchivoBaseDatosPojo archivo;
    private String packageNames;
    private List<EntidadesPojo> entidades;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();
    protected static final Log logger = LogFactory.getLog(RepositoriesServices.class);

    
    public void startCreacion(ArchivoBaseDatosPojo archivo,Creador creador) {
    	 //  this.proyectoName = archivo.getProyectoName();
    	this.creador=creador;
    	this.entidades = archivo.getEntidades();
        this.packageNames = archivo.getPackageNames();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.archivo = archivo;

        try {
            this.create();
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
            	e.printStackTrace();
        }
    }


    private void create() throws InterruptedException {

        logger.info("inicia la creacion de la clase Repository");
        if (this.entidades.size() > 0) {
            for (EntidadesPojo entidad : this.entidades) {
                if(entidad.getIsEntity()) {
                    logger.info("Inicia la creacion del Repository " + " "+ entidad.getNombreClase());
                    this.createRepository(entidad);
                }
            }
        }
    }


    private  void createRepository(EntidadesPojo entidad) throws InterruptedException {
        StringBuilder sb1 = new StringBuilder("\r\n");
        List <AtributoPojo> listAtributos = entidad.getAtributos();
        String nameOfClass = entidad.getNombreClase()+"Repository";
        logger.info("createRepository" + "  for Entity:  " + entidad.getNombreClase());
        String datoTipo = "";

        for (AtributoPojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        sb1.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sb1.append("package " + packageNames + ".repository;\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.List;"+"\r\n");
        sb1.append("import java.util.Date;"+"\r\n");
        sb1.append("import org.springframework.data.repository.CrudRepository;"+"\r\n");
        sb1.append("import org.springframework.data.jpa.repository.JpaRepository;"+"\r\n");
        sb1.append("import org.springframework.data.jpa.repository.Query;"+"\r\n");
        sb1.append("import org.springframework.data.repository.query.Param;"+"\r\n");
        sb1.append("import org.springframework.stereotype.Repository;"+"\r\n");
        sb1.append("\r\n");
        sb1.append("import " + packageNames + "." + entidad.getPaquete() +"."+ entidad.getNombreClase() + ";"+"\r\n");
        sb1.append("@Repository"+"\r\n");
        sb1.append("public interface "+nameOfClass+" extends JpaRepository< " + entidad.getNombreClase() + ", "+ datoTipo + "> {\r\n ");
        sb1.append("\r\n");


        for (AtributoPojo atributos : listAtributos) {
            String cadenaOriginal = atributos.getAtributoName();
            String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
            String restoDeLaCadena = cadenaOriginal.substring(1);
            String atributoName = primeraLetra + restoDeLaCadena;

            if (atributos.getsId()){
                sb1.append("		public " + entidad.getNombreClase() + " findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");"+"\r\n");
               
                sb1.append("		public List<" + entidad.getNombreClase() + "> findBy"+atributoName+"Containing("+atributos.getTipoDato() + " " + atributos.getAtributoName()+");"+ "\r\n");
                sb1.append("\r\n");

                sb1.append("@Query(value = \"SELECT t FROM "+entidad.getNombreClase()+" t WHERE t.id =?1\")"+"\r\n");
                sb1.append(" public " + entidad.getNombreClase() + " findByIdQuery("+datoTipo+" id);"+"\r\n");
                sb1.append("\r\n");

              if(this.archivo.checkAtributos(entidad)) {
            	  sb1.append(metodoSearch(entidad)+"\r\n");
              }
                
            }
            
            if (!atributos.getsId()) {
				
            	sb1.append("		public " + entidad.getNombreClase() + " findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
				sb1.append("\r\n");			
				sb1.append("		public List<" + entidad.getNombreClase() + "> findBy" + atributoName + "Containing("+ atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
				sb1.append("\r\n");
			}
            
        }

        sb1.append("\r\n");
        sb1.append("}\r\n");
        sb1.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");

        fileCreateService.createFileClassJava(nameOfClass, "repository",sb1, this.creador.directionForJava());
       // fileCreateService.createFileClassJavaNoAddres(nameOfClass, "repository",sb1);
    }


    private String metodoSearch(EntidadesPojo entidad) {
    	 
    	boolean concat = false;
         List<String> atributosName = new ArrayList<String>();
    	// List <AtributoPojo> listAtributos = entidad.getAtributos();
        StringBuilder search = new StringBuilder("@Query(value = \"SELECT p FROM "+ entidad.getNombreClase()+" p WHERE CONCAT(");
        
        for (AtributoPojo atributos : entidad.getAtributos()) {
            if (!atributos.getsId()) { 
            	atributosName.add(atributos.getAtributoName());
            	}
        }

        for (int i = 0; i < atributosName.size(); i++ ) {   
        	if(atributosName.size() != (i+1)){        		
        		search.append(" p."+atributosName.get(i)+", ' '," );
            	concat = true;
            }else {
            	search.append(" p."+atributosName.get(i)+") LIKE %?1%\")");
            }
        }

        search.append("\r\n");
        search.append("public List<" + entidad.getNombreClase()+ "> finBySearch(String keyword);"+"\r\n");
        search.append("\r\n");
        return search.toString();
    }

}



