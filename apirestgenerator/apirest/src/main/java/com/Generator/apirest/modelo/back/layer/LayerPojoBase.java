package com.Generator.apirest.modelo.back.layer;


import com.Generator.apirest.ServiceImpl.layers.CreateCapaPojoForEntitys;
import com.Generator.apirest.ServiceImpl.layers.CreateClasesProyecto;
import com.Generator.apirest.core.files.AnadirCarpeta;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class LayerPojoBase implements LayerInterface{

    protected static final Log logger = LogFactory.getLog(LayerPojoBase.class);

    private Creador creator;
    private CreateClasesProyecto createClasesProyecto;
    private CreateCapaPojoForEntitys createCapaPojoForEntitys;
    private AnadirCarpeta folderDirectory;


    public LayerPojoBase(Creador creador, CreateClasesProyecto createClasesProyecto, CreateCapaPojoForEntitys createCapaPojoForEntitys, AnadirCarpeta anadirCarpeta) {
        this.creator = creador;
        this.createClasesProyecto = createClasesProyecto;
        this.createCapaPojoForEntitys = createCapaPojoForEntitys;
        this.folderDirectory = anadirCarpeta;
    }

    @Override
    public Boolean createLayer(ArchivoBaseDatosPojo baseFileDataPojo) {
        return generateBasePojo(baseFileDataPojo);
    }


    public boolean generateBasePojo(ArchivoBaseDatosPojo baseFileDataPojo){
        try {
            createClasesProyecto.StartCreateClasesProyecto(baseFileDataPojo, creator);
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
