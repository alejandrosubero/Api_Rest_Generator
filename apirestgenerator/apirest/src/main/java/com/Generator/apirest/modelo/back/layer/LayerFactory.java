package com.Generator.apirest.modelo.back.layer;

import com.Generator.apirest.core.pojos.back.LayerPojo;
import org.springframework.stereotype.Component;

@Component
public class LayerFactory {

    private LayerPojoBase layerPojoBase;
    private LayerPojoJava7 layerPojoJava7;

    public LayerFactory(LayerPojoBase layerPojoBase, LayerPojoJava7 layerPojoJava7) {
        this.layerPojoBase = layerPojoBase;
        this.layerPojoJava7 = layerPojoJava7;
    }

    public  LayerInterface generateLayer(LayerPojo layerPojo){
        if (layerPojo.getCreateCapaJavaBase7()) {
            return this.layerPojoJava7;
        } else {
            return this.layerPojoBase;
        }
    }

}
