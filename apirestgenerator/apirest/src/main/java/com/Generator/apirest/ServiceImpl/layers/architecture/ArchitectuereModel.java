package com.Generator.apirest.ServiceImpl.layers.architecture;

import com.Generator.apirest.ServiceImpl.layers.CreatePojoLayerForEntitys;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.pojos.back.Creador;
import com.Generator.apirest.files.AnadirCarpeta;
import com.Generator.apirest.modelo.back.layer.LayerInterface;
import com.Generator.apirest.modelo.back.layer.LayerPojoBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ArchitectuereModel  implements LayerInterface {

    protected static final Log logger = LogFactory.getLog(ArchitectuereModel.class);

    private Creador creator;
    private CreatePojoLayerForEntitys createPojoLayerForEntitys;
    private AnadirCarpeta folderDirectory;
    private ArchitectuereClass architectuereClass;

    public ArchitectuereModel( CreatePojoLayerForEntitys createPojoLayerForEntitys, AnadirCarpeta folderDirectory, ArchitectuereClass architectuereClass) {

        this.createPojoLayerForEntitys = createPojoLayerForEntitys;
        this.folderDirectory = folderDirectory;
        this.architectuereClass = architectuereClass;
    }


    @Override
    public Boolean createLayer(ArchivoBaseDatosPojo baseFileDataPojo, Creador creator) {
        this.creator = creator;
        return generateBase(baseFileDataPojo);
    }


    public boolean generateBase(ArchivoBaseDatosPojo baseFileDataPojo) {
        try {
            architectuereClass.createClasesProyecto(baseFileDataPojo, creator);
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
