package com.Generator.apirest.modelo.back.javaPlus07;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.core.build.*;
import com.Generator.apirest.core.design.ClassDesign;
import com.Generator.apirest.core.design.MethodDesign;
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

import java.util.ArrayList;
import java.util.List;

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

	private String returnObjectClass(EntityPojo entidad, ArchivoBaseDatosPojo archivo){
		String returnObjectClass = this.archivo.getCapaPojo().getCreateCapaPojoForEntitys()?
						stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM()))
						: entidad.getNombreClase();
		return returnObjectClass;
	}


	private void createService(EntityPojo entidad) throws InterruptedException {

		StringBuilder main = new StringBuilder("\r\n");
		StringBuilder sb2 = new StringBuilder("\r\n");
		List<String> importList = new ArrayList<>();

		String atributoName = "";
		String datoTipo = "";
		List<AttributePojo> listAtributos = entidad.getAtributos();
		String nameOfClass = stringEnsamble(List.of(entidad.getNombreClase(),"Service"));
		String returnObjectClass = this.returnObjectClass(entidad,this.archivo);
		String returnObjectClassPackage = this.archivo.getCapaPojo().getCreateCapaPojoForEntitys()? this.archivo.getCapaPojo().getModelT() : "entitys";

		logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

		for (AttributePojo atributoID : entidad.getAtributos()) {
			if (atributoID.getsId()) {
				datoTipo = atributoID.getTipoDato();
			}
		}

		for (AttributePojo atributos : listAtributos) {
			if (!atributos.getsId()) {
				atributoName = this.capitalizeOrUncapitalisedFirstLetter(atributos.getAtributoName(),'u');
				if (this.archivo.getMethodManager().isMethodFindByOrLoop()) {

					sb2.append( MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.none)
							.returnsClass(
									archivo.getCapaPojo().getCreateCapaPojoForEntitys()?
											stringEnsamble(List.of(entidad.getNombreClase(),this.archivo.getCapaPojo().getModelM())):
											entidad.getNombreClase()
							)
							.methodName(stringEnsamble(List.of("findBy",atributoName)))
							.parameter(List.of(ParameterClassMethod.builder()
											.atributoClass(atributos.getTipoDato())
											.atributoName(atributos.getAtributoName())
									.build()))
							.curlyBraces(false)
							.build()
							.toString()
					);
				}

				if (this.archivo.getMethodManager().isMethodContaining()) {
					sb2.append(MethodDesign.builder()
							.modifiers(Modifier.Public)
							.returnsType(RetunsType.List)
							.returnsClass(returnObjectClass)
							.methodName(stringEnsamble(List.of("findBy",atributoName,"Containing")))
							.parameter(List.of(ParameterClassMethod.builder()
									.atributoClass(atributos.getTipoDato())
									.atributoName(atributos.getAtributoName())
									.build()))
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
					.parameter(List.of(ParameterClassMethod.builder()
									.atributoClass(datoTipo)
									.atributoName("id")
									.build()))
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
					.parameter(List.of(ParameterClassMethod.builder()
											.atributoClass(entidad.getNombreClase())
											.atributoName( entidad.getNombreClase().toLowerCase())
											.build()))
					.build().toString());
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
					.parameter(List.of(ParameterClassMethod.builder()
							.atributoClass(datoTipo)
							.atributoName("id")
							.build()))
					.build().toString());
		}

		if (this.archivo.getMethodManager().isMethodUpdate()) {

			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("update",entidad.getNombreClase())))
					.parameter(
							List.of(ParameterClassMethod.builder()
									.atributoClass(entidad.getNombreClase())
									.atributoName(entidad.getNombreClase().toLowerCase())
									.build()))
					.build().toString());
		}

		if (this.archivo.getMethodManager().isMethodsaveOrUpdate()) {

			sb2.append(MethodDesign.builder()
					.modifiers(Modifier.Public)
					.returnsType(RetunsType.none)
					.curlyBraces(false)
					.returnsClass(RetunsType.Boolean.toString().toLowerCase())
					.methodName(stringEnsamble(List.of("saveOrUpdate",entidad.getNombreClase())))
					.parameter(
							List.of(ParameterClassMethod.builder()
									.atributoClass(entidad.getNombreClase())
									.atributoName(entidad.getNombreClase().toLowerCase())
									.build()))
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
							.parameter(List.of(ParameterClassMethod.builder()
											.atributoClass(relacion.getNameClassRelacion())
											.atributoName(relacion.getNameRelacion())
											.build()))
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
							.parameter(List.of(ParameterClassMethod.builder()
											.atributoClass(relacion.getNameClassRelacion())
											.atributoName(relacion.getNameClassRelacion().toLowerCase())
											.build()))
							.build().toString());
				}
			}
		}


		importList.add(this.importGroupServiceClass());
		importList.add(this.importPahtBuild(packageNames,entidad.getPaquete(),entidad.getNombreClase()));
		importList.add(this.importPahtBuild(packageNames,returnObjectClassPackage,returnObjectClass));

		for (RelationshipPojo relacion : entidad.getRelaciones()) {
			importList.add(this.importPahtBuild(packageNames,entidad.getPaquete(),relacion.getNameClassRelacion()));
		}


		ClassDesign classTemplate = ClassDesign.builder()
				.packagePaht(packageNames)
				.packageName("service")
				.imports(importList)
				.modifier(Modifier.Public)
				.className(nameOfClass)
				.classType(ClassType.INTERFACE)
				.content(new FormatText().reformat(sb2.toString()))
				.isClassIsImplement(false)
				.isClassIsInheritance(false)
				.build();


		main.append(this.anotacionesJava.creatNotaClase() + "\r\n");
		main.append(classTemplate.toString());
		main.append(BREAK_LINE);
		main.append(AnotacionesJava.apacheSoftwareLicensed());

		this.createFileClass(nameOfClass, "service", main);
	}


	private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb)
			throws InterruptedException {
		String nameFile = entidad_getNombreClase + ".java";
		String singleString = sb.toString();

		String direction = creador.getDireccionDeCarpeta() + proyectoName + pathSeparator + "src" + pathSeparator + "main" + pathSeparator
				+ "java" + pathSeparator + creador.getCom() + pathSeparator + creador.getPackageNames1() + pathSeparator + creador.getArtifact()
				+ pathSeparator + entidad_paquete;

		creador.crearArchivo(direction, singleString, nameFile);

		logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
	}


}
