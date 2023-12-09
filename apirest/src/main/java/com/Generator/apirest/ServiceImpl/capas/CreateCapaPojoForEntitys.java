package com.Generator.apirest.ServiceImpl.capas;



import com.Generator.apirest.ServiceImpl.tool.CreateToolImpl;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.*;
import com.Generator.apirest.modelo.back.java07.CreateControlles07;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// @Scope("singleton")
@Component
public class CreateCapaPojoForEntitys {

    @Autowired
    private CreateControllerCapaPojo createControllerCapaPojo;

    @Autowired
    private CreateMapper createMapper;

    @Autowired
    private CreateValidation createValidation;

    @Autowired
    private CreateToolImpl createTool;

    @Autowired
    private CreateControlles createControlles;

    @Autowired
    private EntityResponseClass entityResponseClass;
    
    @Autowired
    private CreateControlles07 createControlles07;

    protected static final Log logger = LogFactory.getLog(CreateCapaPojoForEntitys.class);

    public void StartCreateCapaPojoForEntitys(ArchivoBaseDatosPojo archivo, Creador creador){


        if(archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
            createControllerCapaPojo.startCreacionControlles(archivo, creador);
            createMapper.initiarCreateMapper(archivo, creador);
            createValidation.startCreacion(archivo, creador);
            entityResponseClass.startCreateEntityResponseClass(archivo, creador);
        }else {
        	if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
        		createControlles07.startCreacionControlles(archivo, creador);
        	}else {
        		createControlles.startCreacionControlles(archivo, creador);
        	}
        }

        
        if(archivo.getIsToolActive()){
            createTool.inicioCreate(archivo, creador);
        }

        logger.info("Finalizo Creando Archivos de repositorios, servicios proyecto, mappers");
    }


}
