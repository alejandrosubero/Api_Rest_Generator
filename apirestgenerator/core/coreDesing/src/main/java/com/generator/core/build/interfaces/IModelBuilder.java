package com.generator.core.build.interfaces;

import com.generator.core.interfaces.IImportModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.ModelOup;
import com.generator.core.pojos.Creador;

import java.util.Set;

public interface IModelBuilder extends IImportModel {
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
