package com.Generator.apirest.controller;

import com.Generator.apirest.ServiceImpl.ServiceValidation;
import com.Generator.apirest.ServiceImpl.mail.Mesend;
import com.Generator.apirest.mapper.ProyectMapper;
import com.Generator.apirest.pojos.mail.ContactRecibeMail;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.SalveProyectService;
import com.Generator.apirest.services.builders.ServicioGenerarproyectoRest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ControllerAna {

	// /ApiREST/Generator/api

	private  static final Log logger = LogFactory.getLog(ControllerAna.class);

	@Autowired
	private SalveProyectService salveProyectService;

	@Autowired
	private ProyectMapper mapper;

	@Autowired
	private ServicioGenerarproyectoRest servicioGenerarproyectoRest;

	@Autowired
	private Mesend mesend;

	@Autowired
	private ServiceValidation serviceValid;

	
	
	@GetMapping("/check")
	public String buenosDias() {
		logger.info("Loading the test pages");
		return "<h1>--------------"
				+ "The Sevice is Run"
		+"the document  Swagger is in link: ==>  "
		+ "http://localhost:8987/ANACODE/swagger-ui.html"
		+"the document  h2 is in link: ==>"
		+ " http://localhost:8987/ANACODE/h2-console"
				+ "----------</h1>";
	}


	@PostMapping("/archivosBase")
	public boolean createBaseApp(@RequestBody ArchivoBaseDatosPojo archivo) throws Exception {
		logger.info("check data in object.");
		if (archivo != null) {
			logger.info("inica la accion del proyecto: " + archivo.getProyectoName());
			serviceValid.validateSaveProyecte(archivo);
			return servicioGenerarproyectoRest.executeBase(archivo);
		}
		return false;
	}

	@PostMapping("/contactMe")
	public boolean contactaMeAMi(@RequestBody ContactRecibeMail contactMe) throws Exception {
		logger.info("Rescive contacto");
		return mesend.sendMailContacto(contactMe.getAsunto(), contactMe.getMensage());
	}



}

