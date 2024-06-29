package com.Generator.apirest.modelo.back.controller;


import com.Generator.apirest.files.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.core.pojos.back.AttributePojo;
import com.Generator.apirest.core.pojos.back.EntityPojo;
import com.Generator.apirest.core.pojos.back.RelationshipPojo;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.interfaces.FileCreateService;
import com.Generator.apirest.core.interfaces.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;


//@Scope("singleton")
@Component
public class CreateControlles07 implements IImportModel, ControllerInterface {

    private static final Log logger = LogFactory.getLog(CreateControlles07.class);

    private FileCreateService fileCreateService;
    private Creador creador;
    private ArchivoBaseDatosPojo archivo;
    private String paquete;
    private List<EntityPojo> entidades;


    public CreateControlles07(FileCreateService fileCreateService) {
        this.fileCreateService = fileCreateService;
    }


    @Override
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        this.creador = creator;
        this.entidades = archivo.getEntidades();
        this.paquete = archivo.getPackageNames();
        this.archivo = baseFilePojo;
        this.createController(entidades);
    }

    public void startCreacionControlles(ArchivoBaseDatosPojo archivo, Creador creador) {
        this.creador = creador;
        this.entidades = archivo.getEntidades();
        this.paquete = archivo.getPackageNames();
        this.archivo = archivo;
        this.createController(entidades);
    }

    private void createController(List<EntityPojo> entidadesList) {

        for (EntityPojo entidad : entidadesList) {
            String nameOfClass = entidad.getNombreClase() + "Controller";
            try {
                if (entidad.getIsEntity()) {
                    fileCreateService.createFileClassJava(nameOfClass, "controller", metods(entidad), this.creador.directionForJava());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private StringBuffer metods(EntityPojo entidad) {

        StringBuffer sb = new StringBuffer(BREAK_LINE);
        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());
        try {

            sb.append(new AnotacionesJava(archivo).creatNotaClase());
            sb.append(BREAK_LINE);
            sb.append(this.createImport(entidad));

            sb.append(BREAK_LINE);
            sb.append(this.createTituloClass(entidad));
            sb.append(BREAK_LINE);

            if (this.archivo.getMethodManager().isMethodFindByOrLoop())
                sb.append(this.createLoop(entidad));
            sb.append(BREAK_LINE);

            if (this.archivo.getMethodManager().isMethodContaining())
                sb.append(this.creatContain(entidad));
            sb.append(BREAK_LINE);

            if (this.archivo.getMethodManager().isMethodfindById())
                sb.append(this.createfindId(entidad));
            sb.append(BREAK_LINE);

            if (this.archivo.getMethodManager().isMethodgetAll())
                sb.append(this.createfindAll(entidad));
            sb.append(BREAK_LINE);

            if (this.archivo.getMethodManager().isMetohdSave())
                sb.append(this.createSalve(entidad));
            sb.append(BREAK_LINE);


//            sb.append(this.createUpdate(entidad));
//            sb.append("\r\n");
//

//            sb.append(this.createsaveOrUpdate(entidad));
//            sb.append("\r\n");

            if (archivo.checkAtributos(entidad)) {
                sb.append(this.createFinBySearch(entidad));
                sb.append(BREAK_LINE);
            }

            if (entidad.getDelete()) {
                sb.append(this.createDelete(entidad));
            }

            if (this.archivo.getMethodManager().isMethodContainingRelacion())
                sb.append(this.findByRelacion(entidad));

            if (this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
                sb.append(this.findByRelacionNoBidirecional(entidad));

        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}" + BREAK_LINE);
        sb.append(new AnotacionesJava(archivo).apacheSoftwareLicensed() + BREAK_LINE);
        return sb;
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


    private StringBuffer createImport(EntityPojo entidad) {
        StringBuffer sb1 = new StringBuffer();
        sb1.append(stringEnsamble(Lists.newArrayList("package ", paquete, ".controller;")));
        sb1.append(DOUBLEBREAK_LINE);
        sb1.append(stringEnsamble(Lists.newArrayList("import ", paquete, ".entitys.", entidad.getNombreClase(), ";")));
        sb1.append(BREAK_LINE);
        sb1.append(stringEnsamble(Lists.newArrayList("import ", paquete, ".service.", entidad.getNombreClase(), "Service;")));
        sb1.append(BREAK_LINE);
        sb1.append(importController07());
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb1.append(stringEnsamble(Lists.newArrayList("import ", paquete, ".", entidad.getPaquete(), ".", relacion.getNameClassRelacion(), ";")));
        }
        sb1.append(BREAK_LINE);
        return sb1;
    }


    private StringBuffer createTituloClass(EntityPojo entidad) {
        StringBuffer sb2 = new StringBuffer();
        sb2.append(stringEnsamble(Lists.newArrayList("@RestController", BREAK_LINE)));
        sb2.append(stringEnsamble(Lists.newArrayList("@CrossOrigin(origins = \"*\")", BREAK_LINE)));
        sb2.append(stringEnsamble(Lists.newArrayList("@RequestMapping(\"/", entidad.getNombreClase().toLowerCase(), "\")", BREAK_LINE)));
        sb2.append(stringEnsamble(Lists.newArrayList("public class ", entidad.getNombreClase(), "Controller {", BREAK_LINE)));
        sb2.append(stringEnsamble(Lists.newArrayList(AUTOWIRED, BREAK_LINE)));
        sb2.append(stringEnsamble(Lists.newArrayList(entidad.getNombreClase(), "Service ", entidad.getNombreClase().toLowerCase(), "Service;", DOUBLEBREAK_LINE)));
        return sb2;
    }


    private StringBuffer createLoop(EntityPojo entidad) {

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();

            if (!atributos.getsId()) {
                sb3.append(BREAK_LINE);
                sb3.append(stringEnsamble(Lists.newArrayList("        @GetMapping(\"/Get", atrubutoObjeto, "/{", atrubutoObjeto, "}\")", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("        private " + entidad.getNombreClase(), " findBy", atributoName, "(@PathVariable(\"", atrubutoObjeto, "\") ", atributos.getTipoDato(), "  ", atrubutoObjeto, ") {", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.findBy", atributoName, "(", atrubutoObjeto, ");", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("        }", BREAK_LINE)));
            }
        }
        return sb3;
    }


    private StringBuffer creatContain(EntityPojo entidad) {

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributo = entidad.getAtributos();

        for (AttributePojo atributo : listAtributo) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            String atrubutoObjeto = atributo.getAtributoName().toLowerCase();
            if (!atributo.getsId()) {
                sb3.append(BREAK_LINE);
                sb3.append(stringEnsamble(Lists.newArrayList("        @GetMapping(\"/Get", atrubutoObjeto, "contain/{", atrubutoObjeto, "}\")", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("        private List<", entidad.getNombreClase(), "> findBy", atributoName, "Contain(@PathVariable(\"", atrubutoObjeto, "\") ", atributo.getTipoDato(), "  ", atrubutoObjeto, ") {", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.findBy", atributoName, "Containing(", atrubutoObjeto, ");", BREAK_LINE)));
                sb3.append(stringEnsamble(Lists.newArrayList("        }", BREAK_LINE)));
            }
        }
        return sb3;
    }


    private StringBuffer createfindId(EntityPojo entidad) {
        StringBuffer sb4 = new StringBuffer();
        sb4.append(BREAK_LINE);
        sb4.append(stringEnsamble(Lists.newArrayList("        @GetMapping(\"/Get", entidad.getNombreClase(), "/{id}\")", BREAK_LINE)));
        sb4.append(stringEnsamble(Lists.newArrayList("          private ", entidad.getNombreClase(), " findById", "(@PathVariable(\"id\") ", idTipoDato(entidad), " id) {", BREAK_LINE)));
        sb4.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.findById(id);", BREAK_LINE)));
        sb4.append(stringEnsamble(Lists.newArrayList("          }", BREAK_LINE)));
        return sb4;
    }


    private StringBuffer createfindAll(EntityPojo entidad) {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(BREAK_LINE);
        sb5.append(stringEnsamble(Lists.newArrayList("        @GetMapping(\"/GetAll", entidad.getNombreClase(), "\")", BREAK_LINE)));
        sb5.append(stringEnsamble(Lists.newArrayList("        private  List<", entidad.getNombreClase(), "> getAll", entidad.getNombreClase(), "(){", BREAK_LINE)));
        sb5.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.getAll", entidad.getNombreClase(), "();}", BREAK_LINE)));
        sb5.append(BREAK_LINE);
        return sb5;
    }


    private StringBuffer createFinBySearch(EntityPojo entidad) {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(BREAK_LINE);
        sb5.append(stringEnsamble(Lists.newArrayList("        @GetMapping(\"/Search\")", BREAK_LINE)));
        sb5.append(stringEnsamble(Lists.newArrayList("        private  List<", entidad.getNombreClase(), "> finBySearch(@RequestParam(value = \"search\") String search){", BREAK_LINE)));
        sb5.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.search(search);}", BREAK_LINE)));
        sb5.append(BREAK_LINE);
        return sb5;
    }


    private StringBuffer createSalve(EntityPojo entidad) {
        StringBuffer sb6 = new StringBuffer();
        sb6.append(BREAK_LINE);
        sb6.append(stringEnsamble(Lists.newArrayList("        @PostMapping(\"/saveOrUpdate\")", BREAK_LINE)));
        sb6.append(stringEnsamble(Lists.newArrayList("        private Boolean  saveOrUpdate", entidad.getNombreClase(), "(@RequestBody ", entidad.getNombreClase(), " ", entidad.getNombreClase().toLowerCase(), "){ ", BREAK_LINE)));
        sb6.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.saveOrUpdate", entidad.getNombreClase(), "(", entidad.getNombreClase().toLowerCase(), "); }", BREAK_LINE)));
        sb6.append(BREAK_LINE);
        return sb6;
    }


    private StringBuffer findByRelacion(EntityPojo entidad) {
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(stringEnsamble(Lists.newArrayList("        @PostMapping(\"/Get_", relacion.getNameRelacion(), "_contain/\")", "\r\n")));
                sb61.append(stringEnsamble(Lists.newArrayList("        private List<", entidad.getNombreClase(), "> findBy", relacion.getNameClassRelacion(), "(@RequestBody ", relacion.getNameClassRelacion(), " ", relacion.getNameClassRelacion().toLowerCase(), "){ ", BREAK_LINE)));
                sb61.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.findBy", relacion.getNameClassRelacion(), "Containing(", relacion.getNameClassRelacion().toLowerCase(), "); }", BREAK_LINE)));
                sb61.append(BREAK_LINE);
            }
        }
        return sb61;
    }


    private StringBuffer findByRelacionNoBidirecional(EntityPojo entidad) {
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append(BREAK_LINE);
                sb61.append(stringEnsamble(Lists.newArrayList("        @PostMapping(\"/findRelacion\")", BREAK_LINE)));
                sb61.append(stringEnsamble(Lists.newArrayList("        private List<", entidad.getNombreClase(), "> findRelacion", relacion.getNameClassRelacion()
                        , "(@RequestBody ", relacion.getNameClassRelacion(), " ", relacion.getNameClassRelacion().toLowerCase(), "){ ", BREAK_LINE)));
                sb61.append(stringEnsamble(Lists.newArrayList("            return " + entidad.getNombreClase().toLowerCase()
                        , "Service.findByRelacion", relacion.getNameClassRelacion(), "(", relacion.getNameClassRelacion().toLowerCase(), "); }", BREAK_LINE)));
                sb61.append(BREAK_LINE);
            }
        }
        return sb61;
    }


    private StringBuffer createDelete(EntityPojo entidad) {
        StringBuffer sb9 = new StringBuffer();
        sb9.append(BREAK_LINE);
        sb9.append(stringEnsamble(Lists.newArrayList("        @DeleteMapping(\"/delete", entidad.getNombreClase(), "/{id}\")", BREAK_LINE)));
        sb9.append(stringEnsamble(Lists.newArrayList("            private boolean delete", entidad.getNombreClase(), "(@PathVariable(\"id\") ", idTipoDato(entidad), " id) {" + BREAK_LINE)));
        sb9.append(stringEnsamble(Lists.newArrayList("            return ", entidad.getNombreClase().toLowerCase(), "Service.delete", entidad.getNombreClase(), "(id); }", BREAK_LINE)));
        return sb9;
    }

}
