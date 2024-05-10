package com.Generator.apirest.modelo.back.base;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.LayerPojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CreateMapper implements IImportModel {
 
    
    private List<EntityPojo> toEntidad;
    private List<EntityPojo> toPojos;
    protected static final Log logger = LogFactory.getLog(CreateMapper.class);


    private String returnObjectClassPackage;


    public void initiarCreateMapper(ArchivoBaseDatosPojo archivo, Creador creadors) {
        toPojos = new ArrayList<EntityPojo>();
        toEntidad = new ArrayList<EntityPojo>();
        returnObjectClassPackage = archivo.getCapaPojo().getModelT();
        this.separateEntidadToPojos(archivo.getEntidades());
        this.createMapper(archivo, creadors );
    }


    private void separateEntidadToPojos(List<EntityPojo> entidadesList) {
    	for (EntityPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(returnObjectClassPackage) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }

    
    private void createMapper(ArchivoBaseDatosPojo archivo, Creador creador) {
        for (EntityPojo entidad : archivo.getEntidades()) {
            if (entidad.getIsEntity()) {
                    if (entidad.getIsEntity()) {
                        String escritos = metods(archivo, creador, entidad).toString();
                        createArchivoController(escritos, entidad.getNombreClase() + "Mapper", creador, archivo.getProyectoName());
                }
            }
        }
    }
    
    
    private void createArchivoController(String escrito, String nameOfClass, Creador creador, String proyectoName) {
       
    	try {	

            String direction =   path(Lists.newArrayList(creador.getDireccionDeCarpeta() + proyectoName 
            		,"src" , "main","java",creador.getCom(),creador.getPackageNames1(),creador.getArtifact() ,"mapper"));
            
            creador.crearArchivo(direction, escrito, nameOfClass + ".java");
            
        } catch (Exception e) {
            logger.error(e);
        }
    }

    
    private StringBuilder metods(ArchivoBaseDatosPojo archivo, Creador creador, EntityPojo entidad) {

        StringBuilder sb = new StringBuilder(BREAK_LINE);
        logger.info("Create Mapper metodos  for Entity:  " + entidad.getNombreClase());
        try {
            sb.append(BREAK_LINE);
            sb.append(this.createImport(archivo, creador, entidad));
            sb.append(this.createTituloClass(entidad));
            sb.append(createEntityToPojoMapper(entidad, archivo.getCapaPojo()));
            sb.append(createPojoToEntityMapper(entidad, archivo.getCapaPojo()));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}" + BREAK_LINE);
        sb.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb;
    }

    private StringBuilder createImport(ArchivoBaseDatosPojo archivo, Creador creadors, EntityPojo entidads) {

        StringBuilder sb1 = new StringBuilder();        
        sb1.append(new AnotacionesJava(archivo).creatNotaClase().toString()+BREAK_LINE);
        sb1.append("package " + creadors.getPackageNames() + ".mapper;" + BREAK_LINE);
        sb1.append("import " + creadors.getPackageNames() + ".entitys." + entidads.getNombreClase() + ";" + BREAK_LINE);
        sb1.append("import " + creadors.getPackageNames() + "."+archivo.getCapaPojo().getModelT()+"." + entidads.getNombreClase() + archivo.getCapaPojo().getModelM()+";" + BREAK_LINE);

        for (RelationshipPojo relacion : entidads.getRelaciones()) {
            sb1.append("import " + creadors.getPackageNames() + "." + entidads.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + BREAK_LINE);
            sb1.append("import " + creadors.getPackageNames() + "."+archivo.getCapaPojo().getModelT()+"."+ relacion.getNameClassRelacion() +archivo.getCapaPojo().getModelM()+ ";" + BREAK_LINE);
        }
        
        sb1.append("import org.springframework.web.bind.annotation.*;" + BREAK_LINE);
        sb1.append("import org.springframework.stereotype.Component;" + BREAK_LINE);
        sb1.append("import org.springframework.beans.factory.annotation.Autowired;" + BREAK_LINE);
        sb1.append(BREAK_LINE);
        sb1.append("import java.util.List;" + BREAK_LINE);
        sb1.append( "import java.util.Date;"+BREAK_LINE);
        sb1.append("import java.util.ArrayList;" + BREAK_LINE);
        sb1.append("import org.modelmapper.ModelMapper;"+BREAK_LINE);
        sb1.append(BREAK_LINE);
        return sb1;
    }

    private StringBuilder createTituloClass(EntityPojo entidad) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    @Component"+BREAK_LINE);
        sb2.append("    public class " + entidad.getNombreClase() + "Mapper {"+BREAK_LINE);
        sb2.append(BREAK_LINE);

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            String nombreMapper = (relacion.getNameClassRelacion() + "Mapper");
            sb2.append("      @Autowired" + BREAK_LINE);
            sb2.append("      private " + nombreMapper+TAB+ nombreMapper.toLowerCase() + ";" + BREAK_LINE);
            sb2.append(BREAK_LINE);
        }
        return sb2;
    }


    private StringBuilder createEntityToPojoMapper(EntityPojo entity, LayerPojo layerPojo) {
		
    	 StringBuilder sb3 = new StringBuilder();
         String entidadReturn = stringEnsamble(List.of(entity.getNombreClase(), layerPojo.getModelM()));

    	 sb3.append("    public " + entidadReturn + " entityToPojo(" + entity.getNombreClase() + " entity) {");
    	 sb3.append(BREAK_LINE);
    	 sb3.append(" 		ModelMapper modelMapper = new ModelMapper();");
    	 sb3.append(BREAK_LINE);
    	 sb3.append("        "+ entidadReturn + " pojo = null;" + "\r\n");
    	 sb3.append(BREAK_LINE);
    	 sb3.append("		if ( entity != null) {");
    	 sb3.append(BREAK_LINE);
    	 sb3.append(stringEnsamble(List.of(DOUBLETAB, TAB,"pojo = modelMapper.map(entity, ",entidadReturn ,".class);")));
    	 sb3.append(BREAK_LINE);
    	 sb3.append("       }");
    	 sb3.append(BREAK_LINE);
    	 sb3.append("	return  pojo;");
    	 sb3.append(BREAK_LINE);
    	 sb3.append("}");
    	 sb3.append(BREAK_LINE);
    	return sb3;
    }
    
    
    private StringBuilder  createPojoToEntityMapper(EntityPojo entity, LayerPojo layerPojo) {
		
   	 StringBuilder sb7 = new StringBuilder();
   	 sb7.append("    public " + entity.getNombreClase() + " pojoToEntity(" + entity.getNombreClase() + layerPojo.getModelM()+" pojo) {");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("		ModelMapper modelMapper = new ModelMapper();");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("        "+ entity.getNombreClase() + " entity = null;" + BREAK_LINE);
   	 sb7.append(BREAK_LINE);
   	 sb7.append("		if ( pojo != null) {");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("   		entity = modelMapper.map(pojo, "+ entity.getNombreClase() +".class);");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("		}");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("	return  entity;");
   	 sb7.append(BREAK_LINE);
   	 sb7.append("}");
   	 sb7.append(BREAK_LINE);
   	 
   	return sb7;
   }
    
    
    
    private StringBuilder createEntityToPojo(EntityPojo entity, LayerPojo layerPojo) throws InterruptedException {
        
   	 StringBuilder sb3 = new StringBuilder();

           for (EntityPojo pojo : toPojos) {
               String[] clavePojo = pojo.getNombreClase().split("Pojo");

               if (entity.getNombreClase().equals(clavePojo[0])) {
                   sb3.append("        public " + entity.getNombreClase() + " PojoToEntity(" + pojo.getNombreClase() + " pojo) {" + BREAK_LINE);
                   sb3.append( "           "+entity.getNombreClase() + " entity = new "+entity.getNombreClase()+"();" + "\r\n");
                   
                   if (entity.getAtributos().size() > 0) {
                       for (AttributePojo atributo : entity.getAtributos()) {
                           String atributoNameSetGet = atributo.getAtributoName().substring(0, 1).toUpperCase()
                                   + atributo.getAtributoName().substring(1).toLowerCase();
                         //  String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
                           sb3.append("          entity.set" + atributoNameSetGet + "(pojo.get" + atributoNameSetGet + "());" + BREAK_LINE);
                       }
                   }
                   
                   if (entity.getRelaciones().size() > 0) {
                       for (RelationshipPojo relacion : entity.getRelaciones()) {
                           String relacionClase = relacion.getNameClassRelacion();
                           String relacionName = relacion.getNameRelacion();
                           sb3.append("        List<" + relacionClase + "> list" + relacionName + " = new ArrayList<" + relacionClase + ">();" + BREAK_LINE);
                       }
                   }
                   
                   if (entity.getRelaciones().size() > 0) {
                       for (RelationshipPojo relacion : entity.getRelaciones()) {
                           String nombreMapper = (relacion.getNameClassRelacion() + "Mapper");

                           if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                               sb3.append("        entity.set" + relacion.getNameRelacion() + "(" + nombreMapper.toLowerCase() + ".PojoToEntity(pojo.get"
                                       + relacion.getNameRelacion() + "()));" + BREAK_LINE);
                           }

                           
                           if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {
                               String relacionClase = relacion.getNameClassRelacion();
                               String relacionName = relacion.getNameRelacion();
                               sb3.append("        for (" + relacionClase + "Pojo" + " " + relacionName + "pojo" + " : pojo.get" + relacionName + "()) {" + BREAK_LINE);
                               sb3.append("            list" + relacionName + ".add(" + nombreMapper.toLowerCase() + ".PojoToEntity(" + relacionName + "pojo" + "));" + BREAK_LINE);
                               sb3.append("        }" + BREAK_LINE);
                               sb3.append("        entity.set" + relacionName + "(" + "list" + relacionName + ");" + BREAK_LINE);
                           }
                       }
                   }
               } 
           }
       sb3.append("            return entity;" + BREAK_LINE);
       sb3.append("        }" + BREAK_LINE);
       sb3.append(BREAK_LINE);
       return sb3;
   }


   private StringBuilder createPojoToEntity(EntityPojo entity) {
      
   	 StringBuilder sb4 = new StringBuilder();
       for (EntityPojo pojo : toPojos) {
           String[] clavePojo = pojo.getNombreClase().split("Pojo");
           if (entity.getNombreClase().equals(clavePojo[0])) {
               sb4.append(BREAK_LINE);
               sb4.append("    public " + pojo.getNombreClase() + " entityToPojo(" + entity.getNombreClase() + " entity) {" + BREAK_LINE);
               sb4.append("        "+ pojo.getNombreClase() + " pojo = new "+pojo.getNombreClase()+"();" + BREAK_LINE);

               if (entity.getRelaciones().size() > 0) {
                   for (RelationshipPojo relacion : pojo.getRelaciones()) {
                       String relacionClase = relacion.getNameClassRelacion();
                       String relacionNameList = relacion.getNameRelacion();
                       sb4.append("        List<" + relacionClase + "> list" + relacionNameList + " = new ArrayList<" + relacionClase + ">();" + BREAK_LINE);
                   }
               }

               if (entity.getAtributos().size() > 0) {
                   for (AttributePojo atributo : pojo.getAtributos()) {
                       String atributoNameSetGet = atributo.getAtributoName().substring(0, 1).toUpperCase()
                               + atributo.getAtributoName().substring(1).toLowerCase();
                     
                       sb4.append("        pojo.set" + atributoNameSetGet + "(entity.get" + atributoNameSetGet + "());" + BREAK_LINE);
                   }
               }
               
               sb4.append("\r\n");

               if (entity.getRelaciones().size() > 0) {
                   for (RelationshipPojo relacion : pojo.getRelaciones()) {
                       String[] EntidadClase =  relacion.getNameClassRelacion().split("Pojo");
                       String relacionClase = EntidadClase[0];
                       String nombreMapper = (relacionClase + "Mapper");
                       String[] Entidadname =  relacion.getNameRelacion().split("Pojo");;
                       String relacionName = Entidadname[0];
                       String relacionNameList = relacion.getNameRelacion();

                       if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne") ) {
                           sb4.append("        pojo.set" + relacion.getNameRelacion() + "(" + nombreMapper.toLowerCase() + ".entityToPojo(entity.get"
                                   + relacion.getNameRelacion() + "()));" + BREAK_LINE);
                       }

                       if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {
                           sb4.append("        for (" + relacionClase + " " + relacionName + "entity" + " : entity.get" + relacionName + "()) {" + BREAK_LINE);
                           sb4.append("            list" + relacionNameList + ".add(" + nombreMapper.toLowerCase() + ".entityToPojo(" + relacionName +"entity"+" ));" + BREAK_LINE);
                           sb4.append("        }" + BREAK_LINE);
                           sb4.append("        pojo.set" + relacionName + "(" + "list" + relacionNameList + ");" + BREAK_LINE);
                       }
                   }
               }
           }
       }
       sb4.append("            return pojo;" + BREAK_LINE);
       sb4.append("        }" + BREAK_LINE);
       sb4.append(BREAK_LINE);
       return sb4;
   }

}




