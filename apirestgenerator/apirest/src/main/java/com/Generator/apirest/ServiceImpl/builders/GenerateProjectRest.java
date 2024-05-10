package com.Generator.apirest.ServiceImpl.builders;


import com.Generator.apirest.ServiceImpl.layers.CreateBaseFiles;
import com.Generator.apirest.converter.ConvertEntityToModelT;
import com.Generator.apirest.core.AnadirCarpeta;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.layer.LayerFactory;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IBaseModel;
import com.Generator.apirest.services.builders.ServiceGenerateProjectRest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class GenerateProjectRest implements ServiceGenerateProjectRest, IBaseModel {

    protected static final Log logger = LogFactory.getLog(GenerateProjectRest.class);

    private ConvertEntityToModelT convertEntityToModelT;
    private Creador creador;
    private CreateBaseFiles createBaseFiles;
    private LayerFactory layerFactory;
    private AnadirCarpeta anadirCarpeta;


    public GenerateProjectRest(ConvertEntityToModelT convertEntityToModelT, Creador creador, CreateBaseFiles createBaseFiles, LayerFactory layerFactory, AnadirCarpeta anadirCarpeta) {
        this.convertEntityToModelT = convertEntityToModelT;
        this.creador = creador;
        this.createBaseFiles = createBaseFiles;
        this.layerFactory = layerFactory;
        this.anadirCarpeta = anadirCarpeta;
    }

    @Override
    public boolean executeBase(ArchivoBaseDatosPojo baseFileDataPojo) {

        String model = baseFileDataPojo.getCapaPojo().getModelT().trim();
        String modelM = model.substring(0, 1).toUpperCase() + model.substring(1);
        baseFileDataPojo.getCapaPojo().setModelM(modelM);

//        baseFileDataPojo.getCapaPojo().setModelM(this.capitalizeFirstLetter(baseFileDataPojo.getCapaPojo().getModelT().trim()));
        baseFileDataPojo.getMethodManager().validDefault(baseFileDataPojo.isMethoddefaultValue());

        if (baseFileDataPojo.getMethodManager().isMethodDelete()) {
            baseFileDataPojo.getEntidades().stream().forEach(entityPojo -> entityPojo.deleteActive(true));
        }
        if (baseFileDataPojo.getCapaPojo().getCreateCapaPojoForEntitys()) {
            baseFileDataPojo.setEntidades(convertEntityToModelT.startConvertEntityToModelT(baseFileDataPojo));
        }
        this.creador.setDatos(baseFileDataPojo);
        this.createBaseFiles.create(baseFileDataPojo, creador);


        return this.layerFactory.generateLayer(baseFileDataPojo.getCapaPojo()).createLayer(baseFileDataPojo);
    }

}
