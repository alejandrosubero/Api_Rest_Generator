
package com.generator.model;

import com.generator.core.build.interfaces.models.IFacadeModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.models.ModelOup;
import com.generator.model.tempate.controller.TemplateControllers;
import com.generator.model.tempate.repositories.TemplateRepository;
import com.generator.model.tempate.service.TemplateService;
import com.generator.model.tempate.implement.TemplateServiceImplement;
import com.generator.model.tempate.pomxml.TemplatePomxml;

import java.util.LinkedList;

public class FacadeModel implements IFacadeModel {


    public FacadeModel() {
    }


    @Override
    public LinkedList<ModelOup> getModels(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {

        LinkedList<ModelOup> listModels = new LinkedList<>();

        listModels.addAll(TemplateControllers.getInstance().createModel(baseFilePojo, creator));
        listModels.addAll(TemplateRepository.getInstance().createModel(baseFilePojo, creator));
        listModels.addAll( new TemplateService().createModel(baseFilePojo, creator));
        listModels.addAll( new TemplateServiceImplement().createModel(baseFilePojo, creator));
        listModels.addAll( new TemplatePomxml().createModel(baseFilePojo, creator));

        return listModels;
    }
}
