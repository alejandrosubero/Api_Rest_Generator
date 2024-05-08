package com.Generator.apirest.modelo.back.controller;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.back.LayerPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.springframework.stereotype.Component;

@Component
public class ControllerFactory {

    private CreateControllerCapaPojo createControllerCapaPojo;
    private CreateControlles createControlles;
    private CreateControlles07 createControlles07;

    public ControllerFactory(CreateControllerCapaPojo createControllerCapaPojo, CreateControlles createControlles, CreateControlles07 createControlles07) {
        this.createControllerCapaPojo = createControllerCapaPojo;
        this.createControlles = createControlles;
        this.createControlles07 = createControlles07;
    }

    public ControllerInterface controllerCreator(LayerPojo layerPojo) {
        if (layerPojo.getCreateCapaPojoForEntitys()) {
            return this.createControllerCapaPojo;
        } else if (layerPojo.getCreateCapaJavaBase7()) {
            return this.createControlles07;
        } else {
            return this.createControlles;
        }
    }

}
