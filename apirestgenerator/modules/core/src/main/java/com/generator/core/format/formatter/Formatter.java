package com.generator.core.format.formatter;


import com.generator.core.format.interfaces.IFormatter;
import com.generator.core.format.operator.FormatterCode;

public class Formatter {

    private IFormatter iFormatter;

    public Formatter() {
        this.iFormatter = new FormatterCode();
    }

    public String simpleFormat(String multiLineText){
        return this.codeFormatter(this.removeFormatte(multiLineText));
    }

    public String removeFormatte(String multiLineText){
        return iFormatter.removeFormatte(multiLineText);
    }

    public String codeFormatter(String codeNoFornate){
        return iFormatter.codeFormatter(codeNoFornate);
    }

}
