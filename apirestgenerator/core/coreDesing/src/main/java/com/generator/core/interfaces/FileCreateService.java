package com.generator.core.interfaces;

public interface FileCreateService {

	public void crearArchivo(String direccion, String escrito, String nombreArchivo);
	public void createFileClassJava(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb, String directions);
	public void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb, String directions, String extencion);
	public void createFileClassJavaNoAddres(String entidad_getNombreClase, String entidad_paquete, StringBuffer sb);
}
