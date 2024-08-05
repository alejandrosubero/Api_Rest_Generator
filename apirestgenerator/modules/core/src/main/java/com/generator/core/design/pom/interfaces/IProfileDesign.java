package com.generator.core.design.pom.interfaces;

import com.generator.core.design.interfaces.IDesingCommun;
import com.generator.core.design.pom.Profile;

public interface IProfileDesign  extends IDesingCommun {

    default String toStringProfile(Profile profile){
        StringBuffer buffer = new StringBuffer();
        buffer.append("\t<profile>\n");
        buffer.append(stringEnsamble("\t\t\t<id>",profile.getProfileId(),"</id>\n"));
        buffer.append("\t\t\t<activation>\n");
        buffer.append(stringEnsamble("\t\t\t\t<activeByDefault>",profile.getActiveByDefault().toString(),"</activeByDefault>\n"));
        buffer.append("\t\t\t</activation>\n");
        buffer.append( "\t\t\t<properties>\n");
        buffer.append( stringEnsamble("\t\t\t\t<spring.profiles.active>",profile.getProfileId(),"</spring.profiles.active>\n"));
        buffer.append( "\t\t\t</properties>\n");
        buffer.append("\t\t</profile>\n");
        buffer.append(BREAK_LINE);
        return buffer.toString();
    }
}
