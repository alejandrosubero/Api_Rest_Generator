package com.Generator.apirest.ServiceImpl.layers;



import com.Generator.apirest.files.Creador;
import com.Generator.apirest.modelo.back.base.CreateEntity;
import com.Generator.apirest.modelo.back.javaPlus07.CreateRepositories;
import com.Generator.apirest.modelo.back.javaPlus07.CreateServices;
import com.Generator.apirest.modelo.back.javaPlus07.ServicesImplimet;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateClasesProyecto {

    private CreateEntity createEntity;
    private CreateRepositories createRepositories;
    private CreateServices createServices;
    private ServicesImplimet servicesImplimet;


    public CreateClasesProyecto(CreateEntity createEntity, CreateRepositories createRepositories, CreateServices createServices, ServicesImplimet servicesImplimet) {
        this.createEntity = createEntity;
        this.createRepositories = createRepositories;
        this.createServices = createServices;
        this.servicesImplimet = servicesImplimet;
    }

    protected static final Log logger = LogFactory.getLog(CreateClasesProyecto.class);

    public void StartCreateClasesProyecto(ArchivoBaseDatosPojo archivo, Creador creador) {

        logger.info("Creando Archivos de clases para el proyecto");
        try {
            createEntity.createEntityClassFile(archivo, creador);

            // TODO: LE FALTA LOGICA EN ESTE PUNTO PARA DIFERENCIAR LA CREACION DE LOS DIVERSOS COMPONENTES NUEVO

            createRepositories.initCreate(archivo, creador);
            logger.info("created repository");
            createServices.initCreate(archivo, creador);
            logger.info("created  services");
            servicesImplimet.startCreacionImplement(archivo, creador);
            logger.info("created  implement of services");

        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("ocurrio error: "+e);
        }

    }
}
