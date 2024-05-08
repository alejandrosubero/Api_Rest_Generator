package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationControllerFile implements IImportModel {

    protected static final Log logger = LogFactory.getLog(ApplicationControllerFile.class);

    public void createApplicationController(ArchivoBaseDatosPojo archivo, Creador creador) {
        try {

            StringBuilder controller = new StringBuilder();

            controller.append("package " +creador.getPackageNames() + ".controller;"+ BREAK_LINE);
            controller.append(importApplicationController());
            controller.append(BREAK_LINE);
            controller.append("@RestController"+BREAK_LINE);
            controller.append("@CrossOrigin(origins = \"*\")"+BREAK_LINE);
            controller.append("@RequestMapping(\"/\")" + BREAK_LINE);
            controller.append("public class " + archivo.getProyectoName() + "Controller {");
            controller.append(DOUBLEBREAK_LINE);
            controller.append(TAB+"@GetMapping(\"/start\")"+ BREAK_LINE);
            controller.append(TAB+"public String startTest() {" + BREAK_LINE);
            controller.append(DOUBLETAB+"return \" <h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>\"+ " + BREAK_LINE);
            controller.append(DOUBLETAB+"\"<br>\" + \r\n" + BREAK_LINE);
            controller.append(DOUBLETAB+"\"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>\"; " + BREAK_LINE);
            controller.append(TAB+"}"+ BREAK_LINE);
            controller.append(TAB+"}"+ BREAK_LINE);

            String direccion = path(Lists.newArrayList(
                    creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src", "main", "java",
                    creador.getCom(), creador.getPackageNames1(), creador.getArtifact(),"controller"));
            creador.crearArchivo(direccion, controller.toString(), archivo.getProyectoName() + "Controller.java");

        } catch (Exception e) {	logger.error(e); }
    }


}
