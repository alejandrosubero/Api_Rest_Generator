package com.generator.model.controller;


import com.generator.core.pojos.back.LayerPojo;

public class ControllerFactory {


    private CreateControlles07 createControlles07;

    public ControllerFactory(CreateControlles07 createControlles07) {

        this.createControlles07 = createControlles07;
    }

    public ControllerInterface controllerCreator(LayerPojo layerPojo) {
        if (layerPojo.getCreateCapaJavaBase7()) {
            return this.createControlles07;
        }
        return null;
    }
}
