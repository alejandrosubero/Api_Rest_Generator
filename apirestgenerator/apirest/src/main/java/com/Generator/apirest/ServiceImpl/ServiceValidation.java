package com.Generator.apirest.ServiceImpl;


import com.Generator.apirest.mapper.ProyectMapper;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.SalveProyectService;
import com.Generator.apirest.services.ValidateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceValidation implements ValidateService {

	private static final Log logger = LogFactory.getLog(ServiceValidation.class);
	
	@Autowired
	private SalveProyectService salveProyectService;
	
	@Autowired
	private ProyectMapper mapper;
	
	
	private String clave = "zz2";

	private boolean checkSave(String valor) {
		// TODO: conectar esta clave con el token o con password.
		logger.info("*** Valida la clave ***");
		boolean check = valor != null && valor.equals(this.clave) ? true : false;
		return check;
	}

	@Override
	public boolean validateSaveProyecte(ArchivoBaseDatosPojo archivo) {
		boolean check = this.checkSave(archivo.getCapaPojo().getSavePojo());
		if(check) {
			salveProyectService.saveProyectInternamente(mapper.pojoToEntity(archivo));
			logger.info("*** SAVE THE PROYECT ***");
		}else {
			logger.info("***NO SAVE THE PROYECT ***");
		}
		return check;
	}


	
	
	public <T> Object valid(T t) {
		try {
			if (t != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public <T> Object validObjecte(T t) {
		T elemento = null;
		try {
			if (t != null) {
				elemento = t;
			}
			return elemento;
		} catch (Exception e) {
			e.printStackTrace();
			return elemento;
		}
	}


}
