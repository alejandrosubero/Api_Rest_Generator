package com.Generator.apirest.services.builders;


import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

public interface ServicioGenerarproyectoRest {

    public boolean executeBase(ArchivoBaseDatosPojo archivo) throws Exception;

    public boolean generateBase07(ArchivoBaseDatosPojo archivoBaseDatosPojo);

}
