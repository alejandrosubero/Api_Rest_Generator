package com.Generator.apirest.ServiceImpl.capas;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.CreacionDeClases;
import com.Generator.apirest.modelo.back.java07.CreateRepositorie07;
import com.Generator.apirest.modelo.back.java07.CreateService07;
import com.Generator.apirest.modelo.back.java07.CreateServiceImp07;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Scope("singleton")
@Component
public class CreateClasesProyecto07 {


    @Autowired
    private CreacionDeClases creacionDeClases;

    @Autowired
    private CreateRepositorie07 createRepositorie07;

    @Autowired
    private CreateService07 createService07;

    @Autowired
    private CreateServiceImp07 createServiceImp07;


    protected static final Log logger = LogFactory.getLog(CreateClasesProyecto07.class);

    public void StartCreateClasesProyecto(ArchivoBaseDatosPojo archivo, Creador creador) {

        logger.info("Creando Archivos de clases para el proyecto");

        try {
            creacionDeClases.startCreacionDeClases(archivo, creador);
           // creacionDeClases.createClass2();
            logger.info("Finalizo Creando Archivos de clases para el proyecto");

            logger.info("Creando Archivos de repositorios, servicios  proyecto");
            createRepositorie07.startCreacion(archivo,creador);
            createService07.startCreacion(archivo, creador);
            createServiceImp07.startCreacionImplement07(archivo, creador);

        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("ocurrio error en CreateClasesProyecto07.class error: "+e);
        }

    }


}
