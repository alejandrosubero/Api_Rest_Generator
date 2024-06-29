package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.files.Creador;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.interfaces.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ServletInitializerFile implements IImportModel {

    protected static final Log logger = LogFactory.getLog(ServletInitializerFile.class);

    public void createServletInitializer(ArchivoBaseDatosPojo archivo, Creador creador) {
        try {
            String claseName = archivo.getProyectoName() + "Application";
            StringBuffer as = new StringBuffer();

            as.append("package " + creador.getPackageNames() + ";" + DOUBLEBREAK_LINE);
//			as.append(BREAK_LINE);
            as.append(importServletInitializer(archivo.getCapaPojo().getCreateCapaJavaBase7()));
            as.append("public class ServletInitializer extends SpringBootServletInitializer {" + BREAK_LINE);
            as.append(TAB+"@Override"+BREAK_LINE);
            as.append(TAB+"protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {"+BREAK_LINE);
            as.append(DOUBLETAB+"return application.sources(" + claseName + ".class);\r\n" + "	}" + BREAK_LINE + "}"+DOUBLEBREAK_LINE);

            String direccion = path(Lists.newArrayList(
                    creador.getDireccionDeCarpeta() + archivo.getProyectoName(),
                    "src",
                    "main",
                    "java",
                    creador.getCom(),
                    creador.getPackageNames1(),
                    creador.getArtifact()
            ));

            creador.crearArchivo(direccion, as.toString(), "ServletInitializer.java");
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
