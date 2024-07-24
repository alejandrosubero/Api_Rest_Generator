package com.Generator.apirest.modelo.back.layer;

import com.Generator.apirest.ServiceImpl.layers.architecture.ArchitectuereModel;
import com.Generator.apirest.core.pojos.back.LayerPojo;
import org.springframework.stereotype.Component;

@Component
public class LayerFactory {

    private ArchitectuereModel architectuereModel;

    public LayerFactory(ArchitectuereModel architectuereModel) {
        this.architectuereModel = architectuereModel;
    }

    public  LayerInterface generateLayer(){
      return architectuereModel;
    }

}
