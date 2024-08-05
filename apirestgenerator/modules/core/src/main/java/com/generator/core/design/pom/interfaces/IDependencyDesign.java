package com.generator.core.design.pom.interfaces;

import com.generator.core.design.interfaces.IDesingCommun;
import com.generator.core.design.pom.Dependency;

public interface IDependencyDesign extends IDesingCommun {

    default String toStringDependency(Dependency dependency){
        StringBuffer newString = new StringBuffer("<dependency>\n");

        newString.append(stringEnsamble(TAB,TAB,"<groupId>",dependency.getGroupId(),"</groupId>\n"));
        newString.append( stringEnsamble(TAB,TAB, "<artifactId>",dependency.getArtifactId(),"</artifactId>\n"));
        if(dependency.getVersion() != null){
            newString.append(stringEnsamble(TAB,TAB, "<version>",dependency.getVersion(),"</version>\n"));
        }
        newString.append(stringEnsamble("</dependency>"));
        return newString.toString();
    }
}


