package com.generator.plugin.build.interfaces.models;

import com.generator.plugin.interfaces.IImportModel;
import com.generator.plugin.pojos.ArchivoBaseDatosPojo;
import com.generator.plugin.build.models.ModelOup;
import com.generator.plugin.pojos.Creador;

import java.util.Set;

public interface IModelBuilder extends IImportModel {
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
