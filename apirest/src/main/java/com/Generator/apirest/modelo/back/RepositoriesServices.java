package com.Generator.apirest.modelo.back;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.RelacionPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//@Scope("singleton")
@Component
public class RepositoriesServices {

	
	private ArchivoBaseDatosPojo archivo;
	private String proyectoName;
	private String packageNames;
	private List<EntidadesPojo> entidades;
	private Creador creador;
	private String barra = java.nio.file.FileSystems.getDefault().getSeparator();
	
// private String description;
//	private int relantizar = SleepRelantizer.RELANTIZER;
//	private int relantizar2 = SleepRelantizer.RELANTIZERB;
	private AnotacionesJava anotacionesJava = new AnotacionesJava();

	protected static final Log logger = LogFactory.getLog(RepositoriesServices.class);

	public void startCreacion(ArchivoBaseDatosPojo archivo, Creador creadors) {

		this.entidades = archivo.getEntidades();
		this.proyectoName = archivo.getProyectoName();
		this.packageNames = archivo.getPackageNames();
		this.creador = creadors;
		this.archivo = archivo;
	//	this.barra = creador.getBarra();
		this.anotacionesJava.activateAnotacionesJava(archivo);

		try {
			this.create();
		} catch (InterruptedException e) {
			logger.error(" ERROR : " + e);
			// e.printStackTrace();
		}
	}

	private void create() throws InterruptedException {

		logger.info("inicia la creacion de la clase ");
		if (this.entidades.size() > 0) {
			for (EntidadesPojo entidad : this.entidades) {
			//	Thread.sleep(relantizar);
				if (entidad.getIsEntity()) {
					logger.info("Inicia la creacion de Repository ===>" + " Repository" + entidad.getNombreClase());
					this.createRepository(entidad);
					logger.info("Inicia la creacion de Servicio ===>" + " Repository" + entidad.getNombreClase());
				//	Thread.sleep(relantizar);
					this.createService(entidad);
				}
			}
		}
	}

	private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb)
			throws InterruptedException {

	//	Thread.sleep(relantizar);
		String nameFile = entidad_getNombreClase + ".java";
		// sb.append("}\r\n");
		String singleString = sb.toString();
		String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
				+ "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
				+ barra + entidad_paquete;
		creador.crearArchivo(direction, singleString, nameFile);
		logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
	}

	private void createRepository(EntidadesPojo entidad) throws InterruptedException {
		StringBuilder sb1 = new StringBuilder("\r\n");
		List<AtributoPojo> listAtributos = entidad.getAtributos();
		String nameOfClass = entidad.getNombreClase() + "Repository";
		logger.info("createRepository" + "  for Entity:  " + entidad.getNombreClase());
		String datoTipo = "";

		for (AtributoPojo atributoID : listAtributos) {
			if (atributoID.getsId()) {
				datoTipo = atributoID.getTipoDato();
			}
		}
		sb1.append(this.anotacionesJava.creatNotaClase() + "\r\n");
		sb1.append("package " + packageNames + ".repository;\r\n");
		sb1.append("\r\n");
		sb1.append("import java.util.List;");
		sb1.append("import java.util.Date;" + "\r\n");
		sb1.append("\r\n");
		sb1.append("import java.util.Optional;");
		sb1.append("\r\n");
		sb1.append("import org.springframework.data.repository.CrudRepository;");
		sb1.append("\r\n");
		// sb.append("import org.springframework.data.jpa.repository.JpaRepository;");

		sb1.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";");
		sb1.append("\r\n");
		sb1.append("\r\n");
		sb1.append("public interface " + nameOfClass + " extends CrudRepository< " + entidad.getNombreClase() + ", "
				+ datoTipo + "> {\r\n ");
		sb1.append("\r\n");

		for (AtributoPojo atributos : listAtributos) {
			String cadenaOriginal = atributos.getAtributoName();
			String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
			String restoDeLaCadena = cadenaOriginal.substring(1);
			String atributoName = primeraLetra + restoDeLaCadena;

			if (!atributos.getsId()) {
				sb1.append("		public Optional<" + entidad.getNombreClase() + "> findBy" + atributoName + "("
						+ atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
				sb1.append("\r\n");

				sb1.append("		public List<" + entidad.getNombreClase() + "> findBy" + atributoName + "Containing("
						+ atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
				sb1.append("\r\n");
			}
		}
		sb1.append("\r\n");
		sb1.append("}\r\n");
		sb1.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
	//	Thread.sleep(relantizar);
		this.createFileClass(nameOfClass, "repository", sb1);
	}

	private void createService(EntidadesPojo entidad) throws InterruptedException {

		StringBuilder sb2 = new StringBuilder("\r\n");
		String cadenaOriginal = "";
		String atributoName = "";
		String datoTipo = "";

		List<AtributoPojo> listAtributos = entidad.getAtributos();
		String nameOfClass = entidad.getNombreClase() + "Service";
		logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

		for (AtributoPojo atributoID : entidad.getAtributos()) {
			if (atributoID.getsId()) {
				datoTipo = atributoID.getTipoDato();
			}
		}
		sb2.append(this.anotacionesJava.creatNotaClase() + "\r\n");
		sb2.append("package " + packageNames + ".service ;\r\n"); // nombre del paquete hay
		sb2.append("\r\n");
		sb2.append("import java.util.Optional;");
		sb2.append("import java.util.Date;" + "\r\n");
		sb2.append("\r\n");
		sb2.append("import java.util.ArrayList;");
		sb2.append("\r\n");
		sb2.append("import java.util.List;");
		sb2.append("\r\n");
		sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";");

		for (RelacionPojo relacion : entidad.getRelaciones()) {
			sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion()
					+ ";" + "\r\n");
		}

		sb2.append("\r\n");
		sb2.append("\r\n");
		sb2.append("\r\n");
		sb2.append("public interface " + nameOfClass + "{\r\n ");
		sb2.append("\r\n");

		for (AtributoPojo atributos : listAtributos) {
			if (!atributos.getsId()) {
				cadenaOriginal = atributos.getAtributoName();
				String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
				String restoDeLaCadena = cadenaOriginal.substring(1);
				atributoName = primeraLetra + restoDeLaCadena;
				if (this.archivo.getMethodManager().isMethodFindByOrLoop()) {
					sb2.append("		public " + entidad.getNombreClase() + "  findBy" + atributoName + "("
							+ atributos.getTipoDato() + " " + atributos.getAtributoName() + ");" + "\r\n");
					sb2.append("\r\n");
				}
			}
		}

		for (AtributoPojo atributos : listAtributos) {
			if (!atributos.getsId()) {
				cadenaOriginal = atributos.getAtributoName();
				String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
				String restoDeLaCadena = cadenaOriginal.substring(1);
				atributoName = primeraLetra + restoDeLaCadena;
				if (this.archivo.getMethodManager().isMethodContaining()) {
					sb2.append("		public List<" + entidad.getNombreClase() + ">  findBy" + atributoName
							+ "Containing(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");"
							+ "\r\n");
					sb2.append("\r\n");
				}
			}
		}

		if (this.archivo.getMethodManager().isMethodfindById()) {
			sb2.append("		public " + entidad.getNombreClase() + " findById" + "(" + datoTipo + " id);" + "\r\n");
		}

		if (this.archivo.getMethodManager().isMetohdSave()) {
			sb2.append("		public boolean save" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " "
					+ entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
		}

		if (this.archivo.getMethodManager().isMethodgetAll()) {
			sb2.append("		public List<" + entidad.getNombreClase() + "> getAll" + entidad.getNombreClase() + "();"
					+ "\r\n");
		}

		if (entidad.getDelete()) {
			sb2.append("		public boolean delete" + entidad.getNombreClase() + "(" + datoTipo + " id);" + "\r\n");
		}

		if (this.archivo.getMethodManager().isMethodUpdate()) {
			sb2.append("		public boolean update" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " "
					+ entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
		}

		if (this.archivo.getMethodManager().isMethodsaveOrUpdate()) {
			sb2.append(" 		public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase()
					+ " " + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
		}
		sb2.append("\r\n");

		for (RelacionPojo relacion : entidad.getRelaciones()) {

			if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
				if (this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional()) {
					sb2.append("		public List<" + entidad.getNombreClase() + ">  findBy"
							+ relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " "
							+ relacion.getNameRelacion() + ");");
					sb2.append("\r\n");
				}
			} else {
				if (this.archivo.getMethodManager().isMethodContainingRelacion()) {
					sb2.append("		public List<" + entidad.getNombreClase() + ">  findByRelacion"
							+ relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion() + " "
							+ relacion.getNameClassRelacion().toLowerCase() + ");");
					sb2.append("\r\n");
				}
			}

		}

		sb2.append("}\r\n");
		sb2.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
	//	Thread.sleep(relantizar);
		this.createFileClass(nameOfClass, "service", sb2);
	}

	private String metodo(StringBuilder sb, String nameOfClass, String numeral) {
		sb.append("		if (fileOptional" + numeral + ".isPresent()) {" + "\r\n");
		sb.append("\r\n");
		sb.append("		try {" + "\r\n");
		sb.append("\r\n");
		sb.append("	logger.info(\"the proyect be updated\");" + "\r\n");
		sb.append("\r\n");
		sb.append("		" + nameOfClass + " proyectoBDA" + numeral + " = fileOptional.get();" + "\r\n");
		sb.append("\r\n");
		sb.append("		return proyectoBDA" + numeral + "; " + "\r\n");
		sb.append("		} catch (DataAccessException e) {  " + "\r\n");
		sb.append("		logger.error(\" ERROR : \" + e); " + "\r\n");
		sb.append("		}" + "\r\n");
		sb.append("  	}else { " + "\r\n");
		sb.append("		return new " + nameOfClass + "(); " + "\r\n");
		sb.append("		}" + "\r\n");
		sb.append("\r\n");
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private String metodTrycath(StringBuilder sb, String operacion, String operacionElse) {
		sb.append("		try {" + "\r\n");
		sb.append("\r\n");
		sb.append(operacion);
		sb.append("\r\n");
		sb.append("		} catch (DataAccessException e) {" + "\r\n");
		sb.append("		logger.error(\" ERROR : \" + e);" + "\r\n");
		sb.append(operacionElse);
		sb.append("\r\n");
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private String metodoGeneric(StringBuilder sb, String nameOfClass, String numeral, String operacion,
			String operacionElse) {

		sb.append("		if (fileOptional" + numeral + ".isPresent()) {" + "\r\n");
		sb.append("\r\n");
		sb.append("		try {" + "\r\n");
		sb.append("\r\n");
		sb.append("	logger.info(\"the proyect be updated\");" + "\r\n");
		sb.append("\r\n");
		sb.append("		" + nameOfClass + " proyectoBDA" + numeral + " = fileOptional" + numeral + ".get();" + "\r\n");
		sb.append("\r\n");
		sb.append(operacion);
		sb.append("		} catch (DataAccessException e) {  " + "\r\n");
		sb.append("		logger.error(\" ERROR : \" + e); " + "\r\n");
		sb.append("		}" + "\r\n");
		sb.append("  	}else { " + "\r\n");
		sb.append(operacionElse);
		sb.append("		}" + "\r\n");
		sb.append("\r\n");
		return sb.toString();
	}
}
