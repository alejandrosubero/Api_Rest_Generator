package com.Generator.apirest.core.build;

import com.Generator.apirest.services.builders.IBaseModel;

import java.util.List;

public interface MethodInterface extends IBaseModel {

    // SyntaxOfMethod = annotation + modifiers + returnsType + methodName+ (parameter) + curlyBraces + methodBody + curlyBraces

    default public String returnsTypeBuild(RetunsType returnsType, String returnsClass){
        StringBuilder methodTx = new StringBuilder();
        methodTx.append(SPACE);
        if (!returnsType.equals(RetunsType.VOID) && !returnsType.equals(RetunsType.none)) {
            methodTx.append(stringEnsamble(List.of(returnsType.toString(),openAngleBrackets,returnsClass,closeAngleBrackets)));
        } else {
            methodTx.append(returnsClass.toString());
        }
        return methodTx.toString();
    }


    default public String annotationBuild(List<String> annotation){
        StringBuilder methodTx = new StringBuilder();
        if (annotation != null && annotation.size() > 0) {
            annotation.stream().forEach(annotationOne ->{
                methodTx.append(stringEnsamble(List.of(annotationOne,BREAK_LINE)));
            });
        } else {
            methodTx.append(" ");
        }
        return methodTx.toString();
    }


    default public String parameterBuildStructure(List<String>parameters){
        StringBuilder contexParameter = new StringBuilder();
        if (parameters.size() > 0) {
            contexParameter.append(PARENTHESES_OPEN);
            for (int i = 0; i < parameters.size(); i++) {
                if (i < parameters.size() - 1) {
                    contexParameter.append(stringEnsamble(List.of(parameters.get(i),COMMA)));
                } else {
                    contexParameter.append(parameters.get(i));
                }
            }
            contexParameter.append(PARENTHESES_CLOSE );
        } else {
            contexParameter.append(PARENTHESES_OPEN_CLOSE);
        }
        contexParameter.append(BREAK_LINE);
        return contexParameter.toString();
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
