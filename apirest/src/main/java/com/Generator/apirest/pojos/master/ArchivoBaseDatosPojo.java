package com.Generator.apirest.pojos.master;


import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.CapaPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.MethodManager;
import com.Generator.apirest.pojos.tool.ToolClassPojo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Scope("singleton")
@Component
public class ArchivoBaseDatosPojo {

	private String autor;
	private String user;
	private String context;
	private String proyectoName;
	private String packageNames;
	private String description;
	private String springBootVersion;

	private String prograntVersion;
	private String artifact;

	// para el pomxml
	private Boolean wihtSegurity; //spring segurity o no
	private Boolean dataBase; // true o false
	private Boolean databaseTest; // usar databade test y Database
	private String databaseName; // nombre de base de datos
	private Integer tipoDatabase; // oracle = 2, Mysql = 1, h2 = 3., viene sql server = 4
    private Boolean nativeMysql; // usar generador nativo de mysql
	private Double javaVersion;// 1.7 / 1.8 / 11
	private List<EntidadesPojo> entidades;
	private Boolean isToolActive;
	private ToolClassPojo toolClassPojo;
	
	private boolean methoddefaultValue;
	private MethodManager methodManager;
	
	private CapaPojo capaPojo;



	
	
    public boolean checkAtributos(EntidadesPojo entidad) {
      	 List<String> atributosName = new ArrayList<String>();
      	   for (AtributoPojo atributos : entidad.getAtributos()) {
                 if (!atributos.getsId()) { 
                 	atributosName.add(atributos.getAtributoName());
                 	}
             }
      	if(atributosName.size() > 0) {
      		return true;
      	}
      	return false;
      }
    
    
	public CapaPojo getCapaPojo() {
		return capaPojo;
	}

	public void setCapaPojo(CapaPojo capaPojo) {
		this.capaPojo = capaPojo;
	}

	public ArchivoBaseDatosPojo() {	}

	public String getProyectoName() {
		return proyectoName;
	}

	public void setProyectoName(String proyectoName) {
		this.proyectoName = proyectoName;
	}

	public String getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EntidadesPojo> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadesPojo> entidades) {
		this.entidades = entidades;
	}

	public Boolean getWihtSegurity() {
		return wihtSegurity;
	}

	public void setWihtSegurity(Boolean wihtSegurity) {
		this.wihtSegurity = wihtSegurity;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public Boolean getDataBase() {
		return dataBase;
	}

	public void setDataBase(Boolean dataBase) {
		this.dataBase = dataBase;
	}

	public Integer getTipoDatabase() {
		return tipoDatabase;
	}

	public void setTipoDatabase(Integer tipoDatabase) {
		this.tipoDatabase = tipoDatabase;
	}

	public Double getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(Double javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

    public Boolean getNativeMysql() {
        return nativeMysql;
    }

    public void setNativeMysql(Boolean nativeMysql) {
        this.nativeMysql = nativeMysql;
    }

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Boolean getDatabaseTest() {
		return databaseTest;
	}

	public void setDatabaseTest(Boolean databaseTest) {
		this.databaseTest = databaseTest;
	}


	public ToolClassPojo getToolClassPojo() {
		return toolClassPojo;
	}

	public void setToolClassPojo(ToolClassPojo toolClassPojo) {
		this.toolClassPojo = toolClassPojo;
	}

	public String getPrograntVersion() {
		return prograntVersion;
	}

	public void setPrograntVersion(String prograntVersion) {
		this.prograntVersion = prograntVersion;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public Boolean getIsToolActive() {
		return isToolActive;
	}

	public void setIsToolActive(Boolean isToolActive) {
		this.isToolActive = isToolActive;
	}


	public MethodManager getMethodManager() {
		return methodManager;
	}

	public void setMethodManager(MethodManager methodManager) {
		this.methodManager = methodManager;
	}

	public boolean isMethoddefaultValue() {
		return methoddefaultValue;
	}

	public void setMethoddefaultValue(boolean methoddefaultValue) {
		this.methoddefaultValue = methoddefaultValue;
	}

	public String getSpringBootVersion() {
		return springBootVersion;
	}

	public void setSpringBootVersion(String springBootVersion) {
		this.springBootVersion = springBootVersion;
	}

	
	
}

