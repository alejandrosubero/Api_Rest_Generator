package com.Generator.apirest.modelo.back.layer;

import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.pojos.back.Creador;

public interface LayerInterface {
    public Boolean createLayer(ArchivoBaseDatosPojo archivo, Creador creator);
}
