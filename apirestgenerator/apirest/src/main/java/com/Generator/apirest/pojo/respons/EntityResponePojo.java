package com.Generator.apirest.pojo.respons;

import java.io.Serializable;
import java.util.List;


public class EntityResponePojo implements Serializable {
private static final long serialVersionUID = 9068664675778286044L;


    private String error;
   private String mensaje;
   private List<Object> entidades;


 public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getMensaje() {
        return mensaje;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public List<Object> getEntidades() {
        return entidades;
    }


    public void setEntidades(List<Object> entidades) {
        this.entidades = entidades;
    }
}