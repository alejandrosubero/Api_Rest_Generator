package com.Generator.apirest.controller;

import com.Generator.apirest.ServiceImpl.ProyectoServiceImpl;
import com.Generator.apirest.entity.Proyecto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/apis")
public class Donwload {

	// /ApiREST/Generator/apis
	protected static final Log logger = LogFactory.getLog(Donwload.class);

	@Autowired
	private ProyectoServiceImpl proyectoServiceImpl;

	/*
	 * Download Files|2
	 */
	@GetMapping("/downloadLogFiles/{id}")
	public ResponseEntity<byte[]> getFiles(@PathVariable String id) {
		
		logger.info("inicia la ejecusion de getFiles recibe: " + id);
		try {
			Proyecto file = proyectoServiceImpl.findByName(id);
			logger.info("inicia la respuesta ResponseEntity.ok() " + file.getName());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getName()+".zip" + "\"").body(file.getPic());
		} catch (Exception e) {
			return ResponseEntity.status(404).body(null);
		}
	}


	@RequestMapping(value = "/downloadLog/{archivo}")
	public void getLogFile(HttpSession session, HttpServletResponse response, @PathVariable String archivo) {
		String fileName = archivo + ".zip";
		String filePathToBeServed = "C:\\Users\\crismarycastillo\\Desktop\\pruebas\\" + fileName; 

		try {
			File fileToDownload = new File(filePathToBeServed);
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

