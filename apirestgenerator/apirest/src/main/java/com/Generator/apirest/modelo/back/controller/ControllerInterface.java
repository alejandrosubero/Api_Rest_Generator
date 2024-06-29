package com.Generator.apirest.modelo.back.controller;

import com.Generator.apirest.files.Creador;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;

public interface ControllerInterface {
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
