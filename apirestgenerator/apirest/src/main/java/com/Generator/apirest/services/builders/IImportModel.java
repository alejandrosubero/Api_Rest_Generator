package com.Generator.apirest.services.builders;

public interface IImportModel extends IBaseModel {


	default public String importAutowiredAnnotation() {
		StringBuilder autowiredAnnotation = new StringBuilder();
		autowiredAnnotation.append(SPRING_ANNOTATION_IMPORT);
		autowiredAnnotation.append(SPRING_AUTOWIRED_IMPORT);
		return autowiredAnnotation.toString();
	}
	
	default public String importGroupOne() {
		StringBuilder imports = new StringBuilder();
		imports.append(UTIL_LIST_IMPORT);
		imports.append(UTIL_DATE_IMPORT);
		imports.append(UTIL_ARRAY_LIST_IMPORT);
		return imports.toString();
	}

	default public String importGroupServiceClass() {
		StringBuilder imports = new StringBuilder();
		imports.append(UTIL_OPTIONAL_IMPORT);
		imports.append(UTIL_DATE_IMPORT);
		imports.append(UTIL_ARRAY_LIST_IMPORT);
		imports.append(UTIL_LIST_IMPORT);
		return imports.toString();
	}
	
	default public String importServerClienteConvertHexTool() {
		StringBuilder imports = new StringBuilder();
		imports.append(NET_IMPORT);
		imports.append(IO_IMPORT);
		imports.append(IO_BUFFER_READER_IMPORT);
		imports.append(IO_IOEXCEPTION_IMPORT);
		imports.append(IO_IMPUT_STREAM_READER_IMPORT);
		imports.append(SPRING_CONTEX_ANNOTATION_SCOPE_IMPORT);
		imports.append(SPRING_STEREOTYPE_SERVICE_IMPORT);
		return imports.toString();
	}
	

	default public String importAllTool() {
		StringBuilder imports = new StringBuilder();
		imports.append(APACHE_LOG_IMPORT);
		imports.append(APACHE_LOG_FACTORY_IMPORT);
		imports.append(GSON_IMPORT);
		imports.append(SLF4J_LOGGER_IMPORT);
		imports.append(SLF4J_LOGGER_FACTORY_IMPORT);
		imports.append(UTIL_DATE_IMPORT);
		return imports.toString();
	}
	

	default public String importSwagger() {
		StringBuilder imports = new StringBuilder();
		imports.append(SPRING_CONTEX_ANNOTATION_BEAN_IMPORT);
		imports.append(SPRING_CONTEX_ANNOTATION_CONFIGURATION_IMPORT);
		imports.append(SPRINGFOX_PathSelectors_IMPORT);
		imports.append(SPRINGFOX_RequestHandlerSelectors_IMPORT);
		imports.append(SPRINGFOX_DocumentationType_IMPORT);
		imports.append(SPRINGFOX_Docket_IMPORT);
		imports.append(SPRINGFOX_EnableSwagger2_IMPORT);
		imports.append(SPRINGFOX_ApiInfoBuilder_IMPORT);
		imports.append(SPRINGFOX_ApiInfo_IMPORT);
		imports.append(SPRINGFOX_Contact_IMPORT);
		return imports.toString();
	}
	
	
	default public String importApplicationController() {
		StringBuilder imports = new StringBuilder();
		imports.append(SPRING_ANNOTATION_CrossOrigin_IMPORT);
		imports.append(SPRING_ANNOTATION_GetMapping_IMPORT);
		imports.append(SPRING_ANNOTATION_RequestMapping_IMPORT);
		imports.append(SPRING_ANNOTATION_RestController_IMPORT);
		return imports.toString();
	}
	
	
	default public String importServletInitializer(Boolean CapaJavaBase7) {
		StringBuilder imports = new StringBuilder();
		imports.append(SPRING_BOOT_SpringApplicationBuilder_IMPORT);
		if (CapaJavaBase7) {
			imports.append(SPRING_BOOT_support_SpringBootServletInitializer_IMPORT);
		}else {
			imports.append(SPRING_BOOT_SpringBootServletInitializer_IMPORT);
		}
		return imports.toString();
	}
	
	
	default public String importApplicationTests(Boolean CapaJavaBase7) {
		StringBuilder imports = new StringBuilder();
		imports.append(SPRING_BOOT_TEST_IMPORT);
		if (CapaJavaBase7) {
			imports.append(JUNIT_TEST_IMPORT);
		}else {
			imports.append(JUNIT_JUPITER_TEST_IMPORT);
		}
		return imports.toString();
	}
	
	
	default public String importApplication(){
		StringBuilder imports = new StringBuilder();
		// imports.append(SPRING_AUTOWIRED_IMPORT);
		imports.append(APACHE_LOG_FACTORY_IMPORT);
		imports.append(APACHE_LOG_IMPORT);
		imports.append(SPRING_BOOT_SpringApplication_IMPORT);
		imports.append(SPRING_BOOT_SpringBootApplication_IMPORT);
		return imports.toString();
	}


	default public String importController07(){
		StringBuilder imports = new StringBuilder();
		imports.append(SPRING_ANNOTATION_IMPORT);
		imports.append(SPRING_AUTOWIRED_IMPORT);
		imports.append(UTIL_LIST_IMPORT);
		imports.append(UTIL_DATE_IMPORT);
		imports.append(BREAK_LINE);
		return imports.toString();
	}

	/*
	 String direction = 
	    this.path(
	         Lists.newArrayList(direccionDeCarpeta + proyectoName, "src" , "main", "java",this.getCom(),this.getPackageNames1(),this.getArtifact()," ")
	    );
										
		String direction = direccionDeCarpeta + proyectoName + barra + "src" + barra + "main" + barra + "java" + barra + this.getCom() 
		+ barra + this.getPackageNames1() + barra + this.getArtifact() + barra;

	 * */

			
			
			
	
	
}
