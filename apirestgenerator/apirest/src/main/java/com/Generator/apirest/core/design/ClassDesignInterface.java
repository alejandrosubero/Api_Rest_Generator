package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ParameterClassMethod;
import com.Generator.apirest.services.builders.IImportModel;

import java.util.List;

public interface ClassDesignInterface  extends DesingCommunInterface {


    default public String packagePahtBuild( String packagePaht,  String packageName ){
        return stringEnsamble(List.of(PACKAGE_CONST,packagePaht,DOT,packageName,SEMICOLON,BREAK_LINE ));
    }


}
