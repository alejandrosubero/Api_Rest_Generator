package com.Generator.apirest.core;


import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class ArchivosBase implements IImportModel {


	protected static final Log logger = LogFactory.getLog(ArchivosBase .class);
	
	public ArchivosBase() {	}
	
	
	public void iniciarArchivosBase2(ArchivoBaseDatosPojo archivo, Creador creadors, int numero) {
		this.generate(numero, archivo, creadors);
	}

	private void generate(int numero, ArchivoBaseDatosPojo archivo, Creador creadors) {

		if(numero == 0){
			this.createApplicationTests(archivo, creadors);
			this.createApplication(archivo, creadors);
			this.servletInitializer(archivo, creadors);
		}

		if (numero == 1) {
			this.createApplicationPropeties(archivo, creadors);
			this.createBanner(archivo, creadors);
			this.createApplicationController(archivo, creadors);
			this.createSwaggerClass(archivo, creadors);
		}
	}


	public String primeraLetraMayuscula(String cadena) {
		String cadenaN = "";
		char[] caracteres = cadena.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		for (char c : caracteres) { cadenaN = cadenaN + c; }
		return cadenaN;
	}

	
	public void createApplicationTests(ArchivoBaseDatosPojo archivo, Creador creador) {
		try {
			String proyectoName = archivo.getProyectoName();
			String claseName = proyectoName + "ApplicationTests";
			StringBuilder sb1 = new StringBuilder();
			sb1.append("package " + creador.getPackageNames() + ";" + BREAK_LINE );
			sb1.append(importApplicationTests(archivo.getCapaPojo().getCreateCapaJavaBase7()));			
			sb1.append("@SpringBootTest\r\n" + "class " + claseName + " {\r\n" + BREAK_LINE + "	@Test"+BREAK_LINE);
			sb1.append(DOUBLETAB+"void contextLoads() {"+BREAK_LINE + "	}" + BREAK_LINE + "}" + BREAK_LINE);
			
			String direccion = path(Lists.newArrayList(
							creador.getDireccionDeCarpeta() + proyectoName, "src", "test", "java", creador.getCom(),
							 creador.getPackageNames1(),creador.getArtifact()	));
			

			creador.crearArchivo(direccion, sb1.toString(), proyectoName + "ApplicationTests.java");
		} catch (Exception e) {	logger.error(e); }
	}
	
	
	public void createApplication(ArchivoBaseDatosPojo archivo,Creador creador) {

		try {
			StringBuilder sb = new StringBuilder();
			String proyectoName = archivo.getProyectoName();
			sb.append("package " + creador.getPackageNames() + ";");
			sb.append(DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);

			if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
				sb.append("import " + creador.getPackageNames() + ".serviceImplement.StartServer;");
				sb.append(BREAK_LINE);
				sb.append(SPRING_AUTOWIRED_IMPORT);
				// sb.append( "import org.springframework.beans.factory.annotation.Autowired;"+BREAK_LINE);
			}
			
			sb.append(importApplication());
			sb.append( DOUBLEBREAK_LINE);
			sb.append( BREAK_LINE);
//			sb.append( BREAK_LINE);
			sb.append("@SpringBootApplication");
			sb.append(BREAK_LINE);
			sb.append( "public class " + proyectoName + "Application {");
			sb.append(DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
			sb.append("		protected static final Log logger = LogFactory.getLog(" + proyectoName +"Application.class);");
			sb.append(BREAK_LINE);

			if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
				sb.append("@Autowired");
				sb.append(BREAK_LINE);
				sb.append("private static StartServer startServer;");
				sb.append(BREAK_LINE);
			}

		
			sb.append("		public static void main(String[] args) {");
			sb.append(DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
			
			if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
				sb.append(DOUBLETAB+"logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/swagger-ui.html\");");
				sb.append(BREAK_LINE);
			}else {
				sb.append(DOUBLETAB+"logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext()+"/swagger-ui.html\");");
				sb.append(BREAK_LINE);
			}
			
			sb.append(DOUBLETAB+"SpringApplication.run(" + proyectoName + "Application.class, args);");
			sb.append(BREAK_LINE);

			if (archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()) {
				sb.append(DOUBLETAB+"startServer.start();");
				sb.append(BREAK_LINE);
			}

			if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
				sb.append(DOUBLETAB+"logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/swagger-ui.html\");");
				sb.append(BREAK_LINE);
			}else {
				sb.append(DOUBLETAB+"logger.info(\"the document  Swagger is in link: ==>  http://localhost:1111/" + creador.getContext()+"/swagger-ui.html\");");
				sb.append(BREAK_LINE);
			}
			
			sb.append(DOUBLETAB+"}");
			sb.append(BREAK_LINE);
			sb.append("}");
			sb.append(DOUBLEBREAK_LINE);

			String direccion = 
					path(
							Lists.newArrayList(
							creador.getDireccionDeCarpeta() + proyectoName,
									"src",
									"main",
									"java",
									creador.getCom(),
									creador.getPackageNames1(),
									creador.getArtifact()
							));
			
			creador.crearArchivo(direccion, sb.toString(),  proyectoName + "Application.java");
			
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void servletInitializer(ArchivoBaseDatosPojo archivo, Creador creador) {
		try {
			String claseName = archivo.getProyectoName() + "Application";
			StringBuilder as = new StringBuilder();
			
			as.append("package " + creador.getPackageNames() + ";" + DOUBLEBREAK_LINE);
//			as.append(BREAK_LINE);
			as.append(importServletInitializer(archivo.getCapaPojo().getCreateCapaJavaBase7()));
			as.append("public class ServletInitializer extends SpringBootServletInitializer {" + BREAK_LINE);
			as.append(TAB+"@Override"+BREAK_LINE);
			as.append(TAB+"protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {"+BREAK_LINE);
			as.append(DOUBLETAB+"return application.sources(" + claseName + ".class);\r\n" + "	}" + BREAK_LINE + "}"+DOUBLEBREAK_LINE);

			String direccion = path(
					Lists.newArrayList(
							creador.getDireccionDeCarpeta() + archivo.getProyectoName(),
							"src",
							"main",
							"java",
							creador.getCom(),
							creador.getPackageNames1(),
							creador.getArtifact()
					));

			creador.crearArchivo(direccion, as.toString(), "ServletInitializer.java");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	
	public void createApplicationController(ArchivoBaseDatosPojo archivo, Creador creador) {
		try {
			
			StringBuilder controller = new StringBuilder();
			
			controller.append("package " +creador.getPackageNames() + ".controller;"+ BREAK_LINE);
			controller.append(importApplicationController());
			controller.append(BREAK_LINE);
			controller.append("@RestController"+BREAK_LINE);
			controller.append("@CrossOrigin(origins = \"*\")"+BREAK_LINE);
			controller.append("@RequestMapping(\"/\")" + BREAK_LINE);
			controller.append("public class " + archivo.getProyectoName() + "Controller {");
			controller.append(DOUBLEBREAK_LINE);
			controller.append(TAB+"@GetMapping(\"/start\")"+ BREAK_LINE);
			controller.append(TAB+"public String startTest() {" + BREAK_LINE);
			controller.append(DOUBLETAB+"return \" <h1>!!!!!!!!!!!!!!!!!Hello Mundo!!!!!!!!!!!!</h1>\"+ " + BREAK_LINE);
			controller.append(DOUBLETAB+"\"<br>\" + \r\n" + BREAK_LINE);
			controller.append(DOUBLETAB+"\"<h2> !!!!!!!!!!!Estoy funcionando!!!!!!!!! </h2>\"; " + BREAK_LINE);
			controller.append(TAB+"}"+ BREAK_LINE);
			controller.append(TAB+"}"+ BREAK_LINE);
				
			String direccion = path(Lists.newArrayList(
					creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src", "main", "java", 
					creador.getCom(), creador.getPackageNames1(), creador.getArtifact(),"controller")
					);
			
			creador.crearArchivo(direccion, controller.toString(), archivo.getProyectoName() + "Controller.java");
			
		} catch (Exception e) {	logger.error(e); }
	}
	

	public void createSwaggerClass(ArchivoBaseDatosPojo archivo, Creador creador){
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("package " + creador.getPackageNames() + ".configurations;"+BREAK_LINE);
			sb.append(BREAK_LINE);
			sb.append(importSwagger());
			sb.append(DOUBLEBREAK_LINE);
			sb.append("	@Configuration"+BREAK_LINE);
			sb.append("	@EnableSwagger2"+BREAK_LINE);
			sb.append("	public class SwaggerConfig {"+BREAK_LINE);
			sb.append(BREAK_LINE);
			sb.append(DOUBLETAB+ "@Bean"+BREAK_LINE);
			sb.append(DOUBLETAB+"public Docket api() {"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+"return new Docket(DocumentationType.SWAGGER_2)"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".select()"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".apis(RequestHandlerSelectors.basePackage(\""+ creador.getPackageNames() +".controller\"))"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".build().apiInfo(apiEndPointsInfo());"+BREAK_LINE);
			sb.append(DOUBLETAB+"}"+BREAK_LINE);
			sb.append(DOUBLEBREAK_LINE);
			sb.append(DOUBLETAB+"private ApiInfo apiEndPointsInfo() {"+DOUBLEBREAK_LINE);

			// TODO: hay que hacer que se pueda decidir si se quiere o no colocar
			sb.append(DOUBLETAB+ "Contact contact = new Contact(\"Alejandro\", \"https://github.com/SuberoPrueba\", \"blu@Gmail.com\");" + DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
			sb.append(DOUBLETAB+TAB+"return new ApiInfoBuilder()" + BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".title(\""+archivo.getProyectoName()+"\")"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".description(\"Description: "+archivo.getDescription()+"\")"+BREAK_LINE);
			
			// TODO: Pensar que se colocarar ak y hacerlo de forma que se selecione por ahora queda comentedo
			// sb.append("		.termsOfServiceUrl(\"https://github.com\")"	+	BREAK_LINE);
			
			// TODO: hay que hacer que se pueda decidir si se quiere o no colocar
			sb.append(DOUBLETAB+TAB+".contact(contact)"	+ BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".license(\"Apache License Version 2.0\")"	+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".licenseUrl(\"https://www.apache.org/licenses/LICENSE-2.0\")"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".version(\""+archivo.getPrograntVersion()+"\")"+BREAK_LINE);
			sb.append(DOUBLETAB+TAB+".build();"	+BREAK_LINE);
			sb.append(TAB+"}"+BREAK_LINE);
			sb.append(BREAK_LINE);
			sb.append("}"+DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);

			String direccion = path(
					Lists.newArrayList(
							creador.getDireccionDeCarpeta() + archivo.getProyectoName(),
							"src",
							"main",
							"java",
							creador.getCom(),
							creador.getPackageNames1(),
							creador.getArtifact(),
							"configurations"
					));

			creador.crearArchivo(direccion, sb.toString(), "SwaggerConfig.java");
			
		} catch (Exception e) {
			logger.error(e);
		}
	}


	public void createApplicationPropeties(ArchivoBaseDatosPojo archivo, Creador creador) {
		try {
			StringBuilder sb = new StringBuilder();
			String ax = "";
			
			int tipoDatabase= archivo.getTipoDatabase();
			
			if (archivo.getDatabaseTest()){
				ax ="#";
			}
			sb.append("# this is the server port 1111 #"+ BREAK_LINE );
			sb.append("server.port = 1111"+ BREAK_LINE);
			
			if(!archivo.getCapaPojo().getCreateCapaJavaBase7()) {
				sb.append("server.servlet.context-path=/"+creador.getContext() + BREAK_LINE);	
			}
			
			sb.append(BREAK_LINE);

			if( tipoDatabase == 1) {
				sb.append(BREAK_LINE);
				sb.append(ax + "spring.datasource.url=jdbc:mysql://localhost:3306/"+archivo.getDatabaseName()+"?serverTimezone=UTC");
				sb.append(BREAK_LINE);
				sb.append(ax + "spring.datasource.username=");
				sb.append(BREAK_LINE);
				sb.append(ax + "spring.datasource.password =");
				sb.append(BREAK_LINE);
				sb.append(BREAK_LINE);
				sb.append("#spring.jpa.generate-ddl=true");
				sb.append(BREAK_LINE);
				sb.append(ax + "spring.jpa.show-sql = false");
				sb.append(BREAK_LINE);
				sb.append(ax + "spring.jpa.hibernate.ddl-auto=update");
				sb.append(BREAK_LINE);
				sb.append("#spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);

				if (archivo.getNativeMysql()) {
					sb.append("# Naming strategy");
					sb.append(BREAK_LINE);
					sb.append(ax + "spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy");
					sb.append(DOUBLEBREAK_LINE);
//					sb.append(BREAK_LINE);
					sb.append("# Allows Hibernate to generate SQL optimized for a particular DBMS");
					sb.append(DOUBLEBREAK_LINE);
//					sb.append(BREAK_LINE);
					sb.append(ax + "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect");
					sb.append(DOUBLEBREAK_LINE);
//					sb.append(BREAK_LINE);
					sb.append("#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLMyISAMDialect");
					sb.append(BREAK_LINE);
				} else {
					sb.append(BREAK_LINE);
					sb.append(ax + "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect");
					sb.append(BREAK_LINE);
					sb.append("#spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLMyISAMDialect");
					sb.append(BREAK_LINE);
				}
			}

			if( tipoDatabase == 2) {
				sb.append(BREAK_LINE);
				sb.append("#spring.jpa.properties.hibernate.dialect = ");
				sb.append(BREAK_LINE);
                sb.append("# create ORACLE #");
            	sb.append(BREAK_LINE);
                sb.append(ax +"spring.datasource.url=jdbc:oracle:thin:@localhost:1521:"+archivo.getDatabaseName());
            	sb.append(BREAK_LINE);
                sb.append(ax+ "spring.datasource.username=system");
            	sb.append(BREAK_LINE);
                sb.append(ax +"spring.datasource.password=admin");
            	sb.append(BREAK_LINE);
                sb.append(ax + "spring.datasource.driver.class=oracle.jdbc.driver.OracleDriver");
            	sb.append(BREAK_LINE);
                sb.append("# spring.datasource.driver-class-oracle.jdbc.driver.OracleDriver");
            	sb.append(BREAK_LINE);
                sb.append("# logging");
            	sb.append(BREAK_LINE);
                sb.append(ax+"logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n");
            	sb.append(BREAK_LINE);
                sb.append("#logging.level.org.hibernate.SQL=debug");
            	sb.append(BREAK_LINE);
                sb.append(ax+"logging.level.org.hibernate.type.descriptor.sql=trace");
            	sb.append(BREAK_LINE);
                sb.append("#logging.level.=error");
            	sb.append(BREAK_LINE);
				sb.append(BREAK_LINE);
			}

			if( tipoDatabase == 4) {
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.datasource.url = jdbc:sqlserver://localhost:1433;databaseName="+archivo.getDatabaseName()+";integratedSecurity=true");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.datasource.username=alejandro");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.datasource.password=Root");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("#spring-jpa-sql");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.jpa.properties.hibernate.format_sql = true");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.jpa.show-sql=true");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("## Hibernate Properties");	
				sb.append(BREAK_LINE);
				sb.append("# The SQL dialect makes Hibernate generate better SQL for the chosen database");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.SQLServer2012Dialect");
				sb.append(BREAK_LINE);
				sb.append("# spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("# Hibernate ddl auto (create, create-drop, validate, update)");
				sb.append(BREAK_LINE);
				sb.append(ax+"spring.jpa.hibernate.ddl-auto = update");
				sb.append(BREAK_LINE);
				sb.append("#spring.jpa.hibernate.ddl-auto = create-drop");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
			}


			if (archivo.getDatabaseTest() || tipoDatabase == 3){
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("#CONFIGURATION FOR TEST#" );
				sb.append(BREAK_LINE);
				sb.append("spring.datasource.url=jdbc:h2:mem:testdb");
				sb.append(BREAK_LINE);
				sb.append("spring.datasource.driverClassName=org.h2.Driver");
				sb.append(BREAK_LINE);
				sb.append("spring.datasource.username=sa");
				sb.append(BREAK_LINE);
				sb.append("spring.datasource.password=");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("spring.h2.console.enabled=true");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append("spring.jpa.database-platform=org.hibernate.dialect.H2Dialect");
				sb.append(BREAK_LINE);
				sb.append("spring.jpa.generate-ddl=true");
				sb.append(DOUBLEBREAK_LINE);
//				sb.append(BREAK_LINE);
				sb.append(BREAK_LINE);
				sb.append("#H2 CONSOLE URL #" );
				sb.append(BREAK_LINE);
				
				if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
					sb.append("#http://localhost:1111/h2-console");
					sb.append(BREAK_LINE);
				}else {
					sb.append("#http://localhost:1111/"+creador.getContext()+"/h2-console");
					sb.append(BREAK_LINE);
						
				}
				sb.append(BREAK_LINE);
			}

			sb.append(DOUBLEBREAK_LINE);
//			sb.append(BREAK_LINE);
			sb.append("#SWAGGER URL #"+ BREAK_LINE);
			
			if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
				sb.append("#http://localhost:1111/swagger-ui.html");
				sb.append(BREAK_LINE);
			}else {
				sb.append("#http://localhost:1111/"+creador.getContext()+"/swagger-ui.html");
				sb.append(BREAK_LINE);
			}
			
//			sb.append(BREAK_LINE);
			sb.append(DOUBLEBREAK_LINE);
		
			creador.crearArchivo(path(Lists.newArrayList(creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src", "main", "resources", " ")), 
					sb.toString(),	"application.properties");
			
		} catch (Exception e) {	logger.error(e); }
	}


	public void createBanner(ArchivoBaseDatosPojo archivo, Creador creador) {
		BuilderBannerProyect banner = new BuilderBannerProyect(
				path(Lists.newArrayList(creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src", "main", "resources", " ")),
				archivo.getProyectoName());
		banner.builderBannercreate();
	}

}














