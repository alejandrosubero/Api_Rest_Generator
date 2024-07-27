package com.generator.model.tempate.controller;


import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.build.models.ModelOup;

import com.generator.core.design.*;
import com.generator.core.design.ParameterClassMethod;
import com.generator.core.design.reference.*;

import com.generator.core.format.formatter.Formatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.generator.core.pojos.*;
import java.util.*;

public class TemplateControllers implements IModelBuilder {

    private static final Log logger = LogFactory.getLog(TemplateControllers.class);

    private ArchivoBaseDatosPojo archivo;
    private String paquete;
    private List<EntityPojo> entidades;
    private  static TemplateControllers instance;

    private TemplateControllers() {
    }

    public static TemplateControllers getInstance(){
        if(instance == null){
            return new TemplateControllers();
        }else {
            return instance;
        }
    }

    @Override
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        this.entidades = archivo.getEntidades();
        this.paquete = archivo.getPackageNames();
        this.archivo = baseFilePojo;
        return this.createController(entidades, creator);
    }



    private  Set<ModelOup> createController(List<EntityPojo> entidadesList, Creador creator) {
        Set<ModelOup> response = new HashSet<>();

        for (EntityPojo entidad : entidadesList) {
            try {
                if (entidad.getIsEntity()) {
                    String keynameOfClass = stringEnsamble(entidad.getNombreClase(), "Controller");
                    response.add(
                            ModelOup.builder()
                                    .packageNane("controller")
                                    .nameOfClass(keynameOfClass)
                                    .classInString(metods(entidad))
                                    .directoryForJava(creator.directionForJava())
                                    .build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    private String metods(EntityPojo entidad) {
        List<String> listContentLines = new ArrayList<>();
        ClassDesign classTemplate = null;

        logger.info("Create Controller metodos  for Entity:  " + entidad.getNombreClase());

        try {

            ParameterClassMethod servicePrameter =
                    ParameterClassMethod.builder()
                            .modifier(Modifier.Private)
                            .annotations(List.of(AUTOWIRED))
                            .parameterSignatuer(
                                    stringEnsamble(
                                            entidad.getNombreClase(),
                                            "Service ",
                                            entidad.getNombreClase().toLowerCase(),
                                            "Service;"
                                    )).build();


            if (this.archivo.getMethodManager().isMethodFindByOrLoop()) {
                listContentLines.add(this.createLoop(entidad).toString());
            }

            if (this.archivo.getMethodManager().isMethodContaining()) {
                listContentLines.add(this.creatContain(entidad).toString());
            }

            if (this.archivo.getMethodManager().isMethodfindById()) {
                listContentLines.add(this.createfindId(entidad).toString());
            }

            if (this.archivo.getMethodManager().isMethodgetAll()) {
                listContentLines.add(this.createfindAll(entidad).toString());
            }

            if (this.archivo.getMethodManager().isMetohdSave()) {
                listContentLines.add(this.createSalve(entidad).toString());
            }

            if (archivo.checkAtributos(entidad)) {
                listContentLines.add(this.createFinBySearch(entidad).toString());
            }

            if (entidad.getDelete()) {
                listContentLines.add(this.createDelete(entidad).toString());
            }

            if (this.archivo.getMethodManager().isMethodContainingRelacion())
                listContentLines.add(this.findByRelacion(entidad).toString());

            if (this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
                listContentLines.add(this.findByRelacionNoBidirecional(entidad).toString());

            listContentLines.add( new AnotacionesJava(archivo).apacheSoftwareLicensed().toString());

            classTemplate = ClassDesign.builder()
                    .packagePaht(archivo.getPackageNames())
                    .packageName("serviceImplement")
                    .imports(this.createImport(entidad))
                    .annotation(List.of("@RestController", "@CrossOrigin(origins = \"*\")", "@RequestMapping(\"/", entidad.getNombreClase().toLowerCase(), "\")"))
                    .modifier(Modifier.Public)
                    .className(stringEnsamble(entidad.getNombreClase(), "Controller"))
                    .classType(ClassType.CLASS)
                    .classParameterClassMethods(List.of(servicePrameter))
                    .isClassIsImplement(false)
                    .isClassIsInheritance(false)
                    .classImplement(null)
                    .content(new Formatter().simpleFormat(this.listStringStructureToColummString(listContentLines)))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classTemplate.toString();
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


    private List<String> createImport(EntityPojo entidad) {
        List<String> importList = new ArrayList<>();

        importList.add(new AnotacionesJava(archivo).creatNotaClase().toString());
        importList.add(BREAK_LINE);
        importList.add(stringEnsamble("package ", paquete, ".controller;"));
        importList.add(stringEnsamble("import ", paquete, ".entitys.", entidad.getNombreClase(), ";"));
        importList.add(stringEnsamble("import ", paquete, ".service.", entidad.getNombreClase(), "Service;"));
        importList.add(importController07());

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            importList.add(stringEnsamble("import ", paquete, ".", entidad.getPaquete(), ".", relacion.getNameClassRelacion(), ";"));
        }

        return importList;
    }


    private StringBuffer createLoop(EntityPojo entidad) {

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();

            if (!atributos.getsId()) {

                sb3.append(BodyMethodDesign.builder()
                        .bodyLines(
                                toList(BREAK_LINE, stringEnsamble("@GetMapping(\"/Get", atrubutoObjeto, "/{", atrubutoObjeto, "}\")"),
                                        stringEnsamble("private " + entidad.getNombreClase(), " findBy", atributoName, "(@PathVariable(\"", atrubutoObjeto, "\") ", atributos.getTipoDato(), "  ", atrubutoObjeto, ") {"),
                                        stringEnsamble("return ", entidad.getNombreClase().toLowerCase(), "Service.findBy", atributoName, "(", atrubutoObjeto, ");"),
                                        stringEnsamble("}")
                                )
                        ).build().toString());
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
                sb3.append(
                        MethodDesign.builder()
                                .annotation(List.of("@GetMapping(\"/Get", atrubutoObjeto, "contain/{", atrubutoObjeto, "}\")", BREAK_LINE))
                                .modifiers(Modifier.Private)
                                .returnsType(RetunsType.List)
                                .returnsClass(entidad.getNombreClase())
                                .methodName(stringEnsamble(" findBy", atributoName, "Contain"))
                                .parameter(List.of(
                                        ParameterClassMethod.builder()
                                                .atributoClass(stringEnsamble("@PathVariable(\"", atrubutoObjeto, "\") ",
                                                        atributo.getTipoDato()))
                                                .atributoName(atrubutoObjeto).build()))
                                .methodBody(BodyMethodDesign.builder()
                                        .bodyLines(List.of(
                                                "return ", entidad.getNombreClase().toLowerCase(), "Service.findBy", atributoName,
                                                "Containing(", atrubutoObjeto, ");", BREAK_LINE)
                                        ).build().toString()
                                ).build().toString());
            }
        }
        return sb3;
    }


    private StringBuffer createfindId(EntityPojo entidad) {
        StringBuffer sb4 = new StringBuffer();
        sb4.append(BREAK_LINE);
        sb4.append(
                MethodDesign.builder()
                        .annotation(List.of("@GetMapping(\"/Get", entidad.getNombreClase(), "/{id}\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.List)
                        .returnsClass(entidad.getNombreClase())
                        .methodName(stringEnsamble("findById", entidad.getNombreClase()))
                        .parameter(List.of(
                                ParameterClassMethod.builder()
                                        .atributoClass(stringEnsamble("(@PathVariable(\"id\") ", idTipoDato(entidad)))
                                        .atributoName("id").build()))
                        .methodBody(BodyMethodDesign.builder()
                                .bodyLines(List.of(
                                        "return ", entidad.getNombreClase().toLowerCase(), "Service.findById(id);", BREAK_LINE,
                                        BREAK_LINE)
                                ).build().toString()
                        ).build().toString());
        return sb4;
    }


    private StringBuffer createfindAll(EntityPojo entidad) {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(BREAK_LINE);
        sb5.append(
                MethodDesign.builder()
                        .annotation(List.of("@GetMapping(\"/GetAll", entidad.getNombreClase(), "\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.List)
                        .returnsClass(entidad.getNombreClase())
                        .methodName(stringEnsamble("getAll", entidad.getNombreClase()))
                        .parameter(null)
                        .methodBody(BodyMethodDesign.builder()
                                .bodyLines(List.of(
                                        "return ", entidad.getNombreClase().toLowerCase(), "Service.getAll", entidad.getNombreClase(), "();",
                                        BREAK_LINE)
                                ).build().toString()
                        ).build().toString());

        return sb5;
    }


    private StringBuffer createFinBySearch(EntityPojo entidad) {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(BREAK_LINE);
        sb5.append(
                MethodDesign.builder()
                        .annotation(List.of("@GetMapping(\"/Search\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.List)
                        .returnsClass(entidad.getNombreClase())
                        .methodName(stringEnsamble("finBySearch", entidad.getNombreClase()))
                        .parameter(List.of(
                                ParameterClassMethod.builder()
                                        .atributoClass(stringEnsamble("@RequestParam(value = \"search\") String"))
                                        .atributoName("search").build()))
                        .methodBody(BodyMethodDesign.builder()
                                .bodyLines(List.of(
                                        "return ",
                                        entidad.getNombreClase().toLowerCase(),
                                        "Service.search(search);",
                                        BREAK_LINE)
                                ).build().toString()
                        ).build().toString());

        return sb5;
    }


    private StringBuffer createSalve(EntityPojo entidad) {
        StringBuffer sb6 = new StringBuffer();

        sb6.append(
                MethodDesign.builder()
                        .annotation(List.of("@PostMapping(\"/saveOrUpdate\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.none)
                        .returnsClass("Boolean")
                        .methodName(stringEnsamble("saveOrUpdate", entidad.getNombreClase()))
                        .parameter(List.of(
                                ParameterClassMethod.builder()
                                        .atributoClass(stringEnsamble("@RequestBody ", entidad.getNombreClase()))
                                        .atributoName(entidad.getNombreClase().toLowerCase()).build()))
                        .methodBody(BodyMethodDesign.builder()
                                .bodyLines(List.of(
                                        "return ",
                                        entidad.getNombreClase().toLowerCase(),
                                        "Service.saveOrUpdate", entidad.getNombreClase(),
                                        "(", entidad.getNombreClase().toLowerCase(), ");",
                                        BREAK_LINE)
                                ).build().toString()
                        ).build().toString());


        return sb6;
    }


    private StringBuffer findByRelacion(EntityPojo entidad) {
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                sb61.append(
                        MethodDesign.builder()
                                .annotation(List.of("@PostMapping(\"/Get_", relacion.getNameRelacion(), "_contain/\")"))
                                .modifiers(Modifier.Private)
                                .returnsType(RetunsType.List)
                                .returnsClass(entidad.getNombreClase())
                                .methodName(stringEnsamble(" findBy", relacion.getNameClassRelacion()))
                                .parameter(List.of(
                                        ParameterClassMethod.builder()
                                                .atributoClass(stringEnsamble("@RequestBody ", relacion.getNameClassRelacion()))
                                                .atributoName(relacion.getNameClassRelacion().toLowerCase()).build()))
                                .methodBody(BodyMethodDesign.builder()
                                        .bodyLines(List.of(
                                                "return ", entidad.getNombreClase().toLowerCase(),
                                                "Service.findBy", relacion.getNameClassRelacion(),
                                                "Containing(", relacion.getNameClassRelacion().toLowerCase(), ");", BREAK_LINE)
                                        ).build().toString()
                                ).build().toString());
            }
        }
        return sb61;
    }


    private StringBuffer findByRelacionNoBidirecional(EntityPojo entidad) {
        StringBuffer sb61 = new StringBuffer(BREAK_LINE);
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sb61.append(
                        MethodDesign.builder()
                                .annotation(List.of("@PostMapping(\"/findRelacion\")", BREAK_LINE))
                                .modifiers(Modifier.Private)
                                .returnsType(RetunsType.List)
                                .returnsClass(entidad.getNombreClase())
                                .methodName(stringEnsamble("findRelacion", relacion.getNameClassRelacion()))
                                .parameter(List.of(
                                        ParameterClassMethod.builder()
                                                .atributoClass(stringEnsamble("@RequestBody ", relacion.getNameClassRelacion()))
                                                .atributoName(relacion.getNameClassRelacion().toLowerCase()).build()))
                                .methodBody(BodyMethodDesign.builder()
                                        .bodyLines(List.of("return ", entidad.getNombreClase().toLowerCase()
                                                , "Service.findByRelacion", relacion.getNameClassRelacion(),
                                                "(", relacion.getNameClassRelacion().toLowerCase(), ");")
                                        ).build().toString()
                                ).build().toString());
            }
        }
        return sb61;
    }


    private StringBuffer createDelete(EntityPojo entidad) {
        return new StringBuffer(
                MethodDesign.builder()
                        .annotation(List.of("@DeleteMapping(\"/delete", entidad.getNombreClase(), "/{id}\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.none)
                        .returnsClass(RetunsType.Boolean.toString())
                        .methodName(stringEnsamble("delete", entidad.getNombreClase()))
                        .parameter(List.of(
                                ParameterClassMethod.builder()
                                        .atributoClass(
                                                stringEnsamble("@PathVariable(\"id\")", SPACE, idTipoDato(entidad))
                                        )
                                        .atributoName("id").build()))
                        .methodBody(
                                BodyMethodDesign.builder()
                                        .bodyLines(List.of(
                                                "return ", entidad.getNombreClase().toLowerCase(),
                                                "Service.delete", entidad.getNombreClase(),
                                                "(id); }", BREAK_LINE)
                                        ).build().toString()
                        ).build().toString()
        );
    }


}
