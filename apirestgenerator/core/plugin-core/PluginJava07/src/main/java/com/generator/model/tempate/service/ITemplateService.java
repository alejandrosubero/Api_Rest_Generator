package com.generator.model.tempate.service;



import com.generator.plugin.build.models.ModelOup;
import com.generator.plugin.format.formatter.Formatter;
import com.generator.plugin.pojos.*;

import java.util.List;

public interface ITemplateService {


    default ModelOup template(EntityPojo entidad, ArchivoBaseDatosPojo archivo, String javaDirectory) throws InterruptedException {

        AnotacionesJava anotacionesJava = new AnotacionesJava();
        StringBuffer sb2 = new StringBuffer("\r\n");
        String cadenaOriginal = "";
        String atributoName = "";
        String datoTipo = "";
        String packageNames = archivo.getPackageNames();

        List<AttributePojo> listAtributos = entidad.getAtributos();
        String nameOfClass = entidad.getNombreClase() + "Service";

        for (AttributePojo atributoID : entidad.getAtributos()) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }

        sb2.append(anotacionesJava.creatNotaClase() + "\r\n");
        sb2.append("package " + packageNames + ".service ;\r\n");
        sb2.append("\r\n");
        sb2.append("import java.util.Date;" + "\r\n");
        sb2.append("\r\n");
        sb2.append("import java.util.ArrayList;");
        sb2.append("\r\n");
        sb2.append("import java.util.List;");
        sb2.append("\r\n");
        sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";");

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
        }

        sb2.append("\r\n");
        sb2.append("public interface " + nameOfClass + "{\r\n ");
        sb2.append("\r\n");


        if (archivo.getMethodManager().isMethodfindById()) {
            sb2.append("public " + entidad.getNombreClase() + " findById" + "(" + datoTipo + " id);" + "\r\n");
        }

        if (archivo.getMethodManager().isMetohdSave()) {
            sb2.append("public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
        }

        if (archivo.getMethodManager().isMethodgetAll()) {
            sb2.append("public List<" + entidad.getNombreClase() + "> getAll" + entidad.getNombreClase() + "();" + "\r\n");
        }

        if (archivo.checkAtributos(entidad)) {
            sb2.append("public List<" + entidad.getNombreClase() + ">  search(String search);" + "\r\n");
        }

        for (AttributePojo atributos : listAtributos) {
            if (!atributos.getsId()) {
                cadenaOriginal = atributos.getAtributoName();
                String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
                String restoDeLaCadena = cadenaOriginal.substring(1);
                atributoName = primeraLetra + restoDeLaCadena;
                if (archivo.getMethodManager().isMethodFindByOrLoop()) {
                    sb2.append("public " + entidad.getNombreClase() + "  findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");" + "\r\n");
                    sb2.append("\r\n");
                }
            }
        }

        for (AttributePojo atributos : listAtributos) {
            if (!atributos.getsId()) {
                cadenaOriginal = atributos.getAtributoName();
                String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
                String restoDeLaCadena = cadenaOriginal.substring(1);
                atributoName = primeraLetra + restoDeLaCadena;
                if (archivo.getMethodManager().isMethodContaining()) {
                    sb2.append("public List<" + entidad.getNombreClase() + ">  findBy" + atributoName + "Containing(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");" + "\r\n");
                }
            }
        }

        if (entidad.getDelete()) {
            sb2.append("public boolean delete" + entidad.getNombreClase() + "(" + datoTipo + " id);" + "\r\n");
        }

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                if (archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional()) {
                    sb2.append("public List<" + entidad.getNombreClase() + ">  findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ");" + "\r\n");
                }
            } else {
                if (archivo.getMethodManager().isMethodContainingRelacion()) {
                    sb2.append("public List<" + entidad.getNombreClase() + ">  findByRelacion" + relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion() + " " + relacion.getNameClassRelacion().toLowerCase() + ");" + "\r\n");
                }
            }
        }

        sb2.append("}" + "\r\n");
        sb2.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");

        return ModelOup.builder()
                .packageNane("service")
                .nameOfClass(nameOfClass)
                .classInString(new Formatter().simpleFormat(sb2.toString()))
                .directoryForJava(javaDirectory)
                .build();
    }

}
