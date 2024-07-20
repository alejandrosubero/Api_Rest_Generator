package com.generator.model.tempate.service;


import com.generator.core.build.models.ModelOup;
import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.pojos.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;
import java.util.Set;


public class TemplateService implements IModelBuilder, ITemplateService {


    protected static final Log logger = LogFactory.getLog(TemplateService.class);


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

