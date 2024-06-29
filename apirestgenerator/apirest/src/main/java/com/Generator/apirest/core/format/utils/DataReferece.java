package com.Generator.apirest.core.format.utils;

public enum DataReferece {

    FOLDER("DataFiles"),
    FILE("Formate.txt"),
    BREAK_LINE("\r\n"),
    DOUBLETAB("\t\t"),
    LINE_SPACE_REGEX("(?m)^( {0,3})"),
    SPLIT_REGEX("\\n"),
    REGEX_A("(\\n)+"),
    REGEX_B("(\\n\\s*){2,}"),
    INDENT_REGEX("^(?!\\s*$)"),
    REGEX_C("\n"),
    TAB("\t"),
    SAMPLE_FOLDER("/samples"),
    SAMPLE_FILE("/sample2.txt"),
    OVERRIDE("@Override");

    private final String nombre;

    DataReferece(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
