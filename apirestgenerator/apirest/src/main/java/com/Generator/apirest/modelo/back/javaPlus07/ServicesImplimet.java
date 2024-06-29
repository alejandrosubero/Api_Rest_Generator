package com.Generator.apirest.modelo.back.javaPlus07;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.core.build.*;
import com.Generator.apirest.core.design.BodyMethodDesign;
import com.Generator.apirest.core.design.ClassDesign;
import com.Generator.apirest.core.design.MethodDesign;
import com.Generator.apirest.core.design.ParameterClassMethod;
import com.Generator.apirest.core.design.reference.ClassType;
import com.Generator.apirest.core.design.reference.Modifier;
import com.Generator.apirest.core.design.reference.RetunsType;
import com.Generator.apirest.core.format.formatter.Formatter;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.LayerPojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ServicesImplimet implements IImportModel {


    private String returnObjectClass;
    private String returnObjectClassPackage;
    private String mapperServiceNombreClase;
    private String mapperNombreClaseService;


    private LayerPojo layerPojo;
    protected static final Log logger = LogFactory.getLog(CreateServices.class);

    public void startCreacionImplement(ArchivoBaseDatosPojo archivo, Creador creadors) {
        try {
            this.layerPojo = archivo.getCapaPojo();
            this.crearImplemet(archivo, creadors);
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
        }
    }


    private void crearImplemet(ArchivoBaseDatosPojo archivo, Creador creadors) throws InterruptedException {
        for (EntityPojo entidad : archivo.getEntidades()) {
            if (entidad.getIsEntity()) {
                this.createServiceImpl(archivo, creadors, entidad);
            }
        }
    }


    private void createServiceImpl(ArchivoBaseDatosPojo archivo, Creador creadors, EntityPojo entidad) throws InterruptedException {

        StringBuffer sbh = new StringBuffer(BREAK_LINE);
        StringBuffer main = new StringBuffer(BREAK_LINE);


        String nameOfClass = entidad.getNombreClase() + "ServiceImplement";
        String repositorieName = entidad.getNombreClase() + "Repository";
        String repositorieNameOjecte = repositorieName.toLowerCase();
        String serviceName = entidad.getNombreClase() + "Service";
        returnObjectClass = archivo.getCapaPojo().getCreateCapaPojoForEntitys() ? stringEnsamble(List.of(entidad.getNombreClase(), archivo.getCapaPojo().getModelM())) : entidad.getNombreClase();
        returnObjectClassPackage = archivo.getCapaPojo().getCreateCapaPojoForEntitys() ? archivo.getCapaPojo().getModelT() : "entitys";

        mapperServiceNombreClase = stringEnsamble(List.of(entidad.getNombreClase().toLowerCase(), "Mapper"));
        mapperNombreClaseService =  stringEnsamble(List.of(entidad.getNombreClase(), "Mapper", TAB));



//        sbh.append(this.createImport(archivo.getPackageNames(), serviceName, repositorieName, entidad));
//        sbh.append(this.createTitulo(nameOfClass, serviceName, repositorieName, repositorieNameOjecte));


//        if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
//            sbh.append(this.createAutowiredMapper(entidad));
//        }

        if (archivo.getMethodManager().isMethodFindByOrLoop()) {
            sbh.append(this.crearMetodoloop(entidad, repositorieNameOjecte));
        }

        sbh.append(this.metods(archivo, entidad, repositorieNameOjecte, entidad.getNombreClase()));


        List<String> importList = new ArrayList<>();
        importList.add(this.importPahtBuild(archivo.getPackageNames(),"service",serviceName));
        importList.add(this.importPahtBuild(archivo.getPackageNames(),"repository",repositorieName));
        importList.add(this.importGroupServiceClassImplement());
        importList.add(this.importPahtBuild(archivo.getPackageNames(),entidad.getPaquete(),entidad.getNombreClase()));
        importList.add(this.importPahtBuild(archivo.getPackageNames(),returnObjectClassPackage,returnObjectClass));

        if (mapperServiceNombreClase != null && !mapperServiceNombreClase.equals("")) {
            importList.add(
                    this.importPahtBuild(archivo.getPackageNames(),
                            "mapper",
                            stringEnsamble(List.of(entidad.getNombreClase(),
                                    "Mapper", SPACE))));
        }

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            importList.add(
                    this.importPahtBuild(archivo.getPackageNames(), this.layerPojo.getModelT().trim(),
                            stringEnsamble(List.of(relacion.getNameClassRelacion(), this.layerPojo.getModelM()))
                    ));
            importList.add(this.importPahtBuild(archivo.getPackageNames(),entidad.getPaquete(),relacion.getNameClassRelacion()));
        }

        importList.add(BREAK_LINE);
        importList.add(new AnotacionesJava(archivo).creatNotaClase().toString());
        importList.add(BREAK_LINE);


        ParameterClassMethod classParameterLogger = ParameterClassMethod.builder()
                .parameterSignatuer("protected static final Log logger = LogFactory.getLog(" + nameOfClass + ".class);")
                .build();


        ParameterClassMethod classParametersMapper = ParameterClassMethod.builder()
                .annotations(List.of("@Autowired"))
                .modifier(Modifier.Private)
                .atributoClass(stringEnsamble(List.of(entidad.getNombreClase(), "Mapper", SPACE)))
                .atributoName(stringEnsamble(List.of(entidad.getNombreClase().toLowerCase(), "Mapper")))
                .build();


        ParameterClassMethod classParametersRepositories = ParameterClassMethod.builder()
                .annotations(List.of("@Autowired"))
                .modifier(Modifier.Private)
                .atributoClass(repositorieName)
                .atributoName(repositorieNameOjecte)
                .build();

        ClassDesign classTemplate = ClassDesign.builder()
                .packagePaht(archivo.getPackageNames())
                .packageName("serviceImplement")
                .imports(importList)
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

        main.append(classTemplate.toString());
        main.append(DOUBLEBREAK_LINE);
        main.append(AnotacionesJava.apacheSoftwareLicensed() + BREAK_LINE);

        this.createFileClass(nameOfClass, "serviceImplement", main, creadors, archivo.getProyectoName());
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb, Creador creador, String proyectoName) throws InterruptedException {
        String barra = java.nio.file.FileSystems.getDefault().getSeparator();
        String singleString = sb.toString();
        String direction = stringEnsamble(List.of(creador.getDireccionDeCarpeta(), proyectoName, barra
                , "src", barra, "main", barra, "java", barra, creador.getCom()
                , barra, creador.getPackageNames1(), barra, creador.getArtifact()
                , barra, entidad_paquete));

        creador.crearArchivo(direction, singleString, entidad_getNombreClase + ".java");
    }


    private StringBuffer metods(ArchivoBaseDatosPojo archivo, EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        List<StringBuffer> metodosStringBuffer = new ArrayList<>();
        StringBuffer sb = new StringBuffer(BREAK_LINE);

        if (archivo.getMethodManager().isMethodgetAll())
            metodosStringBuffer.add(metodgetAll(entidad, repositorieNameOjecte, entidadNombre));

        if (archivo.getMethodManager().isMetohdSave())
            metodosStringBuffer.add(this.metodSave(entidad, repositorieNameOjecte, entidadNombre));

        if (archivo.getMethodManager().isMethodDelete())
            metodosStringBuffer.add(this.metodDelete(entidad, repositorieNameOjecte, entidadNombre));


        if (archivo.getMethodManager().isMethodUpdate())
            metodosStringBuffer.add(this.metodUpdate(entidad, repositorieNameOjecte, entidadNombre));


        if (archivo.getMethodManager().isMethodfindById())
            metodosStringBuffer.add(this.metodfindById(entidad, repositorieNameOjecte, entidadNombre));


        if (archivo.getMethodManager().isMethodsaveOrUpdate())
            metodosStringBuffer.add(this.metodsaveOrUpdate(entidad, repositorieNameOjecte, entidadNombre));

        if (archivo.getMethodManager().isMethodContaining())
            metodosStringBuffer.add(this.metodContaining(entidad, repositorieNameOjecte, entidadNombre));

        if (archivo.getMethodManager().isMethodContainingRelacion())
            metodosStringBuffer.add(this.ContainingRelacion(entidad, repositorieNameOjecte, entidadNombre));

        if (archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
            metodosStringBuffer.add(this.ContainingRelacionNoBiDirectional(entidad, repositorieNameOjecte, entidadNombre, archivo.getCapaPojo()));

        metodosStringBuffer.forEach(metodo-> sb.append(metodo.toString()));
        sb.append(BREAK_LINE);
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


    private String metodTrycath(String operacion, String operacionElse) {
        StringBuffer sb2 = new StringBuffer("\r\n");
        sb2.append("try {" + "\r\n");
        sb2.append(operacion);
        sb2.append("} catch (DataAccessException e) {" + "\r\n");
        sb2.append("logger.error(\" ERROR : \" + e);" + "\r\n");
        sb2.append(operacionElse);
        sb2.append("}\r\n");
        return sb2.toString();
    }


    private StringBuffer crearMetodoloop(EntityPojo entidad, String repositorieNameOjecte) {

        StringBuffer sbp = new StringBuffer("\r\n");
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            int cont = 1;
            if (!atributos.getsId()) {
                String numeraly = String.valueOf(cont);
                
                BodyMethodDesign bodyMethod = BodyMethodDesign.builder().build();
                bodyMethod.addItem(bodyMethod.buildLoggerInfo(stringEnsamble(List.of("Starting get",entidad.getNombreClase()))));
                bodyMethod.addItem(bodyMethod.createNewObject(entidad.getNombreClase()));
                bodyMethod.addItem(stringEnsamble(List.of(
                        bodyMethod.createOptionalEqual(entidad.getNombreClase(),"fileOptional", numeraly),
                        bodyMethod.callRepository(repositorieNameOjecte, "findBy",atributos.getAtributoName())
                )));
                String operacionu = stringEnsamble(List.of(entidad.getNombreClase().toLowerCase(), "Entity = fileOptional", numeraly, ".get();"));
                bodyMethod.addItem(bodyMethod.createIfBlock(stringEnsamble(List.of("fileOptional", numeraly)), ".isPresent()",
                        bodyMethod.metodTrycath(operacionu, "e.printStackTrace();", "DataAccessException")));
                bodyMethod.addItem(
                        stringEnsamble(
                                List.of("return", TAB, mapperServiceNombreClase, ".entityToPojo", "(", entidad.getNombreClase().toLowerCase(), "Entity", ");"))
                );

                sbp.append(MethodDesign.builder()
                        .annotation(List.of("@Override")).modifiers(Modifier.Public).returnsType(RetunsType.none).returnsClass(returnObjectClass)
                        .methodName(stringEnsamble(List.of("findBy",capitalizeOrUncapitalisedFirstLetter(atributos.getAtributoName(), 'u'))))
                        .parameter(List.of(ParameterClassMethod.builder()
                                .atributoClass(atributos.getTipoDato()).atributoName(atributos.getAtributoName()).build()))
                        .curlyBraces(true).methodBody(bodyMethod.toString()).build().toString());
                cont += 1;
            }
        }
        return sbp;
    }


    private StringBuffer metodgetAll(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuffer ty = new StringBuffer(DOUBLEBREAK_LINE);
        String getNombreClase = entidad.getNombreClase();
        ty.append(stringEnsamble("@Override")).append(BREAK_LINE);
        ty.append(stringEnsamble("public List<"));
        ty.append(stringEnsamble(returnObjectClass));
        ty.append(stringEnsamble("> getAll", getNombreClase, "(){")).append(BREAK_LINE);
        ty.append(stringEnsamble("logger.info(\"execute", "> getAll", getNombreClase, "Get allProyect\");")).append(BREAK_LINE);
        ty.append(stringEnsamble( "List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();")).append(BREAK_LINE);
        ty.append(stringEnsamble(repositorieNameOjecte, ".findAll().forEach(", getNombreClase.toLowerCase(), " -> lista", getNombreClase));
        ty.append(stringEnsamble(".add(", mapperServiceNombreClase, ".entityToPojo", "(", getNombreClase.toLowerCase(), ")));")).append(BREAK_LINE);
        ty.append(stringEnsamble("return lista" + getNombreClase, ";")).append(BREAK_LINE);
        ty.append(stringEnsamble("}")).append(BREAK_LINE);
        return ty;
    }


    private StringBuffer metodSave(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuffer sbg = new StringBuffer("\r\n");
        sbg.append("@Override" + "\r\n");
        sbg.append("public boolean save" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "){" + "\r\n");
        sbg.append("logger.info(\"Save Proyect\");" + "\r\n");
        sbg.append("\r\n");
        String operacion = "" + repositorieNameOjecte + ".save(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n" + "				return true;\r\n";
        String operacionElse = "return false;\r\n";
        sbg.append(this.metodTrycath(operacion, operacionElse));
        sbg.append("}\r\n");
        return sbg;
    }


    private StringBuffer metodDelete(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sbt = new StringBuffer("\r\n");
        if (entidad.getDelete()) {
            sbt.append("@Override" + "\r\n");
            sbt.append("public boolean delete" + entidad.getNombreClase() + "( " + this.idTipoDato(entidad) + " id){" + "\r\n");
            sbt.append("logger.info(\"Delete Proyect\");" + "\r\n");
            sbt.append("boolean clave = false;\r\n");
            sbt.append("\r\n");
            String operacionA = "" + repositorieNameOjecte + ".deleteById(id);" + "\r\n" + "				clave = true;\r\n";
            String operacionElseA = "clave = false;\r\n";
            sbt.append(this.metodTrycath(operacionA, operacionElseA));
            sbt.append("		return clave;\r\n");

            sbt.append("	}\r\n");
        }
        return sbt;
    }


    private StringBuffer metodUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sf = new StringBuffer(BREAK_LINE);
        sf.append(BREAK_LINE);
        sf.append(stringEnsamble(List.of("@Override", BREAK_LINE)));
        sf.append(stringEnsamble(List.of("public boolean update", entidad.getNombreClase(), "(" + entidad.getNombreClase(), TAB, entidad.getNombreClase().toLowerCase(), " ){", BREAK_LINE)));
        sf.append(stringEnsamble(List.of( "logger.info(\"Update ENTITY\");", BREAK_LINE)));
        sf.append(stringEnsamble(List.of("boolean clave = false;", BREAK_LINE)));

        String atributoName = atributoName(entidad.getAtributos());

        sf.append(stringEnsamble(List.of( entidad.getNombreClase(), " empre = ", repositorieNameOjecte)));
        sf.append(stringEnsamble(List.of(".findById(", entidad.getNombreClase().toLowerCase(), ".get", atributoName,"()",").get();", BREAK_LINE)));
        sf.append(stringEnsamble(List.of( "empre = ", entidad.getNombreClase().toLowerCase(), ";", BREAK_LINE)));
        String operacionc = stringEnsamble(List.of( repositorieNameOjecte, ".save(empre);", BREAK_LINE, DOUBLETAB, DOUBLETAB, DOUBLETAB, "clave = true;", BREAK_LINE));
        String operacionElsec = stringEnsamble(List.of("clave = false;", BREAK_LINE));
        sf.append(this.metodTrycath(operacionc, operacionElsec));
        sf.append(BREAK_LINE);
        sf.append(stringEnsamble(List.of("return clave;", BREAK_LINE)));
        sf.append(stringEnsamble(List.of("}" + BREAK_LINE)));

        return sf;
    }

    private StringBuffer metodfindById(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sf = new StringBuffer(DOUBLEBREAK_LINE);
        sf.append(stringEnsamble(List.of("@Override", BREAK_LINE)));
        sf.append(stringEnsamble(List.of("public", TAB)));
        sf.append(stringEnsamble(List.of(returnObjectClass)));
        sf.append(stringEnsamble(List.of(" findById( ", this.idTipoDato(entidad), " id){", BREAK_LINE)));
        sf.append(stringEnsamble(List.of( "return  ", mapperServiceNombreClase, ".entityToPojo", "(", repositorieNameOjecte, ".findById(id).get());", BREAK_LINE)));
        sf.append(stringEnsamble(List.of("}", BREAK_LINE)));
        return sf;
    }

    private String atributoName(List<AttributePojo> atributoPojos) {
        String atributoName = "";
        for (AttributePojo atributo : atributoPojos) {
            if (atributo.getsId()) {
                atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
            }
        }
        return atributoName;
    }


    private StringBuffer metodsaveOrUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sf = new StringBuffer(DOUBLEBREAK_LINE);

        sf.append("@Override" + BREAK_LINE);
        sf.append("public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
        sf.append("logger.info(\"Update Proyect\");" + "\r\n");
        sf.append("boolean clave = false;\r\n");
        String numeraly = String.valueOf(2);
        String atributoName = atributoName(entidad.getAtributos());

        sf.append(stringEnsamble(List.of(TAB, "Optional<", entidad.getNombreClase(), "> fileOptional", numeraly, " = ", repositorieNameOjecte
                , ".findById(", entidad.getNombreClase().toLowerCase(), ".get", atributoName, "());", BREAK_LINE)));

        sf.append("if (fileOptional" + numeraly + ".isPresent()) { " + "\r\n");
        sf.append("clave = this.update" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
        sf.append("logger.info(\" is update\");" + "\r\n");
        sf.append("} else {" + "\r\n");
        sf.append("clave = this.save" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
        sf.append("logger.info(\" is save\");" + "\r\n");
        sf.append("}\r\n");
        sf.append("return clave;\r\n");
        sf.append("}" + "\r\n");
        return sf;
    }


    private StringBuffer ContainingRelacion(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sbx = new StringBuffer();
        logger.info("  " + entidad.getNombreClase());
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (relacion.getBidireccional()) {
                if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                    this.Relacionx(entidad, entidadNombre, relacion);
                }
            }
        }
        return sbx;
    }

    private StringBuffer Relacionx(EntityPojo entidad, String entidadNombre, RelationshipPojo relacion) {
        StringBuffer sbw = new StringBuffer(DOUBLEBREAK_LINE);
        String getNombreClase = entidadNombre;
        String getNombre = entidad.getNombreClase().toLowerCase();
        logger.info("  " + entidad.getNombreClase());
        sbw.append(stringEnsamble(List.of("@Override", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of("public List<")));
        sbw.append(stringEnsamble(List.of(returnObjectClass)));
        sbw.append(stringEnsamble(List.of("> findBy", relacion.getNameClassRelacion(), "Containing(", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "){", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of("logger.info(\"EXECUTE ", relacion.getNameClassRelacion(), " Containing \");", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of("List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of("for (", returnObjectClass, TAB, getNombre, " : this.getAll", getNombreClase, "()) {", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of( "for (", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "x : ", getNombre, ".get", relacion.getNameRelacion(), "()) { ", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of("if(", getNombreClase, ".get", relacion.getNameRelacion(), "().contains(", relacion.getNameRelacion(), ".get", relacion.getNameRelacion(), "())) {	", BREAK_LINE)));
        sbw.append("lista" + getNombreClase + ".add(" + getNombre + "x);	" + "\r\n");
        sbw.append("}" + "\r\n");
        sbw.append("}" + "\r\n");
        sbw.append("}" + "\r\n");
        sbw.append("return lista" + getNombreClase + ";	" + "\r\n");
        sbw.append("\r\n");
        sbw.append("}" + "\r\n");
        return sbw;
    }




    private StringBuffer ContainingRelacionNoBiDirectional(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre, LayerPojo layerPojo) {
        StringBuffer sv = new StringBuffer();
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sv.append(BREAK_LINE);
                sv.append(stringEnsamble(List.of( "@Override", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "public List<")));
                sv.append(stringEnsamble(List.of(returnObjectClass)));
                sv.append(stringEnsamble(List.of("> findByRelacion", relacion.getNameClassRelacion(), "(" + relacion.getNameClassRelacion(), TAB, relacion.getNameClassRelacion().toLowerCase(), "){", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "logger.info(\" Execute ", relacion.getNameClassRelacion(), " findByRelacion\" );",BREAK_LINE )));
                sv.append(stringEnsamble(List.of( "List<", returnObjectClass, "> lista", entidad.getNombreClase(), " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "for (" + returnObjectClass, TAB, entidad.getNombreClase().toLowerCase(), " : this.getAll", entidad.getNombreClase(), "()) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "if(", entidad.getNombreClase().toLowerCase(), ".get", relacion.getNameRelacion(), "().equals", "(", relacion.getNameClassRelacion().toLowerCase(), ")){", BREAK_LINE)));

                sv.append(stringEnsamble(List.of( "lista" + entidad.getNombreClase() + ".add(" + entidad.getNombreClase().toLowerCase() + ");", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "}", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "}", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "return lista" + entidad.getNombreClase() + ";", BREAK_LINE)));
                sv.append("}\r\n");

            }

            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                String getNombreClase = entidad.getNombreClase();
                String getNombre = entidad.getNombreClase().toLowerCase();

                sv.append(DOUBLEBREAK_LINE);
                sv.append(stringEnsamble(List.of("@Override", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "public List<")));
                sv.append(stringEnsamble(List.of(returnObjectClass)));
                sv.append(stringEnsamble(List.of("> findBy", relacion.getNameClassRelacion(), "Containing(", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "){", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "logger.info(\" Execute ", relacion.getNameClassRelacion(), " Containing \");",BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sv.append(stringEnsamble(List.of("for (", returnObjectClass, TAB, getNombre, " : this.getAll", getNombreClase, "()) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of("for (", relacion.getNameClassRelacion(), layerPojo.getModelM(), TAB, relacion.getNameRelacion(), "x : ")));
                sv.append(stringEnsamble(List.of(getNombre, ".get", relacion.getNameRelacion(), "()) { ", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "if(", relacion.getNameRelacion(), "x.equals", "(", relacion.getNameRelacion(), ")) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of( "lista", getNombreClase, ".add(", getNombre, ");	", BREAK_LINE)));
                sv.append("}\r\n");
                sv.append("}\r\n");
                sv.append("}\r\n");
                sv.append(stringEnsamble(List.of( "return lista", getNombreClase, ";	", DOUBLEBREAK_LINE)));
                sv.append("}\r\n");
            }
        }
        return sv;
    }


    private StringBuffer metodContaining(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuffer sbx = new StringBuffer();
        for (AttributePojo atributo : entidad.getAtributos()) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);

            if (!atributo.getsId()) {
                sbx.append(DOUBLEBREAK_LINE);
                sbx.append(stringEnsamble(List.of("@Override", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of("public List<")));
                sbx.append(stringEnsamble(List.of(returnObjectClass)));
                sbx.append(stringEnsamble(List.of("> findBy", atributoName, "Containing(", atributo.getTipoDato(), TAB, atributoName.toLowerCase(), "){", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of("logger.info(\"Execute ", atributoName, " Containing\" );", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of("List<", returnObjectClass, "> lista", entidadNombre, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of( "List<", entidad.getNombreClase(), "> listaS", entidadNombre, " = ")));
                sbx.append(stringEnsamble(List.of(repositorieNameOjecte, ".findBy", atributoName, "Containing(", atributoName.toLowerCase(), ");", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of( "listaS" + entidadNombre + ".forEach(", entidadNombre.toLowerCase(), "x", " -> lista", entidadNombre)));
                sbx.append(stringEnsamble(List.of(".add(", mapperServiceNombreClase, ".entityToPojo", "(", entidadNombre.toLowerCase(), "x", ")));", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of("return lista", entidadNombre, ";", BREAK_LINE)));
                sbx.append("}\r\n");
            }
        }
        return sbx;
    }


}


