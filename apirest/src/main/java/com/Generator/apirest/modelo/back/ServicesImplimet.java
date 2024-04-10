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



@Component
public class ServicesImplimet {


	protected static final Log logger = LogFactory.getLog(RepositoriesServices.class);

	public void startCreacionImplement(ArchivoBaseDatosPojo archivo, Creador creadors) {
		try {
			this.crearImplemet(archivo, creadors);
		} catch (InterruptedException e) {
			logger.error(" ERROR : " + e);
		}
	}


	private void crearImplemet(ArchivoBaseDatosPojo archivo, Creador creadors) throws InterruptedException {
		for (EntidadesPojo entidad : archivo.getEntidades()) {
			if (entidad.getIsEntity()) {
				this.createServiceImpl(archivo,creadors, entidad);
			}
		}
	}

	
	private void createServiceImpl(ArchivoBaseDatosPojo archivo, Creador creadors, EntidadesPojo entidad) throws InterruptedException {

		StringBuilder sbh = new StringBuilder("\r\n");
	
		// String entidadNombre = entidad.getNombreClase();
		String nameOfClass = entidad.getNombreClase() + "ServiceImplement";
		String repositorieName = entidad.getNombreClase() + "Repository";
		String repositorieNameOjecte = repositorieName.toLowerCase();
		String serviceName = entidad.getNombreClase() + "Service";

		sbh.append(new AnotacionesJava(archivo).creatNotaClase().toString() + "\r\n");
		
		sbh.append(this.createImport(archivo.getPackageNames(), serviceName, repositorieName ,entidad));	
		sbh.append(this.createTitulo(nameOfClass, serviceName, repositorieName,  repositorieNameOjecte));
		
		if(archivo.getMethodManager().isMethodFindByOrLoop()) {
		sbh.append(this.crearMetodoloop(entidad, repositorieNameOjecte));
		}
		
		sbh.append(this.metods(archivo, entidad, repositorieNameOjecte, entidad.getNombreClase()));
		sbh.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
	
		this.createFileClass(nameOfClass, "serviceImplement", sbh, creadors, archivo.getProyectoName());
	}
	
	
	private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb, Creador creador, String proyectoName ) throws InterruptedException {
	
		
		String barra = java.nio.file.FileSystems.getDefault().getSeparator();
		sb.append("}\r\n");
		String singleString = sb.toString();
		String direction = creador.getDireccionDeCarpeta() + proyectoName + barra 
												+ "src" + barra + "main" + barra
												+ "java" + barra + creador.getCom() 
												+ barra + creador.getPackageNames1() 
												+ barra + creador.getArtifact()
												+ barra + entidad_paquete;
		
		creador.crearArchivo(direction, singleString, entidad_getNombreClase + ".java");
		//logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
		
	}
	
	
	
	
	
	private StringBuilder metods(ArchivoBaseDatosPojo archivo, EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {

		StringBuilder sb = new StringBuilder("\r\n");
	//	logger.info("**createServiceImplement  metodos  for Entity:  " + entidad.getNombreClase()+"**");
		sb.append("\r\n");
		
		
		if(archivo.getMethodManager().isMethodgetAll()) 
		sb.append(metodgetAll(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMetohdSave())
		sb.append(this.metodSave(entidad, repositorieNameOjecte, entidadNombre));
		
		
		sb.append("\r\n");
		if(archivo.getMethodManager().isMethodDelete())
		sb.append(this.metodDelete(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMethodUpdate())
		sb.append(this.metodUpdate(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMethodfindById())
		sb.append(this.metodfindById(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
	
		if(archivo.getMethodManager().isMethodsaveOrUpdate())
		sb.append(this.metodsaveOrUpdate(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMethodContaining())
		sb.append(this.metodContaining(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMethodContainingRelacion())
		sb.append(this.ContainingRelacion(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		
		if(archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional())
		sb.append(this.ContainingRelacionNoBiDirectional(entidad, repositorieNameOjecte, entidadNombre));
		sb.append("\r\n");
		return sb;
	}
	
	

	private String idTipoDato(EntidadesPojo entidad) {
		List<AtributoPojo> listAtributos = entidad.getAtributos();
		String datoTipo = "Integer";
		for (AtributoPojo atributoID : listAtributos) {
			if (atributoID.getsId()) {
				datoTipo = atributoID.getTipoDato();
			}
		}
		return datoTipo;
	}


	private String metodTrycath(String operacion, String operacionElse) {
		StringBuilder sb2 = new StringBuilder("\r\n");
		sb2.append("				try {" + "\r\n");
		sb2.append(operacion);
		sb2.append("				} catch (DataAccessException e) {" + "\r\n");
		sb2.append("				logger.error(\" ERROR : \" + e);" + "\r\n");
		sb2.append(operacionElse);
		sb2.append("				}\r\n");
		return sb2.toString();
	}


	public StringBuilder createImport(String packageNames, String serviceName, String repositorieName, EntidadesPojo entidad) {
		StringBuilder sb = new StringBuilder("\r\n");
		// logger.info("createServiceImplement" + " paso 2 import for Entity:  " + entidad.getNombreClase());
		
		sb.append("package " + packageNames + ".serviceImplement ;\r\n");
		sb.append("\r\n");
		sb.append("import " + packageNames + ".service." + serviceName + ";\r\n");
		sb.append("import " + packageNames + ".repository." + repositorieName + ";\r\n");
		sb.append("import java.util.Optional;" + "\r\n");
		sb.append("import java.util.ArrayList;" + "\r\n");
		sb.append("import java.util.List;" + "\r\n");
		sb.append("import java.util.Date;"+"\r\n");
		sb.append("import org.apache.commons.logging.Log;" + "\r\n");
		sb.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
		sb.append("import org.springframework.dao.DataAccessException;" + "\r\n");
		sb.append("import org.springframework.stereotype.Service;" + "\r\n");
		sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";" + "\r\n");

		for (RelacionPojo relacion : entidad.getRelaciones()) {
			sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
		}
		sb.append("\r\n");
		return sb;
	}


	private StringBuilder createTitulo(String nameOfClass, String serviceName, String repositorieName, String repositorieNameOjecte) {

		StringBuilder sb1 = new StringBuilder("\r\n");
		// logger.info("createServiceImplement" + " paso 3 clase titulo  for Entity:  " + nameOfClass);
		sb1.append("\r\n");
		sb1.append("\r\n");
		sb1.append("@Service" + "\r\n");
		sb1.append("public class " + nameOfClass + " implements " + serviceName + " {" + "\r\n");
		sb1.append("\r\n");
		sb1.append("protected static final Log logger = LogFactory.getLog(" + nameOfClass + ".class);");
		sb1.append("\r\n");
		sb1.append("@Autowired" + "\r\n");
		sb1.append("private " + repositorieName + " " + repositorieNameOjecte + ";" + "\r\n");
		return sb1;
	}


	private StringBuilder crearMetodoloop(EntidadesPojo entidad, String repositorieNameOjecte) {

		StringBuilder sbp = new StringBuilder("\r\n");
		List<AtributoPojo> listAtributos = entidad.getAtributos();

		for (AtributoPojo atributos : listAtributos) {
			int cont = 1;
			if (!atributos.getsId()) {
				// logger.info("createServiceImplement" + " PASO ==> 4 metodo en loop  for Entity:  " + entidad.getNombreClase() + " CUENTAS = " + cont);
				String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
				sbp.append("		@Override" + "\r\n");
				sbp.append("		public " + entidad.getNombreClase() + " findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + "){" + "\r\n");
				sbp.append("\r\n");
				sbp.append("		logger.info(\"Starting get" + entidad.getNombreClase() + "\");"+"\r\n");
				sbp.append("			" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "Entity = new " + entidad.getNombreClase() + "();"+"\r\n");
				String numeraly = String.valueOf(cont);
				sbp.append("		Optional<" + entidad.getNombreClase() + "> fileOptional" + numeraly + " = " + repositorieNameOjecte + ".findBy" + atributoName + "(" + atributos.getAtributoName() + ");"+"\r\n");
				sbp.append("\r\n");
				sbp.append("		if (fileOptional" + numeraly + ".isPresent()) { "+"\r\n");
				String operacionu = "			" + entidad.getNombreClase().toLowerCase() + "Entity = fileOptional" + numeraly + ".get();" + "\r\n";
				String operacionElseu = "\r\n";
				sbp.append(this.metodTrycath(operacionu, operacionElseu));
				sbp.append("		}"+"\r\n");
				sbp.append("		return " + entidad.getNombreClase().toLowerCase() + "Entity;");
				sbp.append("	}"+"\r\n");
				cont += 1;
			}
		}
		return sbp;
	}


	


	private StringBuilder metodgetAll(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {

		StringBuilder ty = new StringBuilder("\r\n");
		String getNombreClase = entidad.getNombreClase();
		ty.append("\r\n");
		ty.append("		@Override" + "\r\n");
		ty.append("		public List<" + getNombreClase + "> getAll" + getNombreClase + "(){");
		ty.append("\r\n");
		ty.append("		logger.info(\"Get allProyect\");" + "\r\n");
		ty.append("			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
		ty.append("				" + repositorieNameOjecte + ".findAll().forEach(" + getNombreClase.toLowerCase() + " -> lista" + getNombreClase
				+ ".add(" + getNombreClase.toLowerCase() + "));" + "\r\n");
		ty.append("			return lista" + getNombreClase + ";" + "\r\n");
		ty.append("}" + "\r\n");
		return ty;
	}


	private StringBuilder metodSave(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {

		StringBuilder sbg = new StringBuilder("\r\n");
		sbg.append("		@Override" + "\r\n");
		sbg.append("		public boolean save" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "){"+ "\r\n");
		sbg.append("		logger.info(\"Save Proyect\");" + "\r\n");
		sbg.append("\r\n");
		String operacion = "				" + repositorieNameOjecte + ".save(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n" + "				return true;\r\n";
		String operacionElse = "				return false;\r\n";
		sbg.append(this.metodTrycath(operacion, operacionElse));
		sbg.append("		}\r\n");
		return sbg;
	}


	private StringBuilder metodDelete(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sbt = new StringBuilder("\r\n");
		if (entidad.getDelete()) {
			sbt.append("		@Override" + "\r\n");
			sbt.append("		public boolean delete" + entidad.getNombreClase() + "( " + this.idTipoDato(entidad) + " id){" + "\r\n");
			sbt.append("		logger.info(\"Delete Proyect\");" + "\r\n");
			sbt.append("		boolean clave = false;\r\n");
			sbt.append("\r\n");
			String operacionA = "				" + repositorieNameOjecte + ".deleteById(id);" + "\r\n" + "				clave = true;\r\n";
			String operacionElseA = "				clave = false;\r\n";
			sbt.append(this.metodTrycath(operacionA, operacionElseA));
			sbt.append("		return clave;\r\n");
			sbt.append("	}\r\n");
		}
		return sbt;
	}


	private StringBuilder metodUpdate(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sf = new StringBuilder("\r\n");
		sf.append("\r\n");
		sf.append("		@Override" + "\r\n");
		sf.append("		public boolean update" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
		sf.append("			logger.info(\"Update Proyect\");" + "\r\n");
		sf.append("			boolean clave = false;" + "\r\n");
		sf.append("		" + entidad.getNombreClase() + " empre = findById(" + entidad.getNombreClase().toLowerCase() + ".getId());" + "\r\n");
		sf.append("			empre = " + entidad.getNombreClase().toLowerCase() + ";" + "\r\n");
		String operacionc = "				" + repositorieNameOjecte + ".save(empre);" + "\r\n" + 	"						clave = true;" + "\r\n";
		String operacionElsec = "				clave = false;" + "\r\n";
		sf.append(this.metodTrycath(operacionc, operacionElsec));
		sf.append("\r\n");
		sf.append("					return clave;" + "\r\n");
		sf.append("	}\r\n");
		return sf;
	}

	private StringBuilder metodfindById(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sf = new StringBuilder("\r\n");
		sf.append("\r\n");
		sf.append("		@Override" + "\r\n");
		sf.append("		public " + entidad.getNombreClase() + " findById( " + this.idTipoDato(entidad) + " id){" + "\r\n");
		sf.append("				return  " + repositorieNameOjecte + ".findById(id).get();" + "\r\n");
		sf.append("		}" + "\r\n");
		return sf;
	}


	private StringBuilder metodsaveOrUpdate(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sf = new StringBuilder("\r\n");
		sf.append("\r\n");
		sf.append("		@Override" + "\r\n");
		sf.append("		public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
		//sf.append("\r\n");
		sf.append("			logger.info(\"Update Proyect\");" + "\r\n");
		// sf.append("\r\n");
		sf.append("			boolean clave = false;\r\n");
		// sf.append("			logger.info(\"Starting getEmpresa\");" + "\r\n");
		String numeraly = String.valueOf(2);
		sf.append("			Optional<" + entidad.getNombreClase() + "> fileOptional" + numeraly + " = " + repositorieNameOjecte
				+ ".findById(" + entidad.getNombreClase().toLowerCase() + ".getId());" + "\r\n");
		sf.append("			if (fileOptional" + numeraly + ".isPresent()) { " + "\r\n");
		sf.append("				clave = this.update" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
		sf.append("				logger.info(\" is update\");" + "\r\n");
		sf.append("			} else {" + "\r\n");
		sf.append("					clave = this.save" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
		sf.append("					logger.info(\" is save\");" + "\r\n");
		sf.append(" 				}" + "\r\n");
		sf.append(" 		return clave;" + "\r\n");
		sf.append("		}" + "\r\n");
		return sf;
	}


	private StringBuilder ContainingRelacion(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sbx = new StringBuilder();
		//String getNombreClase = entidadNombre;
	//	String getNombre = entidad.getNombreClase().toLowerCase();
		logger.info("  " + entidad.getNombreClase());
		for (RelacionPojo relacion : entidad.getRelaciones()) {
			if (relacion.getBidireccional()) {
				if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
					this.Relacionx(entidad, entidadNombre, relacion);
			}
			}
		}
		return sbx;
	}

	private StringBuilder Relacionx(EntidadesPojo entidad,  String entidadNombre, RelacionPojo relacion) {
		StringBuilder sbw = new StringBuilder();
		String getNombreClase = entidadNombre;
		String getNombre = entidad.getNombreClase().toLowerCase();
		logger.info("  " + entidad.getNombreClase());
			sbw.append("\r\n");
			sbw.append("\r\n");
			sbw.append("		@Override" + "\r\n");
			sbw.append("		public List<" + getNombreClase + "> findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "){" + "\r\n");
			 sbw.append("				logger.info(\"metodo: metodContainingRelacion NEW \");" + "\r\n");// comentarriooooooooooooooooooooooooooooo
			sbw.append("			logger.info(\"Get allProyect\");" + "\r\n");
			sbw.append(" 			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
			sbw.append("			for (" + getNombreClase + " " + getNombre + " : this.getAll" + getNombreClase + "()) {" + "\r\n");
			sbw.append("			for (" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "x : "
																			+ getNombre + ".get" + relacion.getNameRelacion() + "()) { " + "\r\n");
			sbw.append("				if(" + getNombreClase + ".get" + relacion.getNameRelacion() + "().contains(" + relacion.getNameRelacion() + ".get" + relacion.getNameRelacion() + "())) {	" + "\r\n");
			sbw.append("					lista" + getNombreClase + ".add(" + getNombre + "x);	" + "\r\n");
			sbw.append("				}" + "\r\n");
			sbw.append("	  	 	}" + "\r\n");
			sbw.append("		}" + "\r\n");
			sbw.append("					return lista" + getNombreClase + ";	" + "\r\n");
			sbw.append("\r\n");
			sbw.append("	}" + "\r\n");
		return sbw;
	}

	private StringBuilder ContainingRelacionNoBiDirectional(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sv = new StringBuilder();
		for (RelacionPojo relacion: entidad.getRelaciones()) {
				if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
					sv.append("\r\n");
					sv.append("			@Override" + "\r\n");
					sv.append("			public List<" + entidad.getNombreClase() + "> findByRelacion" + relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion() + " " + relacion.getNameClassRelacion().toLowerCase() + "){" + "\r\n");
					sv.append("				List<" + entidad.getNombreClase() + "> lista" + entidad.getNombreClase() + " = new ArrayList<" + entidad.getNombreClase() + ">();" + "\r\n");
					sv.append("				for (" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + " : this.getAll" + entidad.getNombreClase() + "()) {" + "\r\n");
					sv.append("					if(" + entidad.getNombreClase().toLowerCase() + ".get" + relacion.getNameRelacion() + "().equals" + relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion().toLowerCase() + ")){" + "\r\n");
					sv.append("						lista" + entidad.getNombreClase() + ".add(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
					sv.append("					}" + "\r\n");
					sv.append("				}" + "\r\n");
					sv.append("				return lista" + entidad.getNombreClase() + ";" + "\r\n");
					sv.append("			}" + "\r\n");
				}

			if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
					String getNombreClase = entidad.getNombreClase();
					String getNombre = entidad.getNombreClase().toLowerCase();
					sv.append("\r\n");
					sv.append("\r\n");
					sv.append("		@Override" + "\r\n");
					sv.append("		public List<" + getNombreClase + "> findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "){" + "\r\n");
					sv.append("			logger.info(\"metodo: metodContainingRelacion NEW \");" + "\r\n");
					sv.append("			logger.info(\"Get allProyect\");" + "\r\n");
					sv.append(" 			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
					sv.append("			for (" + getNombreClase + " " + getNombre + " : this.getAll" + getNombreClase + "()) {" + "\r\n");
					sv.append("				for (" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "x : "
																+ getNombre + ".get" + relacion.getNameRelacion() + "()) { " + "\r\n");
					// sv.append("					if(" + getNombreClase + ".get" + relacion.getNameRelacion() + "().contains(" + relacion.getNameRelacion() + ".get" + relacion.getNameRelacion() + "())) {	" + "\r\n");
					sv.append("						if("+relacion.getNameRelacion()+"x.equals"+relacion.getNameClassRelacion()+"(" + relacion.getNameRelacion() + ")) {"+ "\r\n");
					sv.append("						lista" + getNombreClase + ".add(" + getNombre + ");	" + "\r\n");
					sv.append("				}" + "\r\n");
					sv.append("	  	 	}" + "\r\n");
					sv.append("		}" + "\r\n");
					sv.append("			return lista" + getNombreClase + ";	" + "\r\n");
					sv.append("\r\n");
					sv.append("	}" + "\r\n");
				}
			}
		return sv;
	}



	 private StringBuilder metodContaining(EntidadesPojo entidad, String repositorieNameOjecte, String entidadNombre) {
		StringBuilder sbx = new StringBuilder();
	//	String getNombreClase = entidadNombre;
	//	String getNombre = entidad.getNombreClase().toLowerCase();
	//	List<AtributoPojo> listAtributos = entidad.getAtributos();

		for (AtributoPojo atributo :  entidad.getAtributos()) {
			//String cadenaOriginal = atributo.getAtributoName();
			//String primeraLetra = atributo.getAtributoName().substring(0, 1).toUpperCase();
			// String restoDeLaCadena = atributo.getAtributoName().substring(1);
			String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);

			if (!atributo.getsId()) {
				sbx.append("\r\n");
				sbx.append("		@Override" + "\r\n");
				sbx.append("		public List<" + entidadNombre + "> findBy"+atributoName+"Containing(" + atributo.getTipoDato()+ " " + atributoName.toLowerCase()+"){" + "\r\n");
				sbx.append("			logger.info(\"Get allProyect\");" + "\r\n");
				sbx.append(" 			List<"+entidadNombre+"> lista"+entidadNombre+" = new ArrayList<"+entidadNombre+">();"+ "\r\n");
				sbx.append("			lista"+entidadNombre+" = "+repositorieNameOjecte+".findBy"+atributoName +"Containing("+atributoName.toLowerCase()+");"+ "\r\n");
				sbx.append("  			return lista"+entidadNombre+";"+ "\r\n");
				sbx.append("		}" + "\r\n");
			}
		}
		return sbx;
	}






	

}


