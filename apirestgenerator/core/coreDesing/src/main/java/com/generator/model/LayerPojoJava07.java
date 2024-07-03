package com.generator.model;

import com.generator.core.build.interfaces.IPluginConnection;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.ModelOup;
import com.generator.core.pojos.Creador;

import java.util.LinkedList;

public class LayerPojoJava07 implements IPluginConnection {


    @Override
    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        return new FacadeModel().getModels(baseFilePojo, creator);
    }


    @Override
    public String modelIdentifier() {
        return "LayerPojoJava07";
    }
}
