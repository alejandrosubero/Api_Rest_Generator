package com.Generator.apirest.ServiceImpl.capas;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.CreacionDeClases;
import com.Generator.apirest.modelo.back.RepositoriesServices;
import com.Generator.apirest.modelo.back.ServicesImplimet;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class CreateClasesProyecto {

    @Autowired
    private CreacionDeClases creacionDeClases;

    @Autowired
    private RepositoriesServices repositoriesServices;

    @Autowired
    private ServicesImplimet servicesImplimet;



    protected static final Log logger = LogFactory.getLog(CreateClasesProyecto.class);

    public void StartCreateClasesProyecto(ArchivoBaseDatosPojo archivo, Creador creador) {

        logger.info("Creando Archivos de clases para el proyecto");

        try {
            creacionDeClases.startCreacionDeClases(archivo, creador);
//            creacionDeClases.createClass2();
            logger.info("Finalizo Creando Archivos de clases para el proyecto");


            // NOTA LE FALTA LOGICA EN ESTE PUNTO PARA DIFERENCIAR LA CREACION DE LOS DIVERSOS COMPONENTES NUEVO
            logger.info("Creando Archivos de repositorios, servicios  proyecto");
            repositoriesServices.startCreacion(archivo, creador);
            servicesImplimet.startCreacionImplement(archivo, creador);


        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("ocurrio error: "+e);
        }

    }
}
