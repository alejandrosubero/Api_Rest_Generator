package com.Generator.apirest.core.build.interfaces.models;



import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.files.Creador;

import java.util.LinkedList;

public interface IFacadeModel {
    public LinkedList<ModelOup> getModels(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
