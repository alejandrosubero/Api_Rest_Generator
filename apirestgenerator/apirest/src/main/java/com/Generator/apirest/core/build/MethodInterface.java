package com.Generator.apirest.core.build;

import com.Generator.apirest.services.builders.IBaseModel;

import java.util.List;

public interface MethodInterface extends IBaseModel {


    default public String returnsTypeBuild(RetunsType returnsType, String returnsClass){
        StringBuilder methodTx = new StringBuilder();

        if (!returnsType.equals(RetunsType.VOID) && !returnsType.equals(RetunsType.none)) {
            methodTx.append(
                    
                    space + returnsType.toString() + openAngleBrackets + returnsClass + closeAngleBrackets);
        } else {
            methodTx.append( space + returnsClass.toString());
        }
        return methodTx.toString();
    }


    default public String annotationBuild(List<String> annotation){
        StringBuilder methodTx = new StringBuilder();

        if (annotation != null && annotation.size() > 0) {

            methodTx.append();
        } else {
            methodTx.append(" ");
        }
        return methodTx.toString();
    }


}
