package com.Generator.apirest.modelo.back;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.CapaPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.RelacionPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CreateControllerCapaPojo implements IImportModel {

	private List<EntidadesPojo> toPojos = new ArrayList<>();
    private List<EntidadesPojo> toEntidad = new ArrayList<>();
    protected static final Log logger = LogFactory.getLog(CreateControllerCapaPojo.class);

    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.createController(creadors, archivo);
    }

    private void createController(Creador creadors, ArchivoBaseDatosPojo archivo) {

        try {
			if (archivo.getEntidades().size() > 0) {
				this.separateEntidadToPojos(archivo.getEntidades(), archivo.getCapaPojo());
				for (EntidadesPojo entidad : archivo.getEntidades()) {
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


    private void separateEntidadToPojos(List<EntidadesPojo> entidadesList, CapaPojo capaPojo) {
        logger.info("Inicia la separacion de entidades");
        for (EntidadesPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(capaPojo.getModelT()) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }


    private StringBuilder metods(EntidadesPojo entidad, Creador creadors, ArchivoBaseDatosPojo archivo) {

        StringBuilder sb = new StringBuilder(BREAK_LINE);
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


    private String idTipoDato(EntidadesPojo entidad) {
        List<AtributoPojo> listAtributos = entidad.getAtributos();
        String datoTipo = "Integer";
        for (AtributoPojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        return datoTipo;
    }


    private StringBuilder createImport(EntidadesPojo entidad, String paquete, CapaPojo capaPojo) {

        StringBuilder sb1 = new StringBuilder();
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
        
      
        for (EntidadesPojo entidadPojo : toPojos) {
            String[] clavePojo = entidadPojo.getNombreClase().split(capaPojo.getModelM());
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                sb1.append("import " + paquete + "." + entidadPojo.getPaquete() + "." + entidadPojo.getNombreClase() + ";");
                sb1.append(BREAK_LINE);
            }
        }

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            sb1.append("import " + paquete + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" );
            sb1.append(BREAK_LINE);
            sb1.append("import " + paquete + ".validation." + relacion.getNameClassRelacion() + "Validation;");
            sb1.append(BREAK_LINE);
            sb1.append("import " + paquete + ".mapper." + relacion.getNameClassRelacion() + "Mapper;");
            sb1.append(BREAK_LINE);
        }

        for (int i = 0; i < toPojos.size(); i++) {
            String[] clavePojo = toPojos.get(i).getNombreClase().split(capaPojo.getModelM());
            if (entidad.getNombreClase().equals(clavePojo[0])) {
                for (RelacionPojo relacion : toPojos.get(i).getRelaciones()) {
                    sb1.append("import " + paquete + "." + toPojos.get(i).getPaquete() + "." + relacion.getNameClassRelacion() + ";");
                    sb1.append(BREAK_LINE);
                }
            }
        }
        sb1.append(BREAK_LINE);
        return sb1;
    }


    private StringBuilder createTituloClass(EntidadesPojo entidad) {

        StringBuilder sb2a = new StringBuilder();

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

    private StringBuilder createAutoWire(EntidadesPojo entidad){

        StringBuilder sb2 = new StringBuilder(BREAK_LINE);
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

        for (RelacionPojo relacion : entidad.getRelaciones()) {
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

    private StringBuilder createLoop(EntidadesPojo entidad) {

        StringBuilder sb3 = new StringBuilder();
        List<AtributoPojo> listAtributos = entidad.getAtributos();
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        for (AtributoPojo atributos : listAtributos) {
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

    
    private StringBuilder createLoopContain(EntidadesPojo entidad) {
        StringBuilder sb3b = new StringBuilder();
        for (AtributoPojo atributo : entidad.getAtributos()) {
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
    


    private StringBuilder createfindId(EntidadesPojo entidad) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        StringBuilder sb4 = new StringBuilder();
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


    private StringBuilder createfindAll(EntidadesPojo entidad) {
        StringBuilder sb5 = new StringBuilder();
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


    private StringBuilder createSave(EntidadesPojo entidad, CapaPojo capaPojo) {
        StringBuilder sb6 = new StringBuilder();
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+"@PostMapping(\"/save\")");
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+"private Boolean  save" + entidad.getNombreClase() + "(@RequestBody " + entidad.getNombreClase() );
        sb6.append(capaPojo.getModelM()+"  " + entidad.getNombreClase().toLowerCase() + "){ ");
        sb6.append(BREAK_LINE);
        sb6.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.save");
        sb6.append(entidad.getNombreClase() + "(" + contenido + " ); }");
        sb6.append(DOUBLEBREAK_LINE);
        return sb6;
    }


    private StringBuilder createUpdate(EntidadesPojo entidad, CapaPojo capaPojo) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        StringBuilder sb7 = new StringBuilder();
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+"@PostMapping(\"/Update\")");
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+"private " + idTipoDato(entidad) + " Update" + entidad.getNombreClase());
        sb7.append("(@RequestBody " + entidad.getNombreClase() + capaPojo.getModelM()+"  ");
        sb7.append(entidad.getNombreClase().toLowerCase() + "){ ");
        sb7.append(BREAK_LINE);
        sb7.append(DOUBLETAB+TAB+ entidad.getNombreClase().toLowerCase() + "Service.update" + entidad.getNombreClase() + "(" + contenido + ");");
        sb7.append(BREAK_LINE);
        String atributoName = "";
        for (AtributoPojo atributo:entidad.getAtributos()) {
            if(atributo.getsId()){
                atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            }
        }
        sb7.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + ".get" + atributoName +"(); }");
        sb7.append(BREAK_LINE);
        return sb7;
    }


    private StringBuilder createsaveOrUpdate(EntidadesPojo entidad, CapaPojo capaPojo) {
        String validationService = entidad.getNombreClase().toLowerCase() + "ValidationService";
        String mapperService = entidad.getNombreClase().toLowerCase() + "Mapper";
        String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + entidad.getNombreClase().toLowerCase() + "))";
        StringBuilder sb8 = new StringBuilder();
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+"@PostMapping(\"/saveOrUpdate\")");
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+"private boolean saveOrUpdate" + entidad.getNombreClase() + "(@RequestBody " + entidad.getNombreClase() + capaPojo.getModelM()+"  ");
        sb8.append(entidad.getNombreClase().toLowerCase() + "){ ");
        sb8.append(BREAK_LINE);
        sb8.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.saveOrUpdate");
        sb8.append( entidad.getNombreClase() + "(" + contenido + " ); }");
        sb8.append(BREAK_LINE);
        return sb8;
    }


    private StringBuilder createDelete(EntidadesPojo entidad) {
        StringBuilder sb9 = new StringBuilder();
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

    private StringBuilder findByRelacion(EntidadesPojo entidad, CapaPojo capaPojo) {
        StringBuilder sb61 = new StringBuilder(BREAK_LINE);

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            String validationService = relacion.getNameClassRelacion().toLowerCase() + "ValidationService";
            String mapperService = relacion.getNameClassRelacion().toLowerCase() + "Mapper";
            String contenido = mapperService + ".pojoToEntity(" + validationService + ".valida(" + relacion.getNameClassRelacion().toLowerCase() + "))";
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"@PostMapping(\"/Get_" + relacion.getNameRelacion() + "_contain/\")" );
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"private List<" + entidad.getNombreClase()+capaPojo.getModelM() + "> findBy" + relacion.getNameClassRelacion());
                sb61.append("(@RequestBody " + relacion.getNameClassRelacion() + capaPojo.getModelM() +"  " + relacion.getNameClassRelacion().toLowerCase() + "){ ");
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+TAB+"return " + entidad.getNombreClase().toLowerCase() + "Service.findBy" + relacion.getNameClassRelacion());
                sb61.append("Containing(" + contenido + "); }");
                sb61.append(DOUBLEBREAK_LINE);
            }
        }
        return sb61;
    }

    private StringBuilder findByRelacionNoBidirecional(EntidadesPojo entidad, CapaPojo capaPojo) {

        StringBuilder sb61 = new StringBuilder();

        for (RelacionPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"@PostMapping(\"/find" + relacion.getNameRelacion() + "\")" );
                sb61.append(BREAK_LINE);
                sb61.append(DOUBLETAB+"private ResponseEntity<EntityRespone> findRelacion" + relacion.getNameClassRelacion() + "(@RequestBody "
                        + relacion.getNameClassRelacion() + capaPojo.getModelM() +" " + relacion.getNameClassRelacion().toLowerCase() + "){ ");
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
