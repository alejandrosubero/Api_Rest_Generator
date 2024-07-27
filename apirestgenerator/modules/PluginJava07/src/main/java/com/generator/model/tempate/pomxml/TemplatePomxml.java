package com.generator.model.tempate.pomxml;



import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.build.models.ModelOup;
import com.generator.core.pojos.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.HashSet;
import java.util.Set;


public class TemplatePomxml implements IModelBuilder, ITemplatePomxml {

    protected static final Log logger = LogFactory.getLog(TemplatePomxml.class);

    @Override
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        Set<ModelOup> response = new HashSet<>();
        try {
            response.add(this.getTemplate(baseFilePojo, creator));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" ERROR : " + e);
            return Set.of();
        }
    }
}
