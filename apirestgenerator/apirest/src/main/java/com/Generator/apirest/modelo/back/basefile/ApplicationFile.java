package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFile implements IImportModel {

    protected static final Log logger = LogFactory.getLog(ApplicationFile.class);


    public void createApplication(ArchivoBaseDatosPojo archivo, Creador creador) {

        try {
            StringBuffer sb = new StringBuffer();
            String proyectoName = archivo.getProyectoName();
            sb.append("package " + creador.getPackageNames() + ";");
            sb.append(DOUBLEBREAK_LINE);

            if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
                sb.append("import " + creador.getPackageNames() + ".serviceImplement.StartServer;");
                sb.append(BREAK_LINE);
                sb.append(SPRING_AUTOWIRED_IMPORT);
            }

            sb.append(importApplication());
            sb.append(DOUBLEBREAK_LINE);
            sb.append(BREAK_LINE);
//			sb.append( BREAK_LINE);
            sb.append("@SpringBootApplication");
            sb.append(BREAK_LINE);
            sb.append("public class " + proyectoName + "Application {");
            sb.append(DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
            sb.append("		protected static final Log logger = LogFactory.getLog(" + proyectoName + "Application.class);");
            sb.append(BREAK_LINE);

            if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
                sb.append("@Autowired");
                sb.append(BREAK_LINE);
                sb.append("private static StartServer startServer;");
                sb.append(BREAK_LINE);
            }

            sb.append("		public static void main(String[] args) {");
            sb.append(DOUBLEBREAK_LINE);

            if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
                sb.append(DOUBLETAB + "logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/swagger-ui.html\");");
                sb.append(BREAK_LINE);
            } else {
                sb.append(DOUBLETAB + "logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext() + "/swagger-ui.html\");");
                sb.append(BREAK_LINE);
            }

            sb.append(DOUBLETAB + "SpringApplication.run(" + proyectoName + "Application.class, args);");
            sb.append(BREAK_LINE);

            if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
                sb.append(DOUBLETAB + "startServer.start();");
                sb.append(BREAK_LINE);
            }

            if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
                sb.append(DOUBLETAB + "logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/swagger-ui.html\");");
                sb.append(BREAK_LINE);
            } else {
                sb.append(DOUBLETAB + "logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext() + "/swagger-ui.html\");");
                sb.append(BREAK_LINE);
            }

            sb.append(DOUBLETAB + "}");
            sb.append(BREAK_LINE);
            sb.append("}");
            sb.append(DOUBLEBREAK_LINE);

            String direccion = path(Lists.newArrayList(
                    creador.getDireccionDeCarpeta() + proyectoName,
                    "src",
                    "main",
                    "java",
                    creador.getCom(),
                    creador.getPackageNames1(),
                    creador.getArtifact()
            ));

            creador.crearArchivo(direccion, sb.toString(), proyectoName + "Application.java");

        } catch (Exception e) {
            logger.error(e);
        }
    }
}
