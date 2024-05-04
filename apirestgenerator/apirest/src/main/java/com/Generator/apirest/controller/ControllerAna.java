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

//	@GetMapping("/getProyectoU/{user}")
//	private ResponseEntity<List<ArchivoBaseDatosPojo>> findByUser(@PathVariable("user") String user) {
//		try {
//			List<ArchivoBaseDatosPojo> archivos = mapper.entidadesToPojo(salveProyectService.findByUser(user));
//			return new ResponseEntity<List<ArchivoBaseDatosPojo>>(archivos, HttpStatus.OK);
//		} catch (DataAccessException e) {
//			List<ArchivoBaseDatosPojo> archivos = null;
//			return new ResponseEntity<List<ArchivoBaseDatosPojo>>(archivos, HttpStatus.BAD_REQUEST);
//		}
//	}

//	@GetMapping("/getProyectoA/{autor}")
//	private ResponseEntity<List<ArchivoBaseDatosPojo>> findBynombreProyectoAutor(@PathVariable("autor") String autor) {
//		try {
//			List<ArchivoBaseDatosPojo> archivos = mapper.entidadesToPojo(salveProyectService.findByAutor(autor));
//			return new ResponseEntity<List<ArchivoBaseDatosPojo>>(archivos, HttpStatus.OK);
//		} catch (DataAccessException e) {
//			List<ArchivoBaseDatosPojo> archivos = null;
//			return new ResponseEntity<List<ArchivoBaseDatosPojo>>(archivos, HttpStatus.BAD_REQUEST);
//		}
//	}

//	@GetMapping("/getProyectoN/{nombre}")
//	private ResponseEntity<ArchivoBaseDatosPojo> findBynombreProyectoNombre(@PathVariable("nombre") String nombre) {
//		try {
//			ArchivoBaseDatosPojo archivo = mapper.entidadToPojo(salveProyectService.findByProyectoName(nombre));
//			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.OK);
//		} catch (DataAccessException e) {
//			ArchivoBaseDatosPojo archivo = null;
//			return new ResponseEntity<ArchivoBaseDatosPojo>(archivo, HttpStatus.BAD_REQUEST);
//		}
//	}

//	@GetMapping(value = "/countProyect/{name}", produces = "application/json")
//	private ResponseEntity<Long> countProyect(@PathVariable("name") String name) {
//		try {
//			Long count = salveProyectService.countProyect(name);
//			return new ResponseEntity<Long>(count, HttpStatus.OK);
//		} catch (DataAccessException e) {
//			Long error = null;
//			return new ResponseEntity<Long>(error, HttpStatus.BAD_REQUEST);
//		}
//	}

}

