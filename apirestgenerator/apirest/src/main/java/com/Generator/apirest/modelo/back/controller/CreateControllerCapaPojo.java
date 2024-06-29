package com.Generator.apirest.modelo.back.controller;


import com.Generator.apirest.files.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.core.pojos.back.AttributePojo;
import com.Generator.apirest.core.pojos.back.LayerPojo;
import com.Generator.apirest.core.pojos.back.EntityPojo;
import com.Generator.apirest.core.pojos.back.RelationshipPojo;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.interfaces.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CreateControllerCapaPojo implements IImportModel, ControllerInterface {

    protected static final Log logger = LogFactory.getLog(CreateControllerCapaPojo.class);

    private List<EntityPojo> toPojos = new ArrayList<>();
    private List<EntityPojo> toEntidad = new ArrayList<>();

    @Override
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        this.createController(creator, baseFilePojo);
    }


    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.createController(creadors, archivo);
    }

    private void createController(Creador creadors, ArchivoBaseDatosPojo archivo) {

        try {
			if (archivo.getEntidades().size() > 0) {
				this.separateEntidadToPojos(archivo.getEntidades(), archivo.getCapaPojo());
				for (EntityPojo entidad : archivo.getEntidades()) {
					if (entidad.getIsEntity()) {
						String escritos = metods(entidad, creadors, archivo).toString();
						createArchivoController(escritos, entidad.getNombreClase()+"Controller", archivo.getProyectoName(),creadors);
					}
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void separateEntidadToPojos(List<EntityPojo> entidadesList, LayerPojo layerPojo) {
        logger.info("Inicia la separacion de entidades");
        for (EntityPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(layerPojo.getModelT()) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }


    private StringBuffer metods(EntityPojo entidad, Creador creadors, ArchivoBaseDatosPojo archivo) {

        StringBuffer sb = new StringBuffer(BREAK_LINE);
        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());

        try { 
        	AnotacionesJava anotacionesJava = new AnotacionesJava(archivo);
            sb.append(anotacionesJava.creatNotaClase()+BREAK_LINE);
            
            sb.append(BREAK_LINE);
            sb.append(this.createImport(entidad, creadors.getPackageNames(), archivo.getCapaPojo()));
            sb.append(BREAK_LINE);
            sb.append(this.createTituloClass(entidad));
            sb.append(BREAK_LINE);
            sb.append(this.createAutoWire(entidad));
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
            sb.append(this.createSave(entidad, archivo.getCapaPojo()));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodUpdate())
            sb.append(this.createUpdate(entidad, archivo.getCapaPojo()));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodsaveOrUpdate())
            sb.append(this.createsaveOrUpdate(entidad, archivo.getCapaPojo()));
            sb.append(BREAK_LINE);

            if(archivo.getMethodManager().isMethodDelete()){
                sb.append(this.createDelete(entidad));
            }
            
            if(archivo.getMethodManager().isMethodContainingRelacion())
            sb.append(this.findByRelacion(entidad, archivo.getCapaPojo()));

        	if(archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
            sb.append(this.findByRelacionNoBidirecional(entidad, archivo.getCapaPojo()));
           
        	sb.append("}" + BREAK_LINE);
        	sb.append(AnotacionesJava.apacheSoftwareLicensed() + BREAK_LINE);
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }


    private void createArchivoController(String escrito, String nameOfClass, String proyectoName, Creador creador) {

        try {
              String direction = this.path(Lists.newArrayList(
                                           creador.getDireccionDeCarpeta() + proyectoName,
                                           "src","main","java",creador.getCom(), creador.getPackageNames1(),
                                            creador.getArtifact(), "controller")
                        );
            
                 creador.crearArchivo(direction, escrito, nameOfClass + ".java");

        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error(e);
        }
    }


    private String idTipoDato(EntityPojo entidad) {
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String datoTipo = "Integer";
        for (AttributePojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        return datoTipo;
    }


    private StringBuffer createImport(EntityPojo entidad, String paquete, LayerPojo layerPojo) {

        StringBuffer sb1 = new StringBuffer();
        sb1.append("package " + paquete + ".controller;");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".entitys." + entidad.getNombreClase() + ";");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".validation." + entidad.getNombreClase() + "Validation;");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".mapper." + entidad.getNombreClase() + "Mapper;");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".service." + entidad.getNombreClase() + "Service;");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".mapper.MapperEntityRespone;");
        sb1.append(BREAK_LINE);
        sb1.append("import " + paquete + ".dto.EntityRespone;");
        sb1.append(BREAK_LINE);
        
        sb1.append(importAutowiredAnnotation());
        sb1.append(importGroupOne()); 
        sb1.append(SPRING_DATAACCESS_EXCEPTION_IMPORT);
        sb1.append(SPRING_RESPONSE_ENTITY_IMPORT);
        sb1.append(SPRING_HTTP_STATUS_IMPORT);
        
      
        for (EntityPojo entidadPojo : toPojos) {
            String[] clavePojo = entidadPojo.getNombreClase().split(layerPojo.getModelM());
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                sb1.append("import " + paquete + "." + entidadPojo.getPaquete() + "." + entidadPojo.getNombreClase() + ";");
                sb1.append(BREAK_LINE);
            }
        }

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" );
            sb1.append(BREAK_LINE);
            sb1.append("import " + paquete + ".validation." + relacion.getNameClassRelacion() + "Validation;");
            sb1.append(BREAK_LINE);
            sb1.append("import " + paquete + ".mapper." + relacion.getNameClassRelacion() + "Mapper;");
            sb1.append(BREAK_LINE);
        }

        for (int i = 0; i < toPojos.size(); i++) {
            String[] clavePojo = toPojos.get(i).getNombreClase().split(layerPojo.getModelM());
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                for (RelationshipPojo relacion : toPojos.get(i).getRelaciones()) {
                    sb1.append("import " + paquete + "." + toPojos.get(i).getPaquete() + "." + relacion.getNameClassRelacion() + ";");
                    sb1.append(BREAK_LINE);
                }
            }
        }
        sb1.append(BREAK_LINE);
        return sb1;
    }


    private StringBuffer createTituloClass(EntityPojo entidad) {

        StringBuffer sb2a = new StringBuffer();

        sb2a.append("@RestController");
        sb2a.append(BREAK_LINE);
        sb2a.append("@CrossOrigin(origins = \"*\")");
        sb2a.append(BREAK_LINE);
        sb2a.append("@RequestMapping(\"/" + entidad.getNombreClase().toLowerCase() + "\")");
        sb2a.append(BREAK_LINE);
        sb2a.append("public class " + entidad.getNombreClase() + "Controller {");
        sb2a.append(DOUBLEBREAK_LINE);
        return sb2a;
    }

    private StringBuffer createAutoWire(EntityPojo entidad){

        StringBuffer sb2 = new StringBuffer(BREAK_LINE);
        String validationAutowiredName ="";
        String mapperAutowiredName ="";

        sb2.append(DOUBLETAB+"@Autowired" );
        sb2.append(BREAK_LINE);
        sb2.append(DOUBLETAB+ entidad.getNombreClase() + "Service " + entidad.getNombreClase().toLowerCase() + "Service;");
        sb2.append(DOUBLEBREAK_LINE);

        sb2.append(DOUBLETAB+"@Autowired");
        sb2.append(BREAK_LINE);
        validationAutowiredName = DOUBLETAB+ entidad.getNombreClase() + "Validation " + entidad.getNombreClase().toLowerCase() + "ValidationService;";
        sb2.append(validationAutowiredName);
        sb2.append(DOUBLEBREAK_LINE);

        sb2.append(DOUBLETAB+"@Autowired");
        sb2.append(BREAK_LINE);
        mapperAutowiredName= DOUBLETAB+ entidad.getNombreClase() + "Mapper " + entidad.getNombreClase().toLowerCase() + "Mapper;";
        sb2.append(mapperAutowiredName);
        sb2.append(DOUBLEBREAK_LINE);

        sb2.append(DOUBLETAB+"@Autowired");
        sb2.append(BREAK_LINE);
        sb2.append(DOUBLETAB+"MapperEntityRespone mapperEntityRespone;");
        sb2.append(DOUBLEBREAK_LINE);

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            String validationAutowiredName2 =DOUBLETAB+ relacion.getNameClassRelacion() + "Validation " + relacion.getNameClassRelacion().toLowerCase() + "ValidationService;";
            String mapperAutowiredName2 =DOUBLETAB+ relacion.getNameClassRelacion() + "Mapper " + relacion.getNameClassRelacion().toLowerCase() + "Mapper;" ;

            if(!validationAutowiredName.equals(validationAutowiredName2)){
                sb2.append(DOUBLETAB+"@Autowired" );
                sb2.append(BREAK_LINE);
                sb2.append(validationAutowiredName2);
            }
            sb2.append(DOUBLEBREAK_LINE);
            if(!mapperAutowiredName.equals(mapperAutowiredName2)){
                sb2.append(DOUBLETAB+"@Autowired");
                sb2.append(BREAK_LINE);
                sb2.append(mapperAutowiredName2);
            }
            sb2.append(DOUBLEBREAK_LINE);
        }
        return sb2;
    }

    private StringBuffer createLoop(EntityPojo entidad) {

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        for (AttributePojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();

            if (!atributos.getsId()) {
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+" @GetMapping(\"/Get" + atrubutoObjeto + "/{" + atrubutoObjeto + "}\")" );
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+"private  ResponseEntity<EntityRespone> findBy" + atributoName + "(@PathVariable(\"" + atrubutoObjeto + "\") " + atributos.getTipoDato() + "  " + atrubutoObjeto + ") {");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB + atributos.getTipoDato() + " busca = (" + atributos.getTipoDato() + ") " + validationService + ".validation(" + atrubutoObjeto + ");");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+"try {");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+DOUBLETAB+"EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(" );
                sb3.append(entidad.getNombreClase().toLowerCase() + "Service.findBy" + atributoName + "(busca));");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+DOUBLETAB+" return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+TAB+"} catch (DataAccessException e) {");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+DOUBLETAB+"EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, \"Ocurrio un error\", e.getMessage());");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+TAB+"return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+" }");
                sb3.append(BREAK_LINE);
                sb3.append(DOUBLETAB+"}" );
                sb3.append(BREAK_LINE);
            }
        }
        return sb3;
    }

    
    private StringBuffer createLoopContain(EntityPojo entidad) {
        StringBuffer sb3b = new StringBuffer();
        for (AttributePojo atributo : entidad.getAtributos()) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
            
            if (!atributo.getsId()) {
                sb3b.append(BREAK_LINE);
                sb3b.append(DOUBLETAB+"@GetMapping(\"/Get" + atrubutoObjeto + "contain/{" + atrubutoObjeto + "}\")");
                sb3b.append(BREAK_LINE);
                
                sb3b.append(DOUBLETAB+"private ResponseEntity<EntityRespone> findBy" + atributoName + "Contain(@PathVariable(\"");
                sb3b.append(atrubutoObjeto + "\") " + atributo.getTipoDato());
                sb3b.append("  " + atrubutoObjeto + ") {");
                sb3b.append(BREAK_LINE);

                sb3b.append(DOUBLETAB+TAB + atributo.getTipoDato() + " busca = (" + atributo.getTipoDato() + ") ");
                sb3b.append(entidad.getNombreClase().toLowerCase() + "ValidationService" );
                sb3b.append(".validation(" + atrubutoObjeto + ");");
                sb3b.append(BREAK_LINE);

                sb3b.append(DOUBLETAB+DOUBLETAB +"EntityRespone entityRespone = mapperEntityRespone.setEntityT(" + entidad.getNombreClase().toLowerCase());
                sb3b.append("Service.findBy" + atributoName + "Containing(busca));");
                sb3b.append(BREAK_LINE);

                sb3b.append(DOUBLETAB+DOUBLETAB +"return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);");
                sb3b.append(BREAK_LINE);

                sb3b.append(DOUBLETAB+TAB +"}" );
                sb3b.append(BREAK_LINE);
            }
        }
        return sb3b;
    }
    


    private StringBuffer createfindId(EntityPojo entidad) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        StringBuffer sb4 = new StringBuffer();
        sb4.append(BREAK_LINE);
        sb4.append(DOUBLETAB+"@GetMapping(\"/Get" + entidad.getNombreClase() + "/{id}\")");
        sb4.append(BREAK_LINE);
        sb4.append(DOUBLETAB+"private ResponseEntity<EntityRespone> findById" + "(@PathVariable(\"id\") String id) {");
        sb4.append(BREAK_LINE);
        sb4.append(DOUBLETAB+"EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(" );
        sb4.append(entidad.getNombreClase().toLowerCase() + "Service.findById(" + validationService + ".valida_id(id))); ");
        sb4.append(BREAK_LINE);
        sb4.append(DOUBLETAB+TAB+"return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);");
        sb4.append(BREAK_LINE);
        sb4.append(DOUBLETAB+"}");
        sb4.append(BREAK_LINE);
        return sb4;
    }


    private StringBuffer createfindAll(EntityPojo entidad) {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(BREAK_LINE);
        sb5.append(DOUBLETAB+"@GetMapping(\"/GetAll" + entidad.getNombreClase() + "\")");
        sb5.append(BREAK_LINE);
        sb5.append(DOUBLETAB+"private  ResponseEntity<EntityRespone> getAll" + entidad.getNombreClase() + "(){");
        sb5.append(BREAK_LINE);
        sb5.append(DOUBLETAB+TAB+"EntityRespone entityRespone = mapperEntityRespone.setEntityT(");
        sb5.append(entidad.getNombreClase().toLowerCase() + "Service.getAll" + entidad.getNombreClase() + "());");
        sb5.append(BREAK_LINE);
        sb5.append(DOUBLETAB+DOUBLETAB+"return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK); }");
        sb5.append(DOUBLEBREAK_LINE);
        return sb5;
    }


    private StringBuffer createSave(EntityPojo entidad, LayerPojo layerPojo) {
        StringBuffer sb6 = new StringBuffer();
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+"@PostMapping(\"/save\")");
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+"private Boolean  save" + entidad.getNombreClase() + "(@RequestBody " + entidad.getNombreClase() );
        sb6.append(layerPojo.getModelM()+"  " + entidad.getNombreClase().toLowerCase() + "){ ");
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.save");
        sb6.append(entidad.getNombreClase() + "(" + contenido + " ); }");
        sb6.append(DOUBLEBREAK_LINE);
        return sb6;
    }


    private StringBuffer createUpdate(EntityPojo entidad, LayerPojo layerPojo) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        StringBuffer sb7 = new StringBuffer();
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+"@PostMapping(\"/Update\")");
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+"private " + idTipoDato(entidad) + " Update" + entidad.getNombreClase());
        sb7.append("(@RequestBody " + entidad.getNombreClase() + layerPojo.getModelM()+"  ");
        sb7.append(entidad.getNombreClase().toLowerCase() + "){ ");
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+TAB+ entidad.getNombreClase().toLowerCase() + "Service.update" + entidad.getNombreClase() + "(" + contenido + ");");
        sb7.append(BREAK_LINE);
        String atributoName = "";
        for (AttributePojo atributo:entidad.getAtributos()) {
            if(atributo.getsId()){
                atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            }
        }
        sb7.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + ".get" + atributoName +"(); }");
        sb7.append(BREAK_LINE);
        return sb7;
    }


    private StringBuffer createsaveOrUpdate(EntityPojo entidad, LayerPojo layerPojo) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        StringBuffer sb8 = new StringBuffer();
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+"@PostMapping(\"/saveOrUpdate\")");
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+"private boolean saveOrUpdate" + entidad.getNombreClase() + "(@RequestBody " + entidad.getNombreClase() + layerPojo.getModelM()+"  ");
        sb8.append(entidad.getNombreClase().toLowerCase() + "){ ");
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.saveOrUpdate");
        sb8.append( entidad.getNombreClase() + "(" + contenido + " ); }");
        sb8.append(BREAK_LINE);
        return sb8;
    }


    private StringBuffer createDelete(EntityPojo entidad) {
        StringBuffer sb9 = new StringBuffer();
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        sb9.append(BREAK_LINE);
        sb9.append(DOUBLETAB+"@DeleteMapping(\"/delete" + entidad.getNombreClase() + "/{id}\")" );
        sb9.append(BREAK_LINE);
        sb9.append(DOUBLETAB+"private boolean delete" + entidad.getNombreClase() + "(@PathVariable(\"id\") String id) {" );
        sb9.append(BREAK_LINE);
        sb9.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.delete");
        sb9.append(entidad.getNombreClase() + "(" + validationService + ".valida_id(id)); }");
        sb9.append(BREAK_LINE);
        return sb9;
    }

    private StringBuffer findByRelacion(EntityPojo entidad, LayerPojo layerPojo) {
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            String validationService = relacion.getNameClassRelacion().toLowerCase() + "ValidationService";
            String mapperService = relacion.getNameClassRelacion().toLowerCase() + "Mapper";
            String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + relacion.getNameClassRelacion().toLowerCase() + "))";
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"@PostMapping(\"/Get_" + relacion.getNameRelacion() + "_contain/\")" );
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"private List<" + entidad.getNombreClase()+ layerPojo.getModelM() + "> findBy" + relacion.getNameClassRelacion());
                sb61.append("(@RequestBody " + relacion.getNameClassRelacion() + layerPojo.getModelM() +"  " + relacion.getNameClassRelacion().toLowerCase() + "){ ");
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.findBy" + relacion.getNameClassRelacion());
                sb61.append("Containing(" + contenido + "); }");
                sb61.append(DOUBLEBREAK_LINE);
            }
        }
        return sb61;
    }

    private StringBuffer findByRelacionNoBidirecional(EntityPojo entidad, LayerPojo layerPojo) {

        StringBuffer sb61 = new StringBuffer();

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"@PostMapping(\"/find" + relacion.getNameRelacion() + "\")" );
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"private ResponseEntity<EntityRespone> findRelacion" + relacion.getNameClassRelacion() + "(@RequestBody "
                        + relacion.getNameClassRelacion() + layerPojo.getModelM() +" " + relacion.getNameClassRelacion().toLowerCase() + "){ ");
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+DOUBLETAB+"EntityRespone entityRespone = mapperEntityRespone.setEntityT(");
                sb61.append(entidad.getNombreClase().toLowerCase() + "Service.findByRelacion" + relacion.getNameClassRelacion());
                sb61.append("(" + relacion.getNameClassRelacion().toLowerCase() + "Mapper.pojoToEntity(");
                sb61.append(relacion.getNameClassRelacion().toLowerCase() + "ValidationService.valida(");
                sb61.append(relacion.getNameClassRelacion().toLowerCase() + "))));");
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+TAB+"return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);");
                sb61.append(BREAK_LINE);
                sb61.append("}");
                sb61.append(BREAK_LINE);
            }
        }
        return sb61;
    }


}
