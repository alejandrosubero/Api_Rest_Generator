package com.Generator.apirest.modelo.back.javaPlus07;


import com.Generator.apirest.core.Creador;
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

        StringBuilder sbh = new StringBuilder(BREAK_LINE);

        String nameOfClass = entidad.getNombreClase() + "ServiceImplement";
        String repositorieName = entidad.getNombreClase() + "Repository";
        String repositorieNameOjecte = repositorieName.toLowerCase();
        String serviceName = entidad.getNombreClase() + "Service";
        returnObjectClass = archivo.getCapaPojo().getCreateCapaPojoForEntitys() ? stringEnsamble(List.of(entidad.getNombreClase(), archivo.getCapaPojo().getModelM())) : entidad.getNombreClase();
        returnObjectClassPackage = archivo.getCapaPojo().getCreateCapaPojoForEntitys() ? archivo.getCapaPojo().getModelT() : "entitys";
        mapperServiceNombreClase = stringEnsamble(List.of(entidad.getNombreClase().toLowerCase(), "Mapper"));
        mapperNombreClaseService = stringEnsamble(List.of(entidad.getNombreClase(), "Mapper", TAB));

        sbh.append(new AnotacionesJava(archivo).creatNotaClase().toString());
        sbh.append(BREAK_LINE);
        sbh.append(this.createImport(archivo.getPackageNames(), serviceName, repositorieName, entidad));
        sbh.append(this.createTitulo(nameOfClass, serviceName, repositorieName, repositorieNameOjecte));

        if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
            sbh.append(this.createAutowiredMapper(entidad));
        }

        if (archivo.getMethodManager().isMethodFindByOrLoop()) {
            sbh.append(this.crearMetodoloop(entidad, repositorieNameOjecte));
        }

        sbh.append(this.metods(archivo, entidad, repositorieNameOjecte, entidad.getNombreClase()));
        sbh.append(AnotacionesJava.apacheSoftwareLicensed() + BREAK_LINE);

        this.createFileClass(nameOfClass, "serviceImplement", sbh, creadors, archivo.getProyectoName());
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb, Creador creador, String proyectoName) throws InterruptedException {
        String barra = java.nio.file.FileSystems.getDefault().getSeparator();
        sb.append(stringEnsamble(List.of("}", BREAK_LINE)));
        String singleString = sb.toString();
        String direction = stringEnsamble(List.of(creador.getDireccionDeCarpeta(), proyectoName, barra
                , "src", barra, "main", barra, "java", barra, creador.getCom()
                , barra, creador.getPackageNames1(), barra, creador.getArtifact()
                , barra, entidad_paquete));

        creador.crearArchivo(direction, singleString, entidad_getNombreClase + ".java");
    }


    private StringBuilder metods(ArchivoBaseDatosPojo archivo, EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder sb = new StringBuilder(DOUBLEBREAK_LINE);

        if (archivo.getMethodManager().isMethodgetAll())
            sb.append(metodgetAll(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMetohdSave())
            sb.append(this.metodSave(entidad, repositorieNameOjecte, entidadNombre));

        sb.append(BREAK_LINE);
        if (archivo.getMethodManager().isMethodDelete())
            sb.append(this.metodDelete(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodUpdate())
		sb.append(this.metodUpdate(entidad, repositorieNameOjecte, entidadNombre));
            sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodfindById())
            sb.append(this.metodfindById(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodsaveOrUpdate())
            sb.append(this.metodsaveOrUpdate(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodContaining())
            sb.append(this.metodContaining(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodContainingRelacion())
            sb.append(this.ContainingRelacion(entidad, repositorieNameOjecte, entidadNombre));
        sb.append(BREAK_LINE);

        if (archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
            sb.append(this.ContainingRelacionNoBiDirectional(entidad, repositorieNameOjecte, entidadNombre, archivo.getCapaPojo()));
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
        StringBuilder sb2 = new StringBuilder("\r\n");
        sb2.append("				try {" + "\r\n");
        sb2.append(operacion);
        sb2.append("				} catch (DataAccessException e) {" + "\r\n");
        sb2.append("				logger.error(\" ERROR : \" + e);" + "\r\n");
        sb2.append(operacionElse);
        sb2.append("				}\r\n");
        return sb2.toString();
    }


    public StringBuilder createImport(String packageNames, String serviceName, String repositorieName, EntityPojo entidad) {
        StringBuilder sb = new StringBuilder("\r\n");

        sb.append("package " + packageNames + ".serviceImplement ;\r\n");
        sb.append("\r\n");
        sb.append("import " + packageNames + ".service." + serviceName + ";\r\n");
        sb.append("import " + packageNames + ".repository." + repositorieName + ";\r\n");

        sb.append("import java.util.Optional;" + "\r\n");
        sb.append("import java.util.ArrayList;" + "\r\n");
        sb.append("import java.util.List;" + "\r\n");
        sb.append("import java.util.Date;" + "\r\n");
        sb.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
        sb.append("import org.springframework.dao.DataAccessException;" + "\r\n");
        sb.append("import org.springframework.stereotype.Service;" + "\r\n");

        sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";" + "\r\n");
        sb.append("import " + packageNames + "." + returnObjectClassPackage + "." + returnObjectClass + ";" + BREAK_LINE);

        if (mapperServiceNombreClase != null && !mapperServiceNombreClase.equals("")) {
            sb.append(stringEnsamble(List.of("import ", packageNames, ".mapper", ".", mapperNombreClaseService, ";", BREAK_LINE)));
            sb.append(BREAK_LINE);
        }

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
            sb.append(BREAK_LINE);
            sb.append(stringEnsamble(List.of("import ", packageNames,".", this.layerPojo.getModelT().trim(),".", relacion.getNameClassRelacion(), this.layerPojo.getModelM(),";",BREAK_LINE)));
            sb.append(DOUBLEBREAK_LINE);}

        return sb;
    }


    private StringBuilder createAutowiredMapper(EntityPojo entidad) {
        StringBuilder sb1 = new StringBuilder(BREAK_LINE);
        sb1.append(DOUBLETAB + "@Autowired");
        sb1.append(BREAK_LINE);
        sb1.append(stringEnsamble(List.of(DOUBLETAB, "private", TAB, mapperNombreClaseService, mapperServiceNombreClase, ";")));
        sb1.append(DOUBLEBREAK_LINE);
        return sb1;
    }

    private StringBuilder createTitulo(String nameOfClass, String serviceName, String repositorieName, String repositorieNameOjecte) {

        StringBuilder sb1 = new StringBuilder("\r\n");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("@Service" + "\r\n");
        sb1.append("public class " + nameOfClass + " implements " + serviceName + " {" + "\r\n");
        sb1.append("\r\n");
        sb1.append("protected static final Log logger = LogFactory.getLog(" + nameOfClass + ".class);");
        sb1.append("\r\n");
        sb1.append("@Autowired" + "\r\n");
        sb1.append("private " + repositorieName + " " + repositorieNameOjecte + ";" + "\r\n");

        return sb1;
    }


    private StringBuilder crearMetodoloop(EntityPojo entidad, String repositorieNameOjecte) {

        StringBuilder sbp = new StringBuilder("\r\n");
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            int cont = 1;
            if (!atributos.getsId()) {
                String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
                String numeraly = String.valueOf(cont);
                String operacionu = stringEnsamble(List.of(DOUBLETAB, TAB, entidad.getNombreClase().toLowerCase(), "Entity = fileOptional", numeraly, ".get();" + BREAK_LINE));

                sbp.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
                sbp.append(stringEnsamble(List.of(DOUBLETAB, "public", TAB)));
                sbp.append(stringEnsamble(List.of(returnObjectClass)));
                sbp.append(stringEnsamble(List.of(TAB, "findBy", atributoName, "(", atributos.getTipoDato(), TAB, atributos.getAtributoName(), "){", DOUBLEBREAK_LINE)));

                sbp.append(stringEnsamble(List.of(DOUBLETAB, "logger.info(\"Starting get", entidad.getNombreClase(), "\");", BREAK_LINE)));
                sbp.append(stringEnsamble(List.of(DOUBLETAB, entidad.getNombreClase(), TAB, entidad.getNombreClase().toLowerCase(), "Entity = new ", entidad.getNombreClase(), "();" + BREAK_LINE)));
                sbp.append(stringEnsamble(List.of(DOUBLETAB, "Optional<", entidad.getNombreClase(), "> fileOptional", numeraly, " = ", repositorieNameOjecte, ".findBy", atributoName, "(", atributos.getAtributoName(), ");", BREAK_LINE)));
                sbp.append(BREAK_LINE);
                sbp.append(stringEnsamble(List.of(DOUBLETAB, "if (fileOptional", numeraly, ".isPresent()) { ", BREAK_LINE)));
                sbp.append(this.metodTrycath(operacionu, BREAK_LINE));
                sbp.append(stringEnsamble(List.of(DOUBLETAB, "}" + BREAK_LINE)));
                sbp.append(stringEnsamble(List.of(DOUBLETAB, "return", TAB, mapperServiceNombreClase, ".entityToPojo", "(", entidad.getNombreClase().toLowerCase(), "Entity", ");")));

                sbp.append(stringEnsamble(List.of(TAB, "}", BREAK_LINE)));
                cont += 1;
            }
        }
        return sbp;
    }


    private StringBuilder metodgetAll(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder ty = new StringBuilder(DOUBLEBREAK_LINE);
        String getNombreClase = entidad.getNombreClase();
        ty.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
        ty.append(stringEnsamble(List.of(DOUBLETAB, "public List<")));
        ty.append(stringEnsamble(List.of(returnObjectClass)));
        ty.append(stringEnsamble(List.of("> getAll", getNombreClase, "(){")));
        ty.append(BREAK_LINE);
        ty.append(stringEnsamble(List.of("		logger.info(\"execute", "> getAll", getNombreClase, "Get allProyect\");", BREAK_LINE)));
        ty.append(stringEnsamble(List.of(DOUBLETAB, TAB, "List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
        ty.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, repositorieNameOjecte, ".findAll().forEach(", getNombreClase.toLowerCase(), " -> lista", getNombreClase)));
        ty.append(stringEnsamble(List.of(".add(", mapperServiceNombreClase, ".entityToPojo", "(", getNombreClase.toLowerCase(), ")));", BREAK_LINE)));
        ty.append(stringEnsamble(List.of(DOUBLETAB, TAB, "return lista" + getNombreClase, ";", BREAK_LINE)));
        ty.append(stringEnsamble(List.of(TAB, "}", BREAK_LINE)));
        return ty;
    }


    private StringBuilder metodSave(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder sbg = new StringBuilder("\r\n");
        sbg.append("		@Override" + "\r\n");
        sbg.append("		public boolean save" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "){" + "\r\n");
        sbg.append("		logger.info(\"Save Proyect\");" + "\r\n");
        sbg.append("\r\n");
        String operacion = "				" + repositorieNameOjecte + ".save(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n" + "				return true;\r\n";
        String operacionElse = "				return false;\r\n";
        sbg.append(this.metodTrycath(operacion, operacionElse));
        sbg.append("		}\r\n");
        return sbg;
    }


    private StringBuilder metodDelete(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sbt = new StringBuilder("\r\n");
        if (entidad.getDelete()) {
            sbt.append("		@Override" + "\r\n");
            sbt.append("		public boolean delete" + entidad.getNombreClase() + "( " + this.idTipoDato(entidad) + " id){" + "\r\n");


            sbt.append("		logger.info(\"Delete Proyect\");" + "\r\n");
            sbt.append("		boolean clave = false;\r\n");
            sbt.append("\r\n");
            String operacionA = "				" + repositorieNameOjecte + ".deleteById(id);" + "\r\n" + "				clave = true;\r\n";
            String operacionElseA = "				clave = false;\r\n";
            sbt.append(this.metodTrycath(operacionA, operacionElseA));
            sbt.append("		return clave;\r\n");

            sbt.append("	}\r\n");
        }
        return sbt;
    }


    private StringBuilder metodUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sf = new StringBuilder(BREAK_LINE);
        sf.append(BREAK_LINE);
        sf.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, "public boolean update", entidad.getNombreClase(), "(" + entidad.getNombreClase(), TAB, entidad.getNombreClase().toLowerCase(), " ){", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, TAB, "logger.info(\"Update ENTITY\");", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, TAB, "boolean clave = false;", BREAK_LINE)));

        String atributoName = atributoName(entidad.getAtributos());

        sf.append(stringEnsamble(List.of(DOUBLETAB, entidad.getNombreClase(), " empre = ", repositorieNameOjecte)));
        sf.append(stringEnsamble(List.of(".findById(", entidad.getNombreClase().toLowerCase(), ".get", atributoName,"()",").get();", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, TAB, "empre = ", entidad.getNombreClase().toLowerCase(), ";", BREAK_LINE)));
        String operacionc = stringEnsamble(List.of(DOUBLETAB, TAB, repositorieNameOjecte, ".save(empre);", BREAK_LINE, DOUBLETAB, DOUBLETAB, DOUBLETAB, "clave = true;", BREAK_LINE));
        String operacionElsec = stringEnsamble(List.of(DOUBLETAB, TAB, "clave = false;", BREAK_LINE));
        sf.append(this.metodTrycath(operacionc, operacionElsec));
        sf.append(BREAK_LINE);
        sf.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, TAB, "return clave;", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(TAB, "}" + BREAK_LINE)));
        return sf;
    }

    private StringBuilder metodfindById(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sf = new StringBuilder(DOUBLEBREAK_LINE);
        sf.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, "public", TAB)));
        sf.append(stringEnsamble(List.of(returnObjectClass)));
        sf.append(stringEnsamble(List.of(" findById( ", this.idTipoDato(entidad), " id){", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "return  ", mapperServiceNombreClase, ".entityToPojo", "(", repositorieNameOjecte, ".findById(id).get());", BREAK_LINE)));
        sf.append(stringEnsamble(List.of(DOUBLETAB, "}", BREAK_LINE)));
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


    private StringBuilder metodsaveOrUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sf = new StringBuilder(DOUBLEBREAK_LINE);

        sf.append("		@Override" + BREAK_LINE);
        sf.append("		public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
        sf.append("			logger.info(\"Update Proyect\");" + "\r\n");
        sf.append("			boolean clave = false;\r\n");
        String numeraly = String.valueOf(2);
        String atributoName = atributoName(entidad.getAtributos());

        sf.append(stringEnsamble(List.of(TAB, "Optional<", entidad.getNombreClase(), "> fileOptional", numeraly, " = ", repositorieNameOjecte
                , ".findById(", entidad.getNombreClase().toLowerCase(), ".get", atributoName, "());", BREAK_LINE)));

        sf.append("			if (fileOptional" + numeraly + ".isPresent()) { " + "\r\n");
        sf.append("				clave = this.update" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
        sf.append("				logger.info(\" is update\");" + "\r\n");
        sf.append("			} else {" + "\r\n");
        sf.append("					clave = this.save" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
        sf.append("					logger.info(\" is save\");" + "\r\n");
        sf.append(" 				}\r\n");
        sf.append(" 		return clave;\r\n");
        sf.append("		}" + "\r\n");
        return sf;
    }


    private StringBuilder ContainingRelacion(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sbx = new StringBuilder();
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

    private StringBuilder Relacionx(EntityPojo entidad, String entidadNombre, RelationshipPojo relacion) {
        StringBuilder sbw = new StringBuilder(DOUBLEBREAK_LINE);
        String getNombreClase = entidadNombre;
        String getNombre = entidad.getNombreClase().toLowerCase();
        logger.info("  " + entidad.getNombreClase());
        sbw.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, "public List<")));
        sbw.append(stringEnsamble(List.of(returnObjectClass)));
        sbw.append(stringEnsamble(List.of("> findBy", relacion.getNameClassRelacion(), "Containing(", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "){", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "logger.info(\"EXECUTE ", relacion.getNameClassRelacion(), " Containing \");", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, TAB, "for (", returnObjectClass, TAB, getNombre, " : this.getAll", getNombreClase, "()) {", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "for (", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "x : ", getNombre, ".get", relacion.getNameRelacion(), "()) { ", BREAK_LINE)));
        sbw.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "if(", getNombreClase, ".get", relacion.getNameRelacion(), "().contains(", relacion.getNameRelacion(), ".get", relacion.getNameRelacion(), "())) {	", BREAK_LINE)));
        sbw.append("					lista" + getNombreClase + ".add(" + getNombre + "x);	" + "\r\n");
        sbw.append("				}" + "\r\n");
        sbw.append("	  	 	}" + "\r\n");
        sbw.append("		}" + "\r\n");
        sbw.append("					return lista" + getNombreClase + ";	" + "\r\n");
        sbw.append("\r\n");
        sbw.append("	}" + "\r\n");
        return sbw;
    }




    private StringBuilder ContainingRelacionNoBiDirectional(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre, LayerPojo layerPojo) {
        StringBuilder sv = new StringBuilder();
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sv.append(BREAK_LINE);
                sv.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, "public List<")));
                sv.append(stringEnsamble(List.of(returnObjectClass)));
                sv.append(stringEnsamble(List.of("> findByRelacion", relacion.getNameClassRelacion(), "(" + relacion.getNameClassRelacion(), TAB, relacion.getNameClassRelacion().toLowerCase(), "){", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, "logger.info(\" Execute ", relacion.getNameClassRelacion(), " findByRelacion\" );")));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "List<", returnObjectClass, "> lista", entidad.getNombreClase(), " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "for (" + returnObjectClass, TAB, entidad.getNombreClase().toLowerCase(), " : this.getAll", entidad.getNombreClase(), "()) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(TAB, "if(", entidad.getNombreClase().toLowerCase(), ".get", relacion.getNameRelacion(), "().equals", "(", relacion.getNameClassRelacion().toLowerCase(), ")){", BREAK_LINE)));

                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "						lista" + entidad.getNombreClase() + ".add(" + entidad.getNombreClase().toLowerCase() + ");", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "					}", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "				}", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "				return lista" + entidad.getNombreClase() + ";", BREAK_LINE)));
                sv.append("			}\r\n");

            }

            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                String getNombreClase = entidad.getNombreClase();
                String getNombre = entidad.getNombreClase().toLowerCase();

                sv.append(DOUBLEBREAK_LINE);
                sv.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, "public List<")));
                sv.append(stringEnsamble(List.of(returnObjectClass)));
                sv.append(stringEnsamble(List.of("> findBy", relacion.getNameClassRelacion(), "Containing(", relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion(), "){", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, "logger.info(\" Execute ", relacion.getNameClassRelacion(), " Containing \");")));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "List<", returnObjectClass, "> lista", getNombreClase, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "for (", returnObjectClass, TAB, getNombre, " : this.getAll", getNombreClase, "()) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "for (", relacion.getNameClassRelacion(), layerPojo.getModelM(), TAB, relacion.getNameRelacion(), "x : ")));
                sv.append(stringEnsamble(List.of(getNombre, ".get", relacion.getNameRelacion(), "()) { ", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, DOUBLETAB, "if(", relacion.getNameRelacion(), "x.equals", "(", relacion.getNameRelacion(), ")) {", BREAK_LINE)));
                sv.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, DOUBLETAB, "lista", getNombreClase, ".add(", getNombre, ");	", BREAK_LINE)));
                sv.append("				}\r\n");
                sv.append("	  	 	}\r\n");
                sv.append("		}\r\n");
                sv.append(stringEnsamble(List.of(DOUBLETAB, "return lista", getNombreClase, ";	", DOUBLEBREAK_LINE)));
                sv.append("	}\r\n");
            }
        }
        return sv;
    }


    private StringBuilder metodContaining(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sbx = new StringBuilder();
        for (AttributePojo atributo : entidad.getAtributos()) {
            String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);

            if (!atributo.getsId()) {
                sbx.append(DOUBLEBREAK_LINE);
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "@Override", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "public List<")));
                sbx.append(stringEnsamble(List.of(returnObjectClass)));
                sbx.append(stringEnsamble(List.of("> findBy", atributoName, "Containing(", atributo.getTipoDato(), TAB, atributoName.toLowerCase(), "){", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "logger.info(\"Execute ", atributoName, " Containing\" );")));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "List<", returnObjectClass, "> lista", entidadNombre, " = new ArrayList<", returnObjectClass, ">();", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "List<", entidad.getNombreClase(), "> listaS", entidadNombre, " = ")));
                sbx.append(stringEnsamble(List.of(repositorieNameOjecte, ".findBy", atributoName, "Containing(", atributoName.toLowerCase(), ");", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, "listaS" + entidadNombre + ".forEach(", entidadNombre.toLowerCase(), "x", " -> lista", entidadNombre)));
                sbx.append(stringEnsamble(List.of(".add(", mapperServiceNombreClase, ".entityToPojo", "(", entidadNombre.toLowerCase(), "x", ")));", BREAK_LINE)));
                sbx.append(stringEnsamble(List.of(DOUBLETAB, DOUBLETAB, "return lista", entidadNombre, ";", BREAK_LINE)));
                sbx.append("		}\r\n");
            }
        }
        return sbx;
    }


}


