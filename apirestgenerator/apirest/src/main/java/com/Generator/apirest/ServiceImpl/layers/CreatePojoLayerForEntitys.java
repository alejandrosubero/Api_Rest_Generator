package com.Generator.apirest.ServiceImpl.layers;


import com.Generator.apirest.ServiceImpl.tool.CreateToolImpl;
import com.Generator.apirest.modelo.back.base.CreateMapper;
import com.Generator.apirest.modelo.back.base.CreateValidation;
import com.Generator.apirest.modelo.back.base.EntityResponseClass;
import com.Generator.apirest.modelo.back.controller.ControllerFactory;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class CreatePojoLayerForEntitys {

    protected static final Log logger = LogFactory.getLog(CreatePojoLayerForEntitys.class);
    //    private ControllerFactory controllerFactory;
    private CreateMapper createMapper;
    private CreateValidation createValidation;
    private CreateToolImpl createTool;
    private EntityResponseClass entityResponseClass;



    public CreatePojoLayerForEntitys( CreateMapper createMapper, CreateValidation createValidation, CreateToolImpl createTool, EntityResponseClass entityResponseClass) {
        this.createMapper = createMapper;
        this.createValidation = createValidation;
        this.createTool = createTool;
        this.entityResponseClass = entityResponseClass;
    }
//    public CreatePojoLayerForEntitys(ControllerFactory controllerFactory, CreateMapper createMapper, CreateValidation createValidation, CreateToolImpl createTool, EntityResponseClass entityResponseClass) {
////        this.controllerFactory = controllerFactory;
//        this.createMapper = createMapper;
//        this.createValidation = createValidation;
//        this.createTool = createTool;
//        this.entityResponseClass = entityResponseClass;
//    }


    public void createLayerPojoForEntitys(ArchivoBaseDatosPojo archivo, Creador creador) {

//        if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
            createMapper.initiarCreateMapper(archivo, creador);
            createValidation.startCreacion(archivo, creador);
//        }

//        this.controllerFactory.controllerCreator(archivo.getCapaPojo()).initCreateController(archivo, creador);
        this.entityResponseClass.startCreateEntityResponseClass(archivo, creador);

        if (archivo.getIsToolActive()) {
            //TODO: CREATE A FACTORI OF TOOLS
            createTool.inicioCreate(archivo, creador);
        }

        logger.info(" Create Layer Pojo For Entitys ");
    }


}
