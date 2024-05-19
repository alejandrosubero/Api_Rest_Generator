package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ParameterClassMethod;
import com.Generator.apirest.services.builders.IImportModel;

import java.util.List;

public interface ClassDesignInterface extends DesingCommunInterface {


    default public String packagePahtBuild(String packagePaht, String packageName) {
        return stringEnsamble(List.of(PACKAGE_CONST, SPACE, packagePaht, DOT, packageName, SEMICOLON, BREAK_LINE));
    }

    default public String classSignatuerAndContent(ClassDesign classDesign) {
        StringBuilder classTemplate = null;
        if (classDesign != null) {
            classTemplate = new StringBuilder();
            classTemplate.append(stringEnsamble(List.of(
                    classDesign.getModifier().toString().toLowerCase(), SPACE,
                    classDesign.getClassType().toString().toLowerCase(), SPACE,
                    classDesign.getClassName(), SPACE,
                    TAB, BRACKET_OPEN, BREAK_LINE)));
            if (classDesign.getContent() != null) {
                classTemplate.append(classDesign.getContent());
            }
            classTemplate.append(BREAK_LINE);
            classTemplate.append(stringEnsamble(List.of(BRACKET_CLOSE, BREAK_LINE)));
        }
        return classTemplate.toString();
    }


}
