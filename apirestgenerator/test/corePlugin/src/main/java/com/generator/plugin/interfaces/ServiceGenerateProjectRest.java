package com.generator.plugin.interfaces;


import com.generator.plugin.pojos.ArchivoBaseDatosPojo;

public interface ServiceGenerateProjectRest {

    public boolean executeBase(ArchivoBaseDatosPojo archivo) throws Exception;

}
