package com.Generator.apirest.mapper;


import com.Generator.apirest.ServiceImpl.mail.Mesend;
import com.Generator.apirest.entity.SalveProyect;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


// @Scope("singleton")
@Component
public class ProyectMapper {

	protected static final Log logger = LogFactory.getLog(ProyectMapper.class);
	
	public SalveProyect pojoToEntity(ArchivoBaseDatosPojo archivo) {
		logger.info("iniciando pojoToEntity()");
		SalveProyect proyect = new SalveProyect();
		Gson gson = new Gson();
		logger.info("serializando a ArchivoPojo");
        String g = new Gson().toJson(archivo);
		Mesend.jsoncuerpo = g;
        logger.info("Mapper to object Archivo");
		proyect.setAutor(archivo.getAutor());
		proyect.setUser(archivo.getUser());
		proyect.setProyectoName(archivo.getProyectoName());
		proyect.setPackageNames(archivo.getPackageNames());
		proyect.setDescription(archivo.getDescription());
		proyect.setEntidades(g);
		logger.info("Ending  pojoToEntity()");
		return proyect;
	}

	public ArchivoBaseDatosPojo entidadToPojo(SalveProyect proyect) {

		logger.info("serializando a ArchivoPojo");
		Gson gson = new Gson();
		logger.info("Mapper to object Archivo");
		return gson.fromJson(proyect.getEntidades(), ArchivoBaseDatosPojo.class);
	}
	
	
	public List< ArchivoBaseDatosPojo> entidadesToPojo(List<SalveProyect> proyect){
		List<ArchivoBaseDatosPojo> pojos = new ArrayList<ArchivoBaseDatosPojo>();
		for (SalveProyect salveProyect : proyect) {
			pojos.add(this.entidadToPojo(salveProyect));
		}
		return pojos;
	}
	

}
