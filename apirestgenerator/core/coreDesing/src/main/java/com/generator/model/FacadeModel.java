
package com.generator.model;

import com.generator.core.build.interfaces.IFacadeModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.build.ModelOup;
import com.generator.core.pojos.Creador;
import com.generator.model.controller.CreateControlles07;
import com.generator.model.java07.CreateRepositorie07;

import java.util.LinkedList;

public class FacadeModel implements IFacadeModel {


    public FacadeModel() {
    }


    @Override
    public LinkedList<ModelOup> getModels(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {
        LinkedList<ModelOup> listModels = new LinkedList<>();

        listModels.addAll(CreateControlles07.getInstance().createModel(baseFilePojo, creator));
        listModels.addAll(CreateRepositorie07.getInstance().createModel(baseFilePojo, creator));



        return listModels;
    }
}
