package com.Generator.apirest.services.builders;


import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

public interface ServicioGenerarproyectoRest {

    public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) throws Exception;

    public boolean generarBase07(ArchivoBaseDatosPojo archivoBaseDatosPojo);

}
