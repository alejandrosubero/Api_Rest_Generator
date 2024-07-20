package com.Generator.apirest.core.build.interfaces.models;



import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.interfaces.IImportModel;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.files.Creador;

import java.util.Set;

public interface IModelBuilder extends IImportModel {
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
