package com.Generator.apirest.core;


import com.Generator.apirest.ServiceImpl.ProyectoServiceImpl;
import com.Generator.apirest.entity.Proyecto;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Component
public class AnadirCarpeta {

	protected static final Log logger = LogFactory.getLog(AnadirCarpeta.class);

	private boolean isEncryptFiles = false;
	private String password = "12345";


	@Autowired
	private ProyectoServiceImpl proyectoServiceImpl;


	public void folderzip(String carpetaAcomprimir, String direccionDeCarpeta, String nombreArchivoZip) throws Exception {
		logger.info("Inicia Metodo folderzip()");
		logger.info("inicia la recuperacion del paht");
		String folderpathZipInput = direccionDeCarpeta + carpetaAcomprimir; // carpeta a comprimir

		String pathOutputZip = direccionDeCarpeta + nombreArchivoZip + ".zip"; // ruta y nombre del zip generar
		logger.info("finaliza la recuperacion del paht en:  " + pathOutputZip);
		try {

			logger.info("inicia la creacion del archivo " + nombreArchivoZip + ".zip");
			ZipFile zipFile = new ZipFile(pathOutputZip);
			ZipParameters parameters = new ZipParameters();

			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

			// activa el encryptado del zip false = no encryptado, true = encryptado;
			parameters.setEncryptFiles(isEncryptFiles);

			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

			// setea el password del archivo
			logger.info("setea el password del archivo en = " + isEncryptFiles);
			parameters.setPassword(password);

			zipFile.addFolder(folderpathZipInput, parameters);
			
			// en la linea superior cambiar Folder por File si es archivo o carpeta

			logger.info("El archivo zip se creo");
			// JOptionPane.showMessageDialog(null, "El archivo zip se creo");

		} catch (ZipException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "El archivo zip no se creo " + e);
		}
	}

	// salva un proyecto en formato zip a una base de datos
	public Boolean salveProyecto(String direccionDeCarpeta, String nombreArchivoZip) {

		boolean salveInfo = false;
		logger.info("Inicia Metodo salveProyecto()");
		logger.info("inicia la ejecusion de salveProyecto");
		Proyecto proyecto = new Proyecto();
		logger.info("se instancia el objeto tipo entidad Proyecto");
		logger.info("inicia la recuperacion del path del proyecto +++++++");
		String pathOutputZip = direccionDeCarpeta + nombreArchivoZip + ".zip"; // ruta y nombre del zip generar
		logger.info("inicia la recuperacion del path del proyecto en:  " + pathOutputZip);
		proyecto.setName(nombreArchivoZip);
		proyecto.setMimetype("application/zip");
		logger.info("inicia la generacion de el array de bytes del proyecto");
		byte[] bic = readBytesFromFile(pathOutputZip);
		proyecto.setPic(bic);
		
		try {
		logger.info("inicia el salvado del proyecto salveProyecto");
		if (proyectoServiceImpl.saveProyecto(proyecto)) {
			logger.info("Proyecto salvado");	
			logger.info("inicia el borrado de la carpeta");
			borrarFolder(direccionDeCarpeta + nombreArchivoZip);
			logger.info("Carpeta base borrada");
			logger.info("inicia el borrado del archivo zip");
			borrarFolder(pathOutputZip);
			logger.info("Zip fue borrado");
			salveInfo = true;

			logger.info("Enviando Mail");
			//	mensendService.sendMailResponse();// ejecuta el envio del correo
			// envioEmailService.sendPreConfiguredMailR();
			logger.info("Mail enviando...");
		}

	} catch (Exception e) {
		logger.error("El proyecto no fue salvado");
		// salveInfo = false;
	}
	//	logger.info("inicia el salvado del proyecto salveProyecto");
	//	return proyectoServiceImpl.saveProyecto(proyecto);
	return salveInfo;
	}


	public static byte[] readBytesFromFile(String filePaths) {
		logger.info("Inicia Metodo readBytesFromFile()");
		logger.info("inicia la recuperacion del paht");
		String filePath = filePaths; // String pathOutputZip
		logger.info("finaliza la recuperacion del paht en: " + filePath);
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;

		try {
			logger.info("inicia la generacion de byte");
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			// read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
			logger.info("finaliza la generacion de byte");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Ourre error inesperado la generacion de byte" + e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("inicia envio de  [] byte");
		return bytesArray;
	}

	
	public void borrarFolder(String directorio) { // metodo que pasa in file para borrar el folder completo
		File f = new File(directorio);
		this.borrarcarpetas(f);
	}

	
	
	private void borrarcarpetas(File fileDel) {
		if (fileDel.isDirectory()) {
			if (fileDel.list().length == 0) {
				fileDel.delete();
			} else {
				for (String temp : fileDel.list()) {
						File fileDelete = new File(fileDel, temp);
						// recursive delete
						borrarcarpetas(fileDelete);
				}
				if (fileDel.list().length == 0) {fileDel.delete();}
			}
		} else {
			// if file, then delete it
			fileDel.delete();
		}
	}


	public boolean isEncryptFiles() {
		return isEncryptFiles;
	}

	public void setEncryptFiles(boolean isEncryptFiles) {
		this.isEncryptFiles = isEncryptFiles;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public ProyectoServiceImpl getProyectoServiceImpl() {
		return proyectoServiceImpl;
	}

	public void setProyectoServiceImpl(ProyectoServiceImpl proyectoServiceImpl) {
		this.proyectoServiceImpl = proyectoServiceImpl;
	}

}
