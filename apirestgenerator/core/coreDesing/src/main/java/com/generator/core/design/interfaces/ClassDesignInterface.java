package com.generator.core.design.interfaces;


import com.generator.core.design.ClassDesign;
import com.generator.core.design.ParameterClassMethod;
import com.generator.core.design.reference.ClassModifier;

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
                  classTemplate.append( parameterClass.toString());
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
