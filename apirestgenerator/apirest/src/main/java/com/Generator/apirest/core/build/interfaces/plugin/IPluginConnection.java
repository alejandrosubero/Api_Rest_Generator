package com.Generator.apirest.core.build.interfaces.plugin;



import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.files.Creador;

import java.util.LinkedList;
import java.util.List;

public interface IPluginConnection {

    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
    public String modelIdentifier();
    public List<String> methodList();
}
