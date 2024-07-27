package com.generator.core.interfaces;


import com.generator.core.pojos.ArchivoBaseDatosPojo;

public interface ServiceGenerateProjectRest {

    public boolean executeBase(ArchivoBaseDatosPojo archivo) throws Exception;

}
