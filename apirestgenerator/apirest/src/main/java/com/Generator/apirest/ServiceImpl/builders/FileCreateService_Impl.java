package com.Generator.apirest.ServiceImpl.builders;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.services.builders.FileCreateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileCreateService_Impl extends Creador implements FileCreateService {

	protected static final Log logger = LogFactory.getLog(FileCreateService_Impl.class);

	public static String barra = java.nio.file.FileSystems.getDefault().getSeparator();
	public static String directorioTrabajo = System.getProperty("user.dir");
	public static String direccionDeCarpeta = directorioTrabajo + barra + "lib" + barra;

	private static int relantizar = 100;

	@Override
	public void crearArchivo(String direccion, String escrito, String nombreArchivo) {

		logger.info("Inicia CrearArchivo");

		String carpetas = direccion;
		String archivos = barra + nombreArchivo;
		String contenido1 = escrito;

		File create_carpeta = new File(carpetas);
		File create_archivo = new File(carpetas + archivos);

		if (create_archivo.exists()) {
			logger.info("THE File EXISTS");
			// JOptionPane.showMessageDialog(null, "el archivo existe");
		} else {
			logger.info("THE File DOES NOT EXIST IT WILL BE CREATED");
			// JOptionPane.showMessageDialog(null, "el archivo no existe pero se creara");
			create_carpeta.mkdirs();
			try {
				if (create_archivo.createNewFile()) {
					FileWriter fw = new FileWriter(create_archivo);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(contenido1);
					bw.close();
					logger.info("THE FILE WAS CREATED");
					// JOptionPane.showMessageDialog(null, "el archivo fue creado");
				} else {
					logger.info("THE FILE WAS NOT CREATED");
					// JOptionPane.showMessageDialog(null, "el archivo no fue creado");
				}
			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(Creador.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	
	
	@Override
	public  void createFileClassJava(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb, String directions) {
		try {
			Thread.sleep(relantizar);
			String nameFile = entidad_getNombreClase + ".java";
			String singleString = sb.toString();
			String direction = directions + entidad_paquete;
			//String direction = this.directionForJava() + entidad_paquete; 
			crearArchivo(direction, singleString, nameFile);
			logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void createFileClassJavaNoAddres(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb) {
		try {
			Thread.sleep(relantizar);
			String nameFile = entidad_getNombreClase + ".java";
			String singleString = sb.toString();
			String direction = this.directionForJava() + entidad_paquete; 
			crearArchivo(direction, singleString, nameFile);
			logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb, String directions, String extencion) {
		try {

			Thread.sleep(relantizar);
			String nameFile = entidad_getNombreClase + extencion;
			String singleString = sb.toString();
			String direction = directions + entidad_paquete;
			crearArchivo(direction, singleString, nameFile);
			logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
