package com.generator.plugin.build.interfaces.models;

import com.generator.plugin.pojos.ArchivoBaseDatosPojo;
import com.generator.plugin.build.models.ModelOup;
import com.generator.plugin.pojos.Creador;

import java.util.LinkedList;

public interface IFacadeModel {
    public LinkedList<ModelOup> getModels(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
