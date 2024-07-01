package com.generator.model.pomxml;


import com.generator.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PomxmlFactory {

    protected static final Log logger = LogFactory.getLog(PomxmlFactory.class);

    private PomxmlCreator07 pomxmlCreator07;

    public PomxmlFactory(PomxmlCreator07 pomxmlCreator07) {
        this.pomxmlCreator07 = pomxmlCreator07;
    }

    public PomxmlInterface getPomxml(ArchivoBaseDatosPojo archivo) {

        logger.info("init the pomxml Creator instance");
        if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
            logger.info("init the pomxml instance for java 1.7");
            return this.pomxmlCreator07;
        }
        return null;
    }
}
