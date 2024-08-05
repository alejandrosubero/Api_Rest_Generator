package com.generator.core.design.pom.interfaces;

import com.generator.core.design.interfaces.IDesingCommun;
import com.generator.core.design.pom.PomDesign;

public interface IPomDesignInterface  extends IDesingCommun {

    default String getProfileString(PomDesign pomDesign){
        StringBuffer buffer = new StringBuffer("<profiles>\n");
        if(pomDesign.getProfiles() != null && pomDesign.getProfiles().size()>0){
            pomDesign.getProfiles().forEach(profile -> {
                buffer.append(profile.toString() );
            });
        }
        buffer.append("</profiles>\n");
        buffer.append(BREAK_LINE);
        return buffer.toString();
    }


    default String getDependenciesString(PomDesign pomDesign){
        StringBuffer buffer = new StringBuffer("<dependencies>\n");
        if(pomDesign.getDependencies() != null && pomDesign.getDependencies().size()>0){
            pomDesign.getDependencies().forEach(dependency -> {
                buffer.append(dependency.toString() );
            });
        }
        buffer.append("</dependencies>\n");
        buffer.append(BREAK_LINE);
        return buffer.toString();
    }

}



