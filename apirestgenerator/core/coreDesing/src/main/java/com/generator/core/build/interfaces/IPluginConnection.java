package com.generator.core.build.interfaces;

import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.ModelOup;
import com.generator.core.pojos.Creador;

import java.util.LinkedList;

public interface IPluginConnection {

    public LinkedList<ModelOup> getModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
    public String modelIdentifier();
}
