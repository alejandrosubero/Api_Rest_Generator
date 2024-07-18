package com.generator.model.java07;


import com.generator.core.build.ModelOup;
import com.generator.core.build.interfaces.IModelBuilder;
import com.generator.core.pojos.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;
import java.util.Set;


public class CreateService07 implements IModelBuilder, ITemplateService07 {


    protected static final Log logger = LogFactory.getLog(CreateService07.class);


    @Override
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {

        if (baseFilePojo != null && creator != null) {
               try {
                    return getListModel(baseFilePojo, creator);
               } catch (InterruptedException e) {
                   logger.error(" ERROR : " + e);
                   e.printStackTrace();
               }
        }
        return Set.of();
    }


    private Set<ModelOup> getListModel(ArchivoBaseDatosPojo archivo, Creador creador) throws InterruptedException {
        logger.info("start build list of Module");
        Set<ModelOup> response = new HashSet<>();
            if(archivo.getEntidades() != null && archivo.getEntidades().size() > 0){
                    for (EntityPojo entidad : archivo.getEntidades()) {
                        if (entidad.getIsEntity()) {
                            logger.info("start create the module Service 07" + entidad.getNombreClase());
                            response.add(template(entidad, archivo, creador.directionForJava()));
                        }
                    }
            }
        return response;
    }

}

