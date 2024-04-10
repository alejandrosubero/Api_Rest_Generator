package com.Generator.apirest.ServiceImpl.builders;


import com.Generator.apirest.ServiceImpl.capas.CreateArchivosBase;
import com.Generator.apirest.ServiceImpl.capas.CreateCapaPojoForEntitys;
import com.Generator.apirest.ServiceImpl.capas.CreateClasesProyecto;
import com.Generator.apirest.ServiceImpl.capas.CreateClasesProyecto07;
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

	@Autowired
	private ConvertEntityToPojo convertEntityToPojo;

	@Autowired
	private Creador creador;

	@Autowired
	private CreateArchivosBase createArchivosBase;

	@Autowired
	private CreateClasesProyecto createClasesProyecto;

	@Autowired
	private CreateCapaPojoForEntitys createCapaPojoForEntitys;

	@Autowired
	private AnadirCarpeta anadirCarpeta;

	@Autowired
	private CreateClasesProyecto07 createClasesProyecto07;

	

	@Override
	public boolean ejecutaBase(ArchivoBaseDatosPojo archivo) {

		
		if(archivo.isMethoddefaultValue()) {
			archivo.getMethodManager().validDefault(archivo.isMethoddefaultValue());
		}
		
		
		if (archivo.getMethodManager().isMethodDelete()) {		
			for (EntidadesPojo entidad : archivo.getEntidades()) {
				if (!entidad.getDelete()) { entidad.deleteActive(true); }
			}
		}
		

		if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
			return this.generarBase07(archivo);
		} else {

			try {
				// Boolean isToolGetPost = archivo.getToolClassPojo().getGetPostCreateTool();

				if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
					archivo.setEntidades(convertEntityToPojo.startConvertEntityToPojo(archivo));
				}
				creador.setDatos(archivo);
				createArchivosBase.StartCreateArchivosBase(archivo, creador);
				createClasesProyecto.StartCreateClasesProyecto(archivo, creador);
				createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
				logger.info("Añadiendo proyecto a zip");
				anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(),creador.getProyectoName());
				logger.info("Finalizo el Añadido del proyecto a zip");
				logger.info("Guardando Proyecto, Limpiando Cahe");
				return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
	}

	@Override
	public boolean generarBase07(ArchivoBaseDatosPojo archivo) {
		try {
//            Boolean isToolGetPost = archivo.getToolClassPojo().getGetPostCreateTool();
			if (archivo.getCapaPojo().getCreateCapaPojoForEntitys()) {
				archivo.setEntidades(convertEntityToPojo.startConvertEntityToPojo(archivo));
			}
			creador.setDatos(archivo);
			createArchivosBase.StartCreateArchivosBase(archivo, creador);
			createClasesProyecto07.StartCreateClasesProyecto(archivo, creador);
			createCapaPojoForEntitys.StartCreateCapaPojoForEntitys(archivo, creador);
			logger.info("Añadiendo proyecto a zip");
			anadirCarpeta.folderzip(creador.getProyectoName(), creador.getDireccionDeCarpeta(),
					creador.getProyectoName());
			logger.info("Finalizo el Añadido del proyecto a zip");
			logger.info("Guardando Proyecto, Limpiando Cahe");
			return anadirCarpeta.salveProyecto(creador.getDireccionDeCarpeta(), creador.getProyectoName());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
