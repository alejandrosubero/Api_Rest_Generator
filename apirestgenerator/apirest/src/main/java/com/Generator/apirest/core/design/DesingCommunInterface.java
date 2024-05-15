package com.Generator.apirest.core.design;

import com.Generator.apirest.core.build.ParameterClassMethod;
import com.Generator.apirest.services.builders.IImportModel;

import java.util.List;

public interface DesingCommunInterface extends IImportModel {


    default public String annotationBuild(List<String> annotation){
        StringBuilder methodTx = new StringBuilder();
        if (annotation != null && annotation.size() > 0) {
            annotation.stream().forEach(annotationOne ->{
                methodTx.append(stringEnsamble(List.of(annotationOne,BREAK_LINE)));
            });
        } else {
            methodTx.append(SPACE);
        }
        return methodTx.toString();
    }

    default public String parameterBuildStructure(List<ParameterClassMethod>parameters){
        StringBuilder contexParameter = new StringBuilder();

        if (parameters.size() > 0) {
            contexParameter.append(PARENTHESES_OPEN);
            for (int i = 0; i < parameters.size(); i++) {
                String annotation ="";
                if(parameters.get(i).getAnnotation().size() > 0){
                   annotation = this.annotationBuild(parameters.get(i).getAnnotation());
                }
                String parameter = parameters.get(i).toString().replaceAll("\\s{1,2}"," ");
                contexParameter.append(annotation);
                if (i < parameters.size() - 1) {
                    contexParameter.append(stringEnsamble(List.of(parameter,COMMA)));
                } else {
                    contexParameter.append(parameter);
                }
            }
            contexParameter.append(PARENTHESES_CLOSE );
        } else {
            contexParameter.append(PARENTHESES_OPEN_CLOSE);
        }
        return contexParameter.toString();
    }
}
