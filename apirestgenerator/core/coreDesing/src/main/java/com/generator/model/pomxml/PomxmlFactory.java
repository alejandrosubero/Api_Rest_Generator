package com.generator.model.pomxml;

import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class PomxmlFactory {

    protected static final Log logger = LogFactory.getLog(PomxmlFactory.class);

    private PomxmlCreator pomxmlCreator;
    private PomxmlCreator07 pomxmlCreator07;

    public PomxmlFactory(PomxmlCreator pomxmlCreator, PomxmlCreator07 pomxmlCreator07) {
        this.pomxmlCreator = pomxmlCreator;
        this.pomxmlCreator07 = pomxmlCreator07;
    }

    public PomxmlInterface getPomxml(ArchivoBaseDatosPojo archivo){

        logger.info("init the pomxml Creator instance");
        if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
            logger.info("init the pomxml instance for java 1.7");
            return this.pomxmlCreator07;
        }else {
            logger.info("init the pomxml");
            return this.pomxmlCreator;
        }
    }
}
