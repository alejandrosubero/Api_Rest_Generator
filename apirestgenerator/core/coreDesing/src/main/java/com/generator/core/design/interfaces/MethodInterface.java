package com.generator.core.design.interfaces;



import com.generator.core.design.reference.RetunsType;

import java.util.List;

public interface MethodInterface extends DesingCommunInterface {

    // SyntaxOfMethod = annotation + modifiers + returnsType + methodName+ (parameter) + curlyBraces + methodBody + curlyBraces

    default public String returnsTypeBuild(RetunsType returnsType, String returnsClass){
        StringBuilder methodTx = new StringBuilder();
        methodTx.append(SPACE);
        if (!returnsType.equals(RetunsType.VOID) && !returnsType.equals(RetunsType.none)) {
            methodTx.append(stringEnsamble(List.of(returnsType.toString(),openAngleBrackets,returnsClass,closeAngleBrackets)));
        } else {
            methodTx.append(returnsClass.toString());
        }
        methodTx.append(SPACE);
        return methodTx.toString();
    }



    default public String bodyBuildStructure(Boolean curlyBraces, String methodBody){
        StringBuilder contexmethodBody = new StringBuilder();
        if (curlyBraces) {
            contexmethodBody.append( stringEnsamble(List.of(BRACKET_OPEN,BREAK_LINE)));
            if (methodBody != null) {
                contexmethodBody.append(methodBody);
            }
            contexmethodBody.append( stringEnsamble(List.of(BRACKET_CLOSE,BREAK_LINE)));
        } else {
            contexmethodBody.append(stringEnsamble(List.of(SEMICOLON,BREAK_LINE)));
        }
        return contexmethodBody.toString();
    }

}
