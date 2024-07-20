package com.generator.model;

import com.generator.core.build.interfaces.plugin.IPluginConnection;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.models.ModelOup;
import com.generator.core.pojos.Creador;

import java.util.LinkedList;
import java.util.List;

public class LayerPojoJava07 implements IPluginConnection {

    public LayerPojoJava07() {
    }

    @Override
    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        return new FacadeModel().getModels(baseFilePojo, creator);
    }

    @Override
    public String modelIdentifier() {
        return "LayerPojoJava07";
    }

    @Override
    public List<String> methodList() {
        return List.of();
    }


}
