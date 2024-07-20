package com.generator.plugin.build.interfaces.plugin;

import com.generator.plugin.pojos.ArchivoBaseDatosPojo;
import com.generator.plugin.build.models.ModelOup;
import com.generator.plugin.pojos.Creador;

import java.util.LinkedList;
import java.util.List;

public interface IPluginConnection {

    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
    public String modelIdentifier();
    public List<String> methodList();
}
