package com.generator.core.pojos.back;

public class Creador {

    private static final char DOT = '.';
    private String context;
    private String proyectoName = "";
    private String packageNames = "";
    private String description = "";
    private String packageNames1 = "";
    private String artifact = "";
    private String com = "";
    private String sDirectorioTrabajo;
    private String direccionDeCarpeta;
    private String direccionOriginal;


    public Creador() {
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getProyectoName() {
        return proyectoName;
    }

    public void setProyectoName(String proyectoName) {
        this.proyectoName = proyectoName;
    }

    public String getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(String packageNames) {
        this.packageNames = packageNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackageNames1() {
        return packageNames1;
    }

    public void setPackageNames1(String packageNames1) {
        this.packageNames1 = packageNames1;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getsDirectorioTrabajo() {
        return sDirectorioTrabajo;
    }

    public void setsDirectorioTrabajo(String sDirectorioTrabajo) {
        this.sDirectorioTrabajo = sDirectorioTrabajo;
    }

    public String getDireccionDeCarpeta() {
        return direccionDeCarpeta;
    }

    public void setDireccionDeCarpeta(String direccionDeCarpeta) {
        this.direccionDeCarpeta = direccionDeCarpeta;
    }

    public String getDireccionOriginal() {
        return direccionOriginal;
    }

    public void setDireccionOriginal(String direccionOriginal) {
        this.direccionOriginal = direccionOriginal;
    }
}
