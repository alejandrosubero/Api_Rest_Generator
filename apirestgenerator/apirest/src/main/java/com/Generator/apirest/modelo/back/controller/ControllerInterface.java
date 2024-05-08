package com.Generator.apirest.modelo.back.controller;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

public interface ControllerInterface {
    public void initCreateController(ArchivoBaseDatosPojo baseFilePojo, Creador creator);
}
