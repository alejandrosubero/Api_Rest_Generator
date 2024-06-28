package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ClassModifier;
import com.Generator.apirest.core.build.FormatText;
import com.Generator.apirest.core.build.Modifier;
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

            classTemplate.append( stringEnsamble(List.of(
                    classDesign.getModifier().toString().toLowerCase(), SPACE,
                    classDesign.getClassType().toString().toLowerCase(), SPACE,
                    classDesign.getClassName())));

            if (classDesign.getClassIsImplement()) {
                classTemplate.append( stringEnsamble(List.of(
                        SPACE, ClassModifier.IMPLEMENTS.toString().toLowerCase(),
                        SPACE, classDesign.getClasImplement())));
            }

            if (classDesign.getClassIsInheritance()) {
                classTemplate.append(stringEnsamble(List.of(
                                SPACE, ClassModifier.EXTENDS.toString().toLowerCase(),
                                SPACE, classDesign.getClassInheritance())));
            }

            classTemplate.append(stringEnsamble(List.of(SPACE, BRACKET_OPEN, BREAK_LINE)));

            if(classDesign.getClassParameterClassMethods().size() > 0){
              for(ParameterClassMethod parameterClass :  classDesign.getClassParameterClassMethods()){
                  classTemplate.append( new FormatText().reformat(parameterClass.toString()));
              }
            }


            if (classDesign.getContent() != null) {
                classTemplate.append(classDesign.getContent());
            }

            classTemplate.append(BREAK_LINE);
            classTemplate.append(stringEnsamble(List.of(BRACKET_CLOSE, BREAK_LINE)));
        }
        return classTemplate.toString();
    }


}
