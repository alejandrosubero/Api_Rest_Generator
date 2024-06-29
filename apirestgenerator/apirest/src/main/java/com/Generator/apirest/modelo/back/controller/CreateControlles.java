package com.Generator.apirest.modelo.back.controller;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateControlles implements IImportModel  ,ControllerInterface {

    protected static final Log logger = LogFactory.getLog(CreateControlles.class);

    @Override
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        this.createController(creator, baseFilePojo);
    }

    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.createController(creadors, archivo);
    }

    private void createController( Creador creadors, ArchivoBaseDatosPojo archivo){

        for ( EntityPojo entidad:  archivo.getEntidades() ) {
            String nameOfClass = entidad.getNombreClase()+ "Controller";
            try {
                if(entidad.getIsEntity()) {   
                    String escritos = metods(archivo, entidad, creadors).toString();
                    createArchivoController(escritos, nameOfClass, creadors, archivo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private StringBuffer metods(ArchivoBaseDatosPojo archivo, EntityPojo entidad, Creador creadors) {

        StringBuffer sb = new StringBuffer(BREAK_LINE);
        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());
        try {
              	
        	sb.append(new AnotacionesJava(archivo).creatNotaClase().toString()+BREAK_LINE);
            sb.append(BREAK_LINE);
            sb.append(this.createImport(creadors, entidad));

            sb.append(BREAK_LINE);
            sb.append(this.createTituloClass(entidad));
            sb.append(BREAK_LINE);

          	if(archivo.getMethodManager().isMethodFindByOrLoop()) 
            sb.append(this.createLoop(entidad));
            sb.append(BREAK_LINE);
            
            if(archivo.getMethodManager().isMethodContaining())
            sb.append(this.createLoopContain(entidad));
            sb.append(BREAK_LINE);
            
            if(archivo.getMethodManager().isMethodfindById())
            sb.append(this.createfindId(entidad));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodgetAll()) 
            sb.append(this.createfindAll(entidad));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMetohdSave())
            sb.append(createSalve(entidad));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodUpdate())
            sb.append(this.createUpdate(entidad));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodsaveOrUpdate())
            sb.append(this.createsaveOrUpdate(entidad));
            sb.append(BREAK_LINE);

            if(entidad.getDelete()) {
                sb.append(this.createDelete(entidad));
            }

            if(archivo.getMethodManager().isMethodContainingRelacion())
            sb.append(this.findByRelacion(entidad));

            if(archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
            sb.append(this.findByRelacionNoBidirecional(entidad));

        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}"+BREAK_LINE);
        sb.append(AnotacionesJava.apacheSoftwareLicensed()+BREAK_LINE);
        return sb;
    }


    private void createArchivoController( String escrito, String nameOfClass,  Creador creador, ArchivoBaseDatosPojo archivo) {
    	try {
            String direction = 
           		    this.path(Lists.newArrayList(
           		    		creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src","main", "java", creador.getCom() ,creador.getPackageNames1(), creador.getArtifact(),"controller")
           		    );
            creador.crearArchivo(direction, escrito, nameOfClass + ".java");
            
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e);
        }
    }



    private String idTipoDato(EntityPojo entidad){
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String datoTipo = "Integer";
        for (AttributePojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        return datoTipo;
    }


    private  StringBuffer createImport(Creador creador, EntityPojo entidad) {

        StringBuffer sb1 = new StringBuffer();
            sb1.append("package " + creador.getPackageNames() + ".controller;");
            sb1.append(BREAK_LINE);
            sb1.append("import "+creador.getPackageNames()+".entitys."+entidad.getNombreClase()+";");
            sb1.append(BREAK_LINE);
            sb1.append("import "+creador.getPackageNames()+".service."+entidad.getNombreClase()+"Service;");
            sb1.append(BREAK_LINE);
            
            sb1.append(importAutowiredAnnotation());
            sb1.append(importGroupOne()); 
                       
            for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + creador.getPackageNames() + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion()+";");
            sb1.append(BREAK_LINE);
             }
            sb1.append(BREAK_LINE);
        return sb1;
    }


    private  StringBuffer createTituloClass(EntityPojo entidad){

    StringBuffer sb2 = new StringBuffer();
        sb2.append("@RestController\r\n");
        sb2.append("@CrossOrigin(origins = \"*\")\r\n");
        sb2.append("@RequestMapping(\"/"+entidad.getNombreClase().toLowerCase()+"\")");
        sb2.append(BREAK_LINE);
        sb2.append("public class " + entidad.getNombreClase() + "Controller {\r\n");
        sb2.append(BREAK_LINE);
        sb2.append("@Autowired");
        sb2.append(BREAK_LINE);
        sb2.append( entidad.getNombreClase() + "Service "+ entidad.getNombreClase().toLowerCase() +"Service;");
        sb2.append(BREAK_LINE);
        sb2.append(BREAK_LINE);
        return sb2;
    }


    private StringBuffer createLoop(EntityPojo entidad){

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase()
                                                + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();
            if (!atributos.getsId()) {
                sb3.append(BREAK_LINE);
                sb3.append("        @GetMapping(\"/Get");
                sb3.append(atrubutoObjeto);
                sb3.append("/{");
                sb3.append(atrubutoObjeto);
                sb3.append("}\")");
                sb3.append(BREAK_LINE);
                sb3.append("        private ");
                sb3.append(entidad.getNombreClase());
                sb3.append(" findBy");
                sb3.append(atributoName);
                sb3.append("(@PathVariable(\"");
                sb3.append(atrubutoObjeto);
                sb3.append("\") ");
                sb3.append(atributos.getTipoDato());
                sb3.append("  ");
                sb3.append(atrubutoObjeto);
                sb3.append(") {");
                sb3.append(BREAK_LINE);
                sb3.append("            return ");
                sb3.append(entidad.getNombreClase().toLowerCase());
                sb3.append("Service.findBy");
                sb3.append(atributoName );
                sb3.append("(");
                sb3.append(atrubutoObjeto);
                sb3.append(");");
                sb3.append(BREAK_LINE);
                sb3.append("        }");
                sb3.append(BREAK_LINE);
                }
        }
        return sb3;
    }

    
    
    private StringBuffer createLoopContain(EntityPojo entidad){

        StringBuffer sb3b = new StringBuffer();

        for (AttributePojo atributo : entidad.getAtributos()) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
            
            if (!atributo.getsId()) {
                sb3b.append("        @GetMapping(\"/Get"+atrubutoObjeto+"contain/{"+atrubutoObjeto+"}\")" +BREAK_LINE);
                sb3b.append("        private List<"+entidad.getNombreClase()+"> findBy"+atributoName
                                                    +"Contain(@PathVariable(\""+atrubutoObjeto+"\") "
                                                    +atributo.getTipoDato()
                                                    +"  "+atrubutoObjeto+") {" +BREAK_LINE);
                
                sb3b.append("            return "+entidad.getNombreClase().toLowerCase()
                										+"Service.findBy"+atributoName
                                                        +"Containing("+atrubutoObjeto+");"+BREAK_LINE);
                sb3b.append("        }"+BREAK_LINE);
            }
        }
        return sb3b;
    }
    
    
    private StringBuffer createfindId(EntityPojo entidad){
        StringBuffer sb4 = new StringBuffer();
                sb4.append(BREAK_LINE);
                
                sb4.append("        @GetMapping(\"/Get" + entidad.getNombreClase() + "/{id}\")" + BREAK_LINE);
                sb4.append("          private " + entidad.getNombreClase() + " findById" + "(@PathVariable(\"id\") " 
                								+idTipoDato(entidad) + " id) {" + BREAK_LINE);
                sb4.append("            return " + entidad.getNombreClase().toLowerCase() 
                								 + "Service.findById(id);" + BREAK_LINE);
                sb4.append("          }" + BREAK_LINE);
        return sb4;
    }



    private StringBuffer createfindAll(EntityPojo entidad){
        StringBuffer sb5 = new StringBuffer();
                sb5.append(BREAK_LINE);
                sb5.append("        @GetMapping(\"/GetAll" + entidad.getNombreClase() + "\")" + BREAK_LINE);
                sb5.append("        private  List<" + entidad.getNombreClase() + "> getAll"+entidad.getNombreClase()+"(){" + BREAK_LINE);
                sb5.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.getAll" + entidad.getNombreClase() + "();}" + BREAK_LINE);
                sb5.append(BREAK_LINE);
        return sb5;
    }


    private StringBuffer createSalve(EntityPojo entidad){
        StringBuffer sb6 = new StringBuffer();
                sb6.append(BREAK_LINE);
                sb6.append("        @PostMapping(\"/save\")" + BREAK_LINE);
                sb6.append("        private Boolean  save" + entidad.getNombreClase() + "(@RequestBody "+entidad.getNombreClase()+" "+entidad.getNombreClase().toLowerCase() +"){ " + BREAK_LINE);
                sb6.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.save"
                                                    +entidad.getNombreClase()+ "("+entidad.getNombreClase().toLowerCase()+"); }" + BREAK_LINE);
                sb6.append(BREAK_LINE);
                return sb6;
    }


    private StringBuffer findByRelacion(EntityPojo entidad){
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion: entidad.getRelaciones()) {
//            if (relacion.getBidireccional()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append("        @PostMapping(\"/Get_" + relacion.getNameRelacion() + "_contain/\")" + BREAK_LINE);
                sb61.append("        private List<" + entidad.getNombreClase() + "> findBy" + relacion.getNameClassRelacion() + "(@RequestBody " + relacion.getNameClassRelacion() + " " +  relacion.getNameClassRelacion().toLowerCase() + "){ " + BREAK_LINE);
                sb61.append("            return " + entidad.getNombreClase().toLowerCase() + "Service.findBy" + relacion.getNameClassRelacion()
                        + "Containing(" + relacion.getNameClassRelacion().toLowerCase() + "); }" + BREAK_LINE);
                sb61.append(BREAK_LINE);
            }
        }
        return sb61;
    }


    private StringBuffer findByRelacionNoBidirecional(EntityPojo entidad){
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion: entidad.getRelaciones()) {
               
        	if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
              
                sb61.append(BREAK_LINE);
                sb61.append("        @PostMapping(\"/findRelacion\")" + BREAK_LINE);
                sb61.append("        private List<" + entidad.getNombreClase() + "> findRelacion"
                									 +relacion.getNameClassRelacion()
                                                      +"(@RequestBody "+ relacion.getNameClassRelacion()
                                                       + " " + relacion.getNameClassRelacion().toLowerCase()
                                                        + "){ " + BREAK_LINE);
                
                sb61.append("            return " + entidad.getNombreClase().toLowerCase()
                                             + "Service.findByRelacion"+relacion.getNameClassRelacion()+"(" 
                                             	+ relacion.getNameClassRelacion().toLowerCase()
                                                  + "); }" + BREAK_LINE);
                sb61.append(BREAK_LINE);
            }
        }
        return sb61;
    }



    private StringBuffer createUpdate(EntityPojo entidad){
        StringBuffer sb7 = new StringBuffer();
        sb7.append(BREAK_LINE);
        sb7.append("        @PostMapping(\"/Update\")" + BREAK_LINE);
        sb7.append("        private "+idTipoDato(entidad)+" Update" + entidad.getNombreClase() + "(@RequestBody "
                                                +entidad.getNombreClase()+" " +entidad.getNombreClase().toLowerCase()
                                                 +"){ " + BREAK_LINE);
        sb7.append("            "+entidad.getNombreClase().toLowerCase() 
        		                   +"Service.update" +entidad.getNombreClase()
                                     + "("+entidad.getNombreClase().toLowerCase()+");"
                                       + BREAK_LINE);

        String atributoName = "";
        for (AttributePojo atributo:entidad.getAtributos()) {
            if(atributo.getsId()){
                atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            }
        }

//        sb7.append("            return "+entidad.getNombreClase().toLowerCase()+".getId(); }"+BREAK_LINE);
        sb7.append("            return "+entidad.getNombreClase().toLowerCase()+".get" + atributoName +"(); }"+BREAK_LINE);
        return sb7;
    }


    private StringBuffer createsaveOrUpdate(EntityPojo entidad){

        StringBuffer sb8 = new StringBuffer();
        sb8.append(BREAK_LINE);
        sb8.append("        @PostMapping(\"/saveOrUpdate\")");
        sb8.append(BREAK_LINE);
        sb8.append("        private boolean saveOrUpdate"); 
        sb8.append(entidad.getNombreClase());
        sb8.append( "(@RequestBody ");
        sb8.append(entidad.getNombreClase());
        sb8.append(" ");
        sb8.append(entidad.getNombreClase().toLowerCase());
        sb8.append("){ ");
        sb8.append(BREAK_LINE);
        
        sb8.append("            return ");
        sb8.append(entidad.getNombreClase().toLowerCase());
        sb8.append("Service.saveOrUpdate");
        sb8.append(entidad.getNombreClase());
        sb8.append("("+entidad.getNombreClase().toLowerCase()+"); }");
        sb8.append(BREAK_LINE);
        
        return sb8;
    }



    private StringBuffer createDelete(EntityPojo entidad){
        StringBuffer sb9 = new StringBuffer();
        sb9.append(BREAK_LINE);
        sb9.append("        @DeleteMapping(\"/delete"); 
        sb9.append( entidad.getNombreClase()); 
        sb9.append("/{id}\")");
        sb9.append(BREAK_LINE);
        sb9.append("            private boolean delete");
        sb9.append(entidad.getNombreClase());
        sb9.append("(@PathVariable(\"id\") ");
        sb9.append(idTipoDato(entidad));
        sb9.append(" id) {");
        sb9.append(BREAK_LINE);
        sb9.append("            return ");
        sb9.append(entidad.getNombreClase().toLowerCase()); 
        sb9.append("Service.delete");
        sb9.append(entidad.getNombreClase() + "(id); }");
        sb9.append(BREAK_LINE);
        
        return sb9;
    }
}
