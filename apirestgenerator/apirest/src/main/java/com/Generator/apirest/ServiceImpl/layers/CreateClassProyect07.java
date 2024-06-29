package com.Generator.apirest.ServiceImpl.layers;


import com.Generator.apirest.files.Creador;
import com.Generator.apirest.modelo.back.base.CreateEntity;
import com.Generator.apirest.modelo.back.java07.CreateRepositorie07;
import com.Generator.apirest.modelo.back.java07.CreateService07;
import com.Generator.apirest.modelo.back.java07.CreateServiceImp07;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class CreateClassProyect07 {

    private static final Log logger = LogFactory.getLog(CreateClassProyect07.class);

    private CreateEntity createEntity;
    private CreateRepositorie07 createRepositorie07;
    private CreateService07 createService07;
    private CreateServiceImp07 createServiceImp07;


    public CreateClassProyect07(CreateEntity createEntity, CreateRepositorie07 createRepositorie07, CreateService07 createService07, CreateServiceImp07 createServiceImp07) {
        this.createEntity = createEntity;
        this.createRepositorie07 = createRepositorie07;
        this.createService07 = createService07;
        this.createServiceImp07 = createServiceImp07;
    }

    public void StartCreateClasesProyecto(ArchivoBaseDatosPojo archivo, Creador creador) {

        logger.info("Creting file for project in java 1.7 ");

        try {
            createEntity.createEntityClassFile(archivo, creador);
            logger.info("end the creation");


            createRepositorie07.startCreacion(archivo, creador);
            logger.info("created the repository ");

            createService07.startCreacion(archivo, creador);
            logger.info("created the service ");

            createServiceImp07.startCreacionImplement07(archivo, creador);
            logger.info("created the  service implement ");

        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(" Error en CreateClassProyect07.class error: " + e);
        }

    }


}
