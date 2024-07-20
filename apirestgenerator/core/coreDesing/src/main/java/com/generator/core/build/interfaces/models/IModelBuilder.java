package com.generator.core.build.interfaces.models;

import com.generator.core.interfaces.IImportModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.models.ModelOup;
import com.generator.core.pojos.Creador;

import java.util.Set;

public interface IModelBuilder extends IImportModel {
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
