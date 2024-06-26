package com.Generator.apirest.ServiceImpl.builders;


import com.Generator.apirest.ServiceImpl.capas.CreateArchivosBase;
import com.Generator.apirest.ServiceImpl.capas.CreateCapaPojoForEntitys;
import com.Generator.apirest.ServiceImpl.capas.CreateClasesProyecto;
import com.Generator.apirest.ServiceImpl.capas.CreateClasesProyecto07;
import com.Generator.apirest.converter.ConvertEntityToModelT;
import com.Generator.apirest.converter.ConvertEntityToPojo;
import com.Generator.apirest.core.AnadirCarpeta;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.ServicioGenerarproyectoRest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerarproyectoRest implements ServicioGenerarproyectoRest {

    protected static final Log logger = LogFactory.getLog(GenerarproyectoRest.class);

    private ConvertEntityToPojo convertEntityToPojo;
    private ConvertEntityToModelT convertEntityToModelT;
    private Creador creador;
    private CreateArchivosBase createArchivosBase;
    private CreateClasesProyecto createClasesProyecto;
    private CreateCapaPojoForEntitys createCapaPojoForEntitys;
    private AnadirCarpeta anadirCarpeta;
    private CreateClasesProyecto07 createClasesProyecto07;

    public GenerarproyectoRest( ConvertEntityToPojo convertEntityToPojo, ConvertEntityToModelT convertEntityToModelT, Creador creador, CreateArchivosBase createArchivosBase, CreateClasesProyecto createClasesProyecto, CreateCapaPojoForEntitys createCapaPojoForEntitys, CreateClasesProyecto07 createClasesProyecto07,AnadirCarpeta anadirCarpeta){
        this.createClasesProyecto07 = createClasesProyecto07;
        this.anadirCarpeta = anadirCarpeta;
        this.createCapaPojoForEntitys = createCapaPojoForEntitys;
        this.createClasesProyecto = createClasesProyecto;
        this.createArchivosBase = createArchivosBase;
        this.creador = creador;
        this.convertEntityToModelT = convertEntityToModelT;
        this.convertEntityToPojo = convertEntityToPojo;
    }

    @Override
    public boolean executeBase(ArchivoBaseDatosPojo archivo) {
        String model = archivo.getCapaPojo().getModelT().trim();
        String modelM = model.substring(0, 1).toUpperCase() + model.substring(1);
        archivo.getCapaPojo().setModelM(modelM);

        if (archivo.isMethoddefaultValue()) {
            archivo.getMethodManager().validDefault(archivo.isMethoddefaultValue());
        }

        if (archivo.getMethodManager().isMethodDelete()) {
            for (EntidadesPojo entidad : archivo.getEntidades()) {
                if (!entidad.getDelete()) {
                    entidad.deleteActive(true);
                }
            }
        }

        if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
            archivo.setEntidades(convertEntityToModelT.startConvertEntityToModelT(archivo));
//            archivo.setEntidades(convertEntityToPojo.startConvertEntityToPojo(archivo));
        }
        creador.setDatos(archivo);
        createArchivosBase.StartCreateArchivosBase(archivo, creador);

        if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
			return generateBase07(archivo);
        } else {
			return this.generateBasePojo(archivo);
        }
    }

	public boolean generateBasePojo(ArchivoBaseDatosPojo archivo){
		try {
			createClasesProyecto.StartCreateClasesProyecto(archivo, creador);
			createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
			return addProyectToZipFileAndSave();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
    public boolean generateBase07(ArchivoBaseDatosPojo archivo) {
        try {
            createClasesProyecto07.StartCreateClasesProyecto(archivo, creador);
            createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
            return addProyectToZipFileAndSave();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean addProyectToZipFileAndSave() {
        try {
            logger.info("Adding project to zip");
            anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(), creador.getProyectoName());
            logger.info("Saving Project, Clearing Cache");
            return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
