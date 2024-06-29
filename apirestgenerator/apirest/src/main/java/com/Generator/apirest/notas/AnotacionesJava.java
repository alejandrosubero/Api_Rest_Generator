package com.Generator.apirest.notas;

import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.springframework.stereotype.Component;

import java.util.Date;

// @Scope("singleton")
@Component
public class AnotacionesJava {

    private String autor;
    private String user;
    private String creadoPor;
    private String description;
    private String version;
    private ArchivoBaseDatosPojo archivos;
    private Date fecha;

    public AnotacionesJava (){ }
    
    
    public AnotacionesJava(ArchivoBaseDatosPojo archivos){
    	this.activateAnotacionesJava(archivos);
    }

    
    public void activateAnotacionesJava(ArchivoBaseDatosPojo archivos){
        this.autor = archivos.getAutor();
        this.user = archivos.getUser();
        this.creadoPor =" ";
        this.description = archivos.getDescription();
        this.version = archivos.getPrograntVersion();
        this.archivos = archivos;
        this.fecha = new Date();
    }


    public StringBuilder creatNotaClase() {
        StringBuilder sb4 = new StringBuilder();
        sb4.append("/*" + "\r\n");
        sb4.append("Create on " +fecha+ "\r\n");
        sb4.append("*Copyright (C) "+fecha.getYear()+"." + "\r\n");
        sb4.append("@author "+ autor + "\r\n");
        sb4.append("@author "+ user + "\r\n");
        sb4.append("@author "+ creadoPor + "\r\n");
        sb4.append("@since "+ archivos.getJavaVersion() + "\r\n");
        sb4.append("@version"+version + "\r\n");
        sb4.append("@version  %I%, %G%"+ "\r\n");
        sb4.append("*<p>Description: "+description+" </p>" + "\r\n");
        sb4.append("*/" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }


    public  StringBuilder metodosDoc(String param, String returm, String see) {
        StringBuilder sb5 = new StringBuilder();
        sb5.append("" + "\r\n");
        sb5.append(" /*" + "\r\n");
//        sb5.append("@author: "+ autor + "\r\n");
        if(param != null && !param.equals(""))
        sb5.append("@param: " + param +"\r\n");

        if(returm != null && !returm.equals(""))
        sb5.append("@return: "+ returm+ "\r\n");

        if(param != null && !param.equals(""))
        sb5.append("@see: " + see + "\r\n");

        sb5.append("@since:" + this.version+ "\r\n");

        sb5.append("Create on " +fecha+ "\r\n");
        sb5.append("*/" + "\r\n");
        return sb5;
    }


    public static StringBuilder apacheSoftwareLicensed() {
        StringBuilder sb3 = new StringBuilder();
        sb3.append(" /*" + "\r\n");
        sb3.append(" Copyright (C) 2008 Google Inc." + "\r\n");
        sb3.append("* Licensed to the Apache Software Foundation (ASF) under one or more" + "\r\n");
        sb3.append("* contributor license agreements.  See the NOTICE file distributed with" + "\r\n");
        sb3.append("* this work for additional information regarding copyright ownership." + "\r\n");
        sb3.append("* The ASF licenses this file to You under the Apache License, Version 2.0" + "\r\n");
        sb3.append("* (the \"License\"); you may not use this file except in compliance with" + "\r\n");
        sb3.append("* the License.  You may obtain a copy of the License at" + "\r\n");
        sb3.append("*" + "\r\n");
        sb3.append("*      http://www.apache.org/licenses/LICENSE-2.0" + "\r\n");
        sb3.append("*" + "\r\n");
        sb3.append("* Unless required by applicable law or agreed to in writing, software" + "\r\n");
        sb3.append("* distributed under the License is distributed on an \"AS IS\" BASIS," + "\r\n");
        sb3.append("* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied." + "\r\n");
        sb3.append("* See the License for the specific language governing permissions and" + "\r\n");
        sb3.append("* limitations under the License." + "\r\n");
        sb3.append("*/" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }


}


/*
*@Documented – Hará que la anotación se mencione en el javadoc.
*@author: Nombre del desarrollador / Nombre autor o autores
*@deprecated: Indica que el método y que no se recomienda su uso / Descripción
*@param: Definición de un parámetro de un método, es requerido para todos los parámetros del método/ Nombre de parámetro y descripción
*@return: Informa de lo que devuelve el método, no se aplica en constructores o métodos "void"/ Descripción del valor de retorno
*@see: Asocia con otro método o clase / Referencia cruzada referencia (#método(); clase#método(); paquete.clase; paquete.clase#método()).
*@version: Versión del método o clase.
* @since JDK1.2"
* @link: link de referencia en este cas va a ser el link de la codeANA <a href = "http://www.aprenderaprogramar.com" />  ANACODE WEB </a>
*
*/

/*
*
/**
ejemplo
 * Esta clase define objetos que contienen tantos enteros aleatorios entre 0 y 1000 como se le definen al crear un objeto
 * @author: Mario R. Rancel
 * @version: 22/09/2016/A
 * @see <a href = "http://www.aprenderaprogramar.com" />  Didáctica en programación </a>
 */