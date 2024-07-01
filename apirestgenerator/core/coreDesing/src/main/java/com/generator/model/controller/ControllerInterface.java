package com.generator.model.controller;

import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.files.Creador;

public interface ControllerInterface {
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
