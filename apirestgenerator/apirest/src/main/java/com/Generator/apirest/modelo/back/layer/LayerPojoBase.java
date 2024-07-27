package com.Generator.apirest.modelo.back.layer;


import com.Generator.apirest.ServiceImpl.layers.CreatePojoLayerForEntitys;
import com.Generator.apirest.ServiceImpl.layers.CreateClasesProyecto;
import com.Generator.apirest.files.AnadirCarpeta;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.generator.core.pojos.*;

@Component
public class LayerPojoBase implements LayerInterface{

    protected static final Log logger = LogFactory.getLog(LayerPojoBase.class);

    private Creador creator;
    private CreatePojoLayerForEntitys createPojoLayerForEntitys;
    private AnadirCarpeta folderDirectory;

    private CreateClasesProyecto createClasesProyecto;


    public LayerPojoBase(Creador creador, CreateClasesProyecto createClasesProyecto, CreatePojoLayerForEntitys createPojoLayerForEntitys, AnadirCarpeta anadirCarpeta) {
        this.creator = creador;
        this.createClasesProyecto = createClasesProyecto;
        this.createPojoLayerForEntitys = createPojoLayerForEntitys;
        this.folderDirectory = anadirCarpeta;
    }

    @Override
    public Boolean createLayer(ArchivoBaseDatosPojo baseFileDataPojo, Creador creador) {
        return generateBasePojo(baseFileDataPojo);
    }


    public boolean generateBasePojo(ArchivoBaseDatosPojo baseFileDataPojo){
        try {
            createClasesProyecto.StartCreateClasesProyecto(baseFileDataPojo, creator);
            createPojoLayerForEntitys.createLayerPojoForEntitys(baseFileDataPojo, creator);
            return addProyectToZipFileAndSave();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    private boolean addProyectToZipFileAndSave() {
        try {
            logger.info("Adding project to zip");
            this.folderDirectory.folderzip(creator.getProyectoName(), creator.getDireccionDeCarpeta(), creator.getProyectoName());
            logger.info("Saving Project, Clearing Cache");
            return this.folderDirectory.salveProyecto(creator.getDireccionDeCarpeta(), creator.getProyectoName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
