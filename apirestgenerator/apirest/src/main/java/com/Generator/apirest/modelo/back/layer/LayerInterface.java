package com.Generator.apirest.modelo.back.layer;


import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;

public interface LayerInterface {
    public Boolean createLayer(ArchivoBaseDatosPojo archivo, Creador creator);
}
