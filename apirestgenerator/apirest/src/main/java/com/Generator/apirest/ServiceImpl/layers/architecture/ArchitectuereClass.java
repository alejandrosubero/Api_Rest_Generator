package com.Generator.apirest.ServiceImpl.layers.architecture;

import com.Generator.apirest.ServiceImpl.layers.CreateClassProyect07;

import com.Generator.apirest.files.PluginManagers;
import com.Generator.apirest.modelo.back.base.CreateEntity;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ArchitectuereClass {

    private static final Log logger = LogFactory.getLog(ArchitectuereClass.class);
    private CreateEntity createEntity;
    private PluginManagers pluginManagers;

    public ArchitectuereClass(CreateEntity createEntity, PluginManagers pluginManagers) {
        this.createEntity = createEntity;
        this.pluginManagers = pluginManagers;
    }

    public void createClasesProyecto(ArchivoBaseDatosPojo archivo, Creador creador) {

        logger.info("Creting file for project in java ");
        try {
            this.pluginManagers.scanAndLoadPlugins();
            createEntity.createEntityClassFile(archivo, creador);
            pluginManagers.executeBuild(archivo, creador, archivo.getCapaPojo().getArchitecture());
            logger.info("end the creation");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(" Error en CreateClassProyect07.class error: " + e);
        }

    }
}
