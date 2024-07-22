package com.generator.model.tempate.implement;


import com.generator.core.build.models.ModelOup;
import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.EntityPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;
import java.util.Set;

public class TemplateServiceImplement implements IModelBuilder, ITemplateImplementService {


    protected static final Log logger = LogFactory.getLog(TemplateServiceImplement.class);


    @Override
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        try {
            return getListModel(baseFilePojo, creator);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(" ERROR : " + e);
            return Set.of();
        }
    }


    private Set<ModelOup> getListModel(ArchivoBaseDatosPojo archivo, Creador creador) throws InterruptedException {

        logger.info("start build list of Module");
        Set<ModelOup> response = new HashSet<>();

        for (EntityPojo entidad : archivo.getEntidades()) {
            if (entidad.getIsEntity()) {
                logger.info("start create the module implement Service 07" + entidad.getNombreClase());
                response.add(this.getTemplate(entidad, archivo, creador));
            }
        }
        return response;
    }


}
