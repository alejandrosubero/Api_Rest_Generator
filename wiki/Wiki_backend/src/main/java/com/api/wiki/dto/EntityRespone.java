
package com.api.wiki.dto;

import java.io.Serializable;
import java.util.List;


public class EntityRespone implements Serializable {
    private static final long serialVersionUID = -8302200628145698257L;
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


