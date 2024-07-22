package com.generator.core.build.interfaces.plugin;

import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.models.ModelOup;

import java.util.LinkedList;
import java.util.List;

public interface IPluginConnection {

    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
    public String modelIdentifier();
    public List<String> methodList();
}
