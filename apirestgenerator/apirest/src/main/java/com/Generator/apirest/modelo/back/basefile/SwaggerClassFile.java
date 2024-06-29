package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.core.files.BaseFiles;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class SwaggerClassFile implements IImportModel {
    protected static final Log logger = LogFactory.getLog(BaseFiles.class);

    //TODO: CONTROLAR EL CREAR ESTA CLASE PARA APLICACIONES CON SPRING BOOT DE 2.9 EN ADELANTE

    public void createSwaggerClass(ArchivoBaseDatosPojo archivo, Creador creador){
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("package " + creador.getPackageNames() + ".configurations;"+BREAK_LINE);
            sb.append(BREAK_LINE);
            sb.append(importSwagger());
            sb.append(DOUBLEBREAK_LINE);
            sb.append("	@Configuration"+BREAK_LINE);
            sb.append("	@EnableSwagger2"+BREAK_LINE);
            sb.append("	public class SwaggerConfig {"+BREAK_LINE);
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+ "@Bean"+BREAK_LINE);
            sb.append(DOUBLETAB+"public Docket api() {"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+"return new Docket(DocumentationType.SWAGGER_2)"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".select()"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".apis(RequestHandlerSelectors.basePackage(\""+ creador.getPackageNames() +".controller\"))"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".build().apiInfo(apiEndPointsInfo());"+BREAK_LINE);
            sb.append(DOUBLETAB+"}"+BREAK_LINE);
            sb.append(DOUBLEBREAK_LINE);
            sb.append(DOUBLETAB+"private ApiInfo apiEndPointsInfo() {"+DOUBLEBREAK_LINE);

            // TODO: hay que hacer que se pueda decidir si se quiere o no colocar
            sb.append(DOUBLETAB+ "Contact contact = new Contact(\"Alejandro\", \"https://github.com/SuberoPrueba\", \"blu@Gmail.com\");" + DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+TAB+"return new ApiInfoBuilder()" + BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".title(\""+archivo.getProyectoName()+"\")"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".description(\"Description: "+archivo.getDescription()+"\")"+BREAK_LINE);

            // TODO: Pensar que se colocarar ak y hacerlo de forma que se selecione por ahora queda comentedo
            // sb.append("		.termsOfServiceUrl(\"https://github.com\")"	+	BREAK_LINE);

            // TODO: hay que hacer que se pueda decidir si se quiere o no colocar
            sb.append(DOUBLETAB+TAB+".contact(contact)"	+ BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".license(\"Apache License Version 2.0\")"	+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".licenseUrl(\"https://www.apache.org/licenses/LICENSE-2.0\")"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".version(\""+archivo.getPrograntVersion()+"\")"+BREAK_LINE);
            sb.append(DOUBLETAB+TAB+".build();"	+BREAK_LINE);
            sb.append(TAB+"}"+BREAK_LINE);
            sb.append(BREAK_LINE);
            sb.append("}"+DOUBLEBREAK_LINE);

            String direccion = path(Lists.newArrayList(
                    creador.getDireccionDeCarpeta() + archivo.getProyectoName(),
                    "src",
                    "main",
                    "java",
                    creador.getCom(),
                    creador.getPackageNames1(),
                    creador.getArtifact(),
                    "configurations"
            ));

            creador.crearArchivo(direccion, sb.toString(), "SwaggerConfig.java");

        } catch (Exception e) {
            logger.error(e);
        }
    }

}
