package com.Generator.apirest.modelo.back.layer;

import com.Generator.apirest.ServiceImpl.layers.CreateCapaPojoForEntitys;
import com.Generator.apirest.ServiceImpl.layers.CreateClassProyect07;
import com.Generator.apirest.core.files.AnadirCarpeta;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class LayerPojoJava7 implements LayerInterface {

    protected static final Log logger = LogFactory.getLog(LayerPojoBase.class);

    private Creador creator;
    private CreateCapaPojoForEntitys createCapaPojoForEntitys;
    private AnadirCarpeta folderDirectory;
    private CreateClassProyect07 createClassProyect07;


    public LayerPojoJava7(Creador creador, CreateCapaPojoForEntitys createCapaPojoForEntitys, AnadirCarpeta anadirCarpeta, CreateClassProyect07 createClassProyect07) {
        this.creator = creador;
        this.createCapaPojoForEntitys = createCapaPojoForEntitys;
        this.folderDirectory = anadirCarpeta;
        this.createClassProyect07 = createClassProyect07;
    }


    @Override
    public Boolean createLayer(ArchivoBaseDatosPojo baseFileDataPojo) {
        return generateBase07(baseFileDataPojo);
    }


    public boolean generateBase07(ArchivoBaseDatosPojo baseFileDataPojo) {
        try {
            createClassProyect07.StartCreateClasesProyecto(baseFileDataPojo, creator);
            createCapaPojoForEntitys.createLayerPojoForEntitys(baseFileDataPojo, creator);
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
