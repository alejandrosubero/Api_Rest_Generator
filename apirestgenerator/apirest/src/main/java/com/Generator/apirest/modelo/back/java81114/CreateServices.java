package com.Generator.apirest.modelo.back.java81114;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.core.build.MethodDesign;
import com.Generator.apirest.core.build.Modifier;
import com.Generator.apirest.core.build.RetunsType;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Scope("singleton")
@Component
public class CreateServices implements IImportModel {

	protected static final Log logger = LogFactory.getLog(CreateServices.class);

	private ArchivoBaseDatosPojo archivo;
	private String proyectoName;
	private String packageNames;
	private List<EntityPojo> entidades;
	private Creador creador;
	private String barra = java.nio.file.FileSystems.getDefault().getSeparator();


	@Autowired
	private AnotacionesJava anotacionesJava;


	public void initCreate(ArchivoBaseDatosPojo archivo, Creador creadors) {

		this.entidades = archivo.getEntidades();
		this.proyectoName = archivo.getProyectoName();
		this.packageNames = archivo.getPackageNames();
		this.creador = creadors;
		this.archivo = archivo;
		this.anotacionesJava.activateAnotacionesJava(archivo);
		try {
			this.create();
		} catch (InterruptedException e) {
			logger.error(" ERROR : " + e);
			 e.printStackTrace();
		}
	}

	private void create() throws InterruptedException {

        logger.info("inicia la creacion de la clase ");
		if (this.entidades != null && this.entidades.size() > 0) {
			this.entidades.stream().forEach(entityPojo -> {
                try {
					logger.info("Inicia la creacion de Servicio " + entityPojo.getNombreClase());
					if(entityPojo.getIsEntity()){
						this.createService(entityPojo);
					}
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
		}
	}



	private void createService(EntityPojo entidad) throws InterruptedException {

		StringBuilder sb2 = new StringBuilder("\r\n");
		String cadenaOriginal = "";
		String atributoName = "";
		String datoTipo = "";
		List<AttributePojo> listAtributos = entidad.getAtributos();
		String nameOfClass = entidad.getNombreClase() + "Service";

		String returnObjectClass =
				this.archivo.getCapaPojo().getCreateCapaPojoForEntitys()?
						stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM()))
						: entidad.getNombreClase();

		String returnObjectClassPackage = this.archivo.getCapaPojo().getCreateCapaPojoForEntitys()? this.archivo.getCapaPojo().getModelT() : "entitys";

		logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

		for (AttributePojo atributoID : entidad.getAtributos()) {
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
		sb2.append("import " + packageNames + "." +returnObjectClassPackage + "." + returnObjectClass + ";");

		for (RelationshipPojo relacion : entidad.getRelaciones()) {
			sb2.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
		}

		sb2.append("\r\n");
		sb2.append("\r\n");
		sb2.append("\r\n");
		sb2.append("public interface " + nameOfClass + "{\r\n ");
		sb2.append("\r\n");

		for (AttributePojo atributos : listAtributos) {
			if (!atributos.getsId()) {
				atributoName = this.capitalizeOrUncapitalisedFirstLetter(atributos.getAtributoName(),'u');
				if (this.archivo.getMethodManager().isMethodFindByOrLoop()) {
//					String returnsClass ="";
//					if(archivo.getCapaPojo().getCreateCapaPojoForEntitys()){
//						returnsClass = stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM()));
//					}else{
//						returnsClass = entidad.getNombreClase();
//					}

//					String returnsClass = archivo.getCapaPojo().getCreateCapaPojoForEntitys()?
//							stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM())):
//							entidad.getNombreClase();

					sb2.append( MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.none)
							.returnsClass(
									archivo.getCapaPojo().getCreateCapaPojoForEntitys()?
											stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM())):
											entidad.getNombreClase()
							)
							.methodName(stringEnsamble(List.of("findBy",atributoName)))
							.parameter(List.of(stringEnsamble(
									List.of(atributos.getTipoDato(), TAB, atributos.getAtributoName()
									))))
							.curlyBraces(false)
							.build()
							.toString()
					);
				}
			}
		}

		for (AttributePojo atributos : listAtributos) {
			if (!atributos.getsId()) {
				atributoName = capitalizeOrUncapitalisedFirstLetter(atributos.getAtributoName(),'u');

				if (this.archivo.getMethodManager().isMethodContaining()) {
					sb2.append(MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.List)
							.returnsClass(returnObjectClass)
							.methodName(stringEnsamble(List.of("findBy",atributoName,"Containing")))
							.parameter(List.of(stringEnsamble(
									List.of(atributos.getTipoDato(), TAB, atributos.getAtributoName()))))
							.curlyBraces(false)
							.build().toString());
				}
			}
		}

		if (this.archivo.getMethodManager().isMethodfindById()) {
			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.returnsClass(returnObjectClass)
					.methodName(stringEnsamble(List.of("findById")))
					.parameter(List.of(stringEnsamble(
							List.of(datoTipo ,TAB, "id"))))
							.curlyBraces(false)
					.build().toString());
		}

		if (this.archivo.getMethodManager().isMetohdSave()) {
			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("save",entidad.getNombreClase())))
							.parameter(List.of(
									stringEnsamble(
											List.of(
													entidad.getNombreClase(), TAB, entidad.getNombreClase().toLowerCase()))
							)).build()
					.toString());
		}

		if (this.archivo.getMethodManager().isMethodgetAll()) {
			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.List)
					.curlyBraces(false)
					.returnsClass(returnObjectClass)
					.methodName(stringEnsamble(List.of("getAll",entidad.getNombreClase())))
					.build().toString());
		}

		if (archivo.getMethodManager().isMethodDelete()){

			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("delete",entidad.getNombreClase())))
					.parameter(List.of(stringEnsamble(
							List.of(datoTipo ,TAB, "id"))))
					.build().toString());
		}

		if (this.archivo.getMethodManager().isMethodUpdate()) {

			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("update",entidad.getNombreClase())))
					.parameter(List.of(
							stringEnsamble(List.of(entidad.getNombreClase() ,TAB,entidad.getNombreClase().toLowerCase()))))
					.build().toString());
		}

		if (this.archivo.getMethodManager().isMethodsaveOrUpdate()) {

			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("saveOrUpdate",entidad.getNombreClase())))
					.parameter(List.of(
							stringEnsamble(List.of(entidad.getNombreClase() ,TAB,entidad.getNombreClase().toLowerCase()))))
					.build().toString());
		}

		for (RelationshipPojo relacion : entidad.getRelaciones()) {
			if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
				if (this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional()) {

					sb2.append(MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.List)
							.curlyBraces(false)
							.returnsClass(returnObjectClass)
							.methodName(stringEnsamble(List.of("findBy",relacion.getNameClassRelacion(),"Containing")))
							.parameter(List.of(
									stringEnsamble(List.of(relacion.getNameClassRelacion(), TAB, relacion.getNameRelacion()))))
							.build().toString());
				}
			} else {
				if (this.archivo.getMethodManager().isMethodContainingRelacion()) {

					sb2.append(MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.List)
							.curlyBraces(false)
							.returnsClass(returnObjectClass)
							.methodName(stringEnsamble(List.of("findByRelacion",relacion.getNameClassRelacion())))
							.parameter(List.of(
									stringEnsamble(
											List.of(relacion.getNameClassRelacion(),TAB,relacion.getNameClassRelacion().toLowerCase()
											))))
							.build().toString());
				}
			}
		}

		sb2.append(stringEnsamble(List.of("}", BREAK_LINE)));
		sb2.append(AnotacionesJava.apacheSoftwareLicensed());
		sb2.append(BREAK_LINE);
		this.createFileClass(nameOfClass, "service", sb2);
	}


	private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb)
			throws InterruptedException {
		String nameFile = entidad_getNombreClase + ".java";
		String singleString = sb.toString();
		String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
				+ "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
				+ barra + entidad_paquete;
		creador.crearArchivo(direction, singleString, nameFile);
		logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
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
