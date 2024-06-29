package com.Generator.apirest.ServiceImpl.layers;


import com.Generator.apirest.files.BaseFiles;
import com.Generator.apirest.files.Creador;
import com.Generator.apirest.modelo.back.pomxml.PomxmlFactory;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("singleton")
@Component
public class CreateBaseFiles {

    private BaseFiles baseFiles;
    private PomxmlFactory pomxmlCreator;

    public CreateBaseFiles(BaseFiles baseFiles, PomxmlFactory pomxmlCreator) {
        this.baseFiles = baseFiles;
        this.pomxmlCreator = pomxmlCreator;
    }
    protected static final Log logger = LogFactory.getLog(CreateBaseFiles.class);

    public void create(ArchivoBaseDatosPojo archivo, Creador creador) {
        logger.info("init base files for project");
        this.baseFiles.iniciarArchivosBase2(archivo, creador, 0);
        this.baseFiles.iniciarArchivosBase2(archivo, creador, 1);
        this.pomxmlCreator.getPomxml(archivo).initPomxml(archivo, creador);
    }

}



