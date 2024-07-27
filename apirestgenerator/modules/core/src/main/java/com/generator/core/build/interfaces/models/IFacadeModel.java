package com.generator.core.build.interfaces.models;





import com.generator.core.build.models.ModelOup;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;

import java.util.LinkedList;

public interface IFacadeModel {
    public LinkedList<ModelOup> getModels(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
