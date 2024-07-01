package com.generator.model.controller;



import com.generator.core.design.BodyMethodDesign;
import com.generator.core.design.ClassDesign;
import com.generator.core.design.MethodDesign;
import com.generator.core.design.ParameterClassMethod;
import com.generator.core.design.reference.Modifier;
import com.generator.core.design.reference.RetunsType;
import com.generator.core.interfaces.FileCreateService;
import com.generator.core.interfaces.IImportModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.back.AttributePojo;
import com.generator.core.pojos.back.Creador;
import com.generator.core.pojos.back.EntityPojo;
import com.generator.core.pojos.back.RelationshipPojo;
import com.generator.core.pojos.notas.AnotacionesJava;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.ArrayList;
import java.util.List;


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
                    fileCreateService.createFileClassJava(
                            nameOfClass,
                            "controller",
                            metods(entidad),
                            this.creador.directionForJava()
                    );
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




            sb.append(BREAK_LINE);
            sb.append(this.createTituloClass(entidad));
            sb.append(BREAK_LINE);


            ClassDesign classTemplate = ClassDesign.builder()
                    .packagePaht(archivo.getPackageNames())
                    .packageName("serviceImplement")
                    .imports(this.createImport(entidad))
                    .annotation(List.of("@Service"))
                    .modifier(Modifier.Public)
                    .className(nameOfClass)
                    .classType(ClassType.CLASS)
                    .isClassIsImplement(true)
                    .isClassIsInheritance(false)
                    .classImplement(serviceName)
                    .content(new Formatter().simpleFormat(sbh.toString()))
                    .classParameterClassMethods(List.of(classParametersRepositories,classParametersMapper,classParameterLogger))
                    .build();







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


    private StringBuffer createTituloClass(EntityPojo entidad) {
        StringBuffer sb2 = new StringBuffer();

        sb2.append( BodyMethodDesign.builder()
                .bodyLines(
                        toList(
                                stringEnsamble("@RestController"),
                                stringEnsamble("@CrossOrigin(origins = \"*\")"),
                                stringEnsamble("@RequestMapping(\"/", entidad.getNombreClase().toLowerCase(), "\")"),
                                stringEnsamble("public class ", entidad.getNombreClase(), "Controller {"),
                                stringEnsamble(AUTOWIRED),
                                stringEnsamble(entidad.getNombreClase(), "Service ", entidad.getNombreClase().toLowerCase(), "Service;")
                        )).build().toString());

        return sb2;
    }


    private StringBuffer createLoop(EntityPojo entidad) {

        StringBuffer sb3 = new StringBuffer();
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
            String atrubutoObjeto = atributos.getAtributoName().toLowerCase();

            if (!atributos.getsId()) {

                sb3.append( BodyMethodDesign.builder()
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
                                .returnsType(RetunsType. List)
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
                        .returnsType(RetunsType. List)
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
                        .returnsType(RetunsType. List)
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
                        .returnsType(RetunsType. List)
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
                                        .bodyLines(List.of("return ",entidad.getNombreClase().toLowerCase()
                                                        ,"Service.findByRelacion", relacion.getNameClassRelacion(),
                                                        "(", relacion.getNameClassRelacion().toLowerCase(), ");")
                                                ).build().toString()
                                ).build().toString());
            }
        }
        return sb61;
    }


    private StringBuffer createDelete(EntityPojo entidad) {
        return  new StringBuffer(
                MethodDesign.builder()
                        .annotation(List.of("@DeleteMapping(\"/delete", entidad.getNombreClase(), "/{id}\")", BREAK_LINE))
                        .modifiers(Modifier.Private)
                        .returnsType(RetunsType.none)
                        .returnsClass(RetunsType.Boolean.toString())
                        .methodName(stringEnsamble("delete", entidad.getNombreClase()))
                        .parameter(List.of(
                                ParameterClassMethod.builder()
                                        .atributoClass(
                                          stringEnsamble("@PathVariable(\"id\")", SPACE,idTipoDato(entidad))
                                        )
                                .atributoName("id").build()))
                        .methodBody(
                                BodyMethodDesign.builder()
                                        .bodyLines(List.of(
                                                "return ",entidad.getNombreClase().toLowerCase(),
                                                "Service.delete", entidad.getNombreClase(),
                                                "(id); }", BREAK_LINE)
                                        ).build().toString()
                        ).build().toString()
        );
    }

}
