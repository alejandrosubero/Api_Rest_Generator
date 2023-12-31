package com.Generator.apirest.modelo.back;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;



@Scope("singleton")
@Component
public class OsEjecutandose {

	private static String barra = java.nio.file.FileSystems.getDefault().getSeparator();
	private static String sDirectorioTrabajo = System.getProperty("user.dir");
	private static String direccionDeCarpeta = sDirectorioTrabajo + barra+ "lib"+ barra;

	protected static final Log logger = LogFactory.getLog(OsEjecutandose.class);
	
	public String datos_pc() {
		String usar = "";
		try {
			// sacar ip
			String hostName = InetAddress.getLocalHost().getHostAddress();
			logger.info("hostName: " + hostName);
			// nombre
			InetAddress addr = InetAddress.getByName(hostName);
			String hostname = addr.getHostName();
			logger.info("hostname: " + hostname);
			// Sistema OPerativo
			String so = System.getProperty("os.name");
			logger.info("os name: " + so);
			if (so.equals("Windows 10")) {
				usar = "\\";
				logger.info("Sistema operativo es windows usa = " + usar);
			}else {
				usar = "//";
				logger.info("Sistema operativo es "+ so +" usa = "+ usar);
			}
			return usar;
		} catch (UnknownHostException e) {
			logger.error("NO SE EJECUTOEL SCRIP EL ERROR: " + e);
			return "NO SE EJECUTOEL SCRIP EL ERROR: " + e;
		}
	}
}
