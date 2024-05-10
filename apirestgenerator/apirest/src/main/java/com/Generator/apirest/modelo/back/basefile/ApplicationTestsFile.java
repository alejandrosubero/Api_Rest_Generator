package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTestsFile implements IImportModel {

    protected static final Log logger = LogFactory.getLog(ApplicationTestsFile.class);

    public void createApplicationTests(ArchivoBaseDatosPojo archivo, Creador creador) {
        try {
            String proyectoName = archivo.getProyectoName();
            String claseName = proyectoName + "ApplicationTests";
            StringBuilder sb1 = new StringBuilder();
            sb1.append("package " + creador.getPackageNames() + ";" + BREAK_LINE );
            sb1.append(importApplicationTests(archivo.getCapaPojo().getCreateCapaJavaBase7()));
            sb1.append("@SpringBootTest\r\n" + "class " + claseName + " {\r\n" + BREAK_LINE + "	@Test"+BREAK_LINE);
            sb1.append(DOUBLETAB+"void contextLoads() {"+BREAK_LINE + "	}" + BREAK_LINE + "}" + BREAK_LINE);

            String direccion = path(Lists.newArrayList(
                    creador.getDireccionDeCarpeta() + proyectoName, "src", "test", "java", creador.getCom(),
                    creador.getPackageNames1(),creador.getArtifact()	));

            creador.crearArchivo(direccion, sb1.toString(), proyectoName + "ApplicationTests.java");
        } catch (Exception e) {	logger.error(e); }
    }
}
