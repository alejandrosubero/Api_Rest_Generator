package com.generator.model.template;

import com.generator.core.build.models.ModelOup;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;
import com.generator.model.FacadeModel;
import com.generator.model.tempate.controller.TemplateControllers;
import com.generator.model.util.JsonToObject;

import java.util.LinkedList;
import java.util.Set;

public class TemplateControllerTest {

    private JsonToObject jsonToObject;
    private Creador creador;

    public TemplateControllerTest() {

    }

    public LinkedList<ModelOup> runTest() {
        LinkedList<ModelOup> models = new LinkedList<>();

        this.jsonToObject = new JsonToObject();
        this.creador = new Creador();
        ArchivoBaseDatosPojo baseFileDataPojo = this.jsonToObject.getArchivoBaseDatosPojo();



        String model = baseFileDataPojo.getCapaPojo().getModelT().trim();
        String modelM = model.substring(0, 1).toUpperCase() + model.substring(1);

        baseFileDataPojo.getCapaPojo().setModelM(modelM);
        baseFileDataPojo.getMethodManager().validDefault(baseFileDataPojo.isMethoddefaultValue());

        if (baseFileDataPojo.getMethodManager().isMethodDelete()) {
            baseFileDataPojo.getEntidades().stream().forEach(entityPojo -> entityPojo.deleteActive(true));
        }

        this.creador.setDatos(baseFileDataPojo);

        FacadeModel facadeModel = new FacadeModel();

//        TemplateControllers template = TemplateControllers.getInstance();
//        Set<ModelOup> createModels = template.createModel(baseFileDataPojo, creador);
        LinkedList<ModelOup> arModel = facadeModel.getModels(baseFileDataPojo, creador);

        return arModel;
    }
}
