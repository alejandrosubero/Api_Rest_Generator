package com.Generator.apirest.modelo.back.java07;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class PomxmlCreator07 {

    protected static final Log logger = LogFactory.getLog(PomxmlCreator07.class);


    public void iniciarPomxml2(ArchivoBaseDatosPojo archivo, Creador creadors) {
    	 this.createPomxml(archivo, creadors);
    }



    private void createPomxml(ArchivoBaseDatosPojo archivo, Creador creador){

        String ax2="";
        String ax1="";

        StringBuilder sb = new StringBuilder();
        
        try {
           
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                    + "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n"
                    + "	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n"
                    + "	<modelVersion>4.0.0</modelVersion>\r\n" 
                    + "\r\n");
           
            sb.append( "<parent>\r\n"
                    + "		<groupId>org.springframework.boot</groupId>\r\n"
                    + "		<artifactId>spring-boot-starter-parent</artifactId>\r\n"
                    + "		<version>"+archivo.getSpringBootVersion()+"</version>\r\n"
                    + "		<relativePath/> <!-- lookup parent from repository -->\r\n"
                    + "	</parent>\r\n"
                    + "\r\n");
           
           sb.append("<groupId>"
                    + creador.getCom() + "." + creador.getPackageNames1()
                    + "</groupId>\r\n"
                    + "	<artifactId>" + creador.getArtifact() + "</artifactId>\r\n"
                    + "	<version>0.0.1-SNAPSHOT</version>\r\n"
                    + "<packaging>war</packaging>\r\n" 
                    + "	<name>" + archivo.getProyectoName() + "</name>\r\n"
                    + "	<description>" + creador.getDescription() + "</description>\r\n"
                    + "\r\n");
            
          
         //   String nombreArchivo = paquete+"."+proyectoName + "Application";
            sb.append("<properties>"+"\r\n");
            sb.append(" <java.version>"+archivo.getJavaVersion()+"</java.version>" + "\r\n");
            sb.append("</properties>"+"\r\n");
            sb.append("\r\n");
            sb.append("\r\n");
            sb.append("     <dependencies>\r\n");

           
           sb.append("		<dependency>" + "\r\n" +
                   "			<groupId>org.springframework.boot</groupId>"+ "\r\n" +
                   "			<artifactId>spring-boot-starter-data-jpa</artifactId>" + "\r\n" +
                   "		</dependency>" + "\r\n");
           
           sb.append( "	    <dependency>"+ "\r\n" +
                   "			<groupId>org.springframework.boot</groupId>"+ "\r\n" +
                   "			<artifactId>spring-boot-starter-web</artifactId>" + "\r\n" +
                   "		</dependency>" + "\r\n");
           
           sb.append( "        <dependency>" + "\r\n" +
                   "			<groupId>org.springframework.boot</groupId>" + "\r\n" +
                   "			<artifactId>spring-boot-devtools</artifactId>" + "\r\n" +
                   "			<scope>runtime</scope>" + "\r\n" +
                   "			<optional>true</optional>" + "\r\n" +
                   "		</dependency>" +  "\r\n");
           
           sb.append("     <dependency>"+ "\r\n" +
                   "			<groupId>org.springframework.boot</groupId>"+ "\r\n" +
                   "			<artifactId>spring-boot-starter-thymeleaf</artifactId>" + "\r\n" +
                   "		</dependency>"+ "\r\n");
           
           
           sb.append("<!-- https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->");
           
           sb.append("<dependency>\r\n"
           		+ "			<groupId>org.modelmapper</groupId>\r\n"
           		+ "			<artifactId>modelmapper</artifactId>\r\n"
           		+ "			<version>2.1.1</version>\r\n"
           		+ "		</dependency>");
           
           sb.append("<!-- <dependency>\r\n"
           		+ "			    <groupId>org.modelmapper</groupId>\r\n"
           		+ "			    <artifactId>modelmapper</artifactId>\r\n"
           		+ "			    <version>2.3.9</version>\r\n"
           		+ "			</dependency> --> ");
           
           sb.append("	<!--	<dependency>-->" + "\r\n" +
                   "			<!--			<groupId>org.springframework.boot</groupId>-->"+ "\r\n" +
                   "			<!--			<artifactId>spring-boot-starter-tomcat</artifactId>-->" + "\r\n" +
                   "			<!--			<scope>provided</scope>-->"+ "\r\n" +
                   "			<!--		</dependency>-->" + "\r\n"); 
           
           
           sb.append("		<dependency>"+ "\r\n" +
	       			"		<groupId>org.springframework.boot</groupId>"+ "\r\n" +
	       			"		<artifactId>spring-boot-starter-actuator</artifactId>"+ "\r\n" +
	       			"		</dependency>"+ "\r\n"); 
	           
           
           sb.append("		<dependency>" + "\r\n" +
                   "			<groupId>org.springframework.boot</groupId>" + "\r\n" +
                   "			<artifactId>spring-boot-starter-mail</artifactId>" + "\r\n" +
                   "		</dependency>" + "\r\n");

        
            if(archivo.getWihtSegurity()){
             sb.append("       <dependency>" + "\r\n" +
                     "			<groupId>org.springframework.boot</groupId>" + "\r\n" +
                     "			<artifactId>spring-boot-starter-security</artifactId>" + "\r\n" +
                     "		</dependency>" + "\r\n");
         }
            
        
            if (archivo.getDatabaseTest()){
                ax2 ="-->";
                ax1="<!--";
            }

            if (archivo.getDataBase()) {
              if(archivo.getTipoDatabase() == 1){
                  sb.append("\r\n");
                  sb.append(ax1+ "       <dependency>" + ax2+"\r\n"+
                          ax1+ "                 <groupId>mysql</groupId>" +  ax2+"\r\n" +
                          ax1+"                 <artifactId>mysql-connector-java</artifactId>"+  ax2+"\r\n" +
                          ax1+"                 <scope>runtime</scope>"+ax2+ "\r\n" +
                          ax1+"         </dependency>" +  ax2 +"\r\n" );
              }else if(archivo.getTipoDatabase() == 2){

                  sb.append(ax1+"       <dependency>" +ax2+ "\r\n" +
                          ax1+"             <groupId>com.oracle.ojdbc</groupId>" + ax2+ "\r\n" +
                          ax1+"             <artifactId>ojdbc8</artifactId>" + ax2+ "\r\n" +
                          ax1+"             <scope>runtime</scope>"+ ax2+ "\r\n" +
                          ax1+"         </dependency>" + ax2 +"\r\n" );

              }else if (archivo.getTipoDatabase() == 4) {
		            sb.append(ax1+"         <dependency>" + ax2+ "\r\n" +
                            ax1+"          <groupId>com.microsoft.sqlserver</groupId>" +ax2+ "\r\n" +
                            ax1+ "           <artifactId>mssql-jdbc</artifactId>" + ax2+"\r\n" +
                            ax1+"             <version>8.4.0.jre11</version>" + ax2+"\r\n" +
                            ax1+ "       </dependency>" + ax2+ "\r\n" );

              } else if (archivo.getTipoDatabase() == 3)  {
                  sb.append("       <dependency>"+ ax2+
                          ax1+"             <groupId>com.h2database</groupId>" + ax2+"\r\n" +
                          ax1+"             <artifactId>h2</artifactId>" + ax2 + "\r\n" +
                          ax1+"             <scope>runtime</scope>" + ax2 +"\r\n" +
                          ax1+"         </dependency>"+ax2+ "\r\n");
              }
            }

            if(archivo.getDatabaseTest()){
                sb.append("       <dependency>"+ "\r\n"+
                        "             <groupId>com.h2database</groupId>" + "\r\n" +
                        "             <artifactId>h2</artifactId>" + "\r\n" +
                        "             <scope>runtime</scope>" + "\r\n" +
                        "         </dependency>"+ "\r\n" );
            }

            if(archivo.getToolClassPojo().getGetPostCreateTool()){
                sb.append("    <dependency>\n" +
                        "            <groupId>org.apache.httpcomponents</groupId>\n" +
                        "            <artifactId>httpclient</artifactId>\n" +
                        "            <version>4.5.8</version>\n" +
                        "        </dependency>\n" +
                        "        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->\n" +
                        "        <dependency>\n" +
                        "            <groupId>com.google.code.gson</groupId>\n" +
                        "            <artifactId>gson</artifactId>\n" +
                        "            <version>2.8.5</version>\n" +
                        "        </dependency>"+ "\r\n");
            }

            if(archivo.getToolClassPojo().getArchivosManamentTool()){
                sb.append("\n" +
                        "\t\t <dependency>\n" +
                        "\t\t\t <groupId>net.lingala.zip4j</groupId>\n" +
                        "\t\t\t <artifactId>zip4j</artifactId>\n" +
                        "\t\t\t <version>1.2.7</version>\n" +
                        "\t\t </dependency>\n");
            }

            // ak servidor soket
            if(archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()){
                sb.append("\n" +
                        "\t\t <dependency>\n" +
                        "\t\t\t<groupId>org.springframework.boot</groupId>\n" +
                        "\t\t\t<artifactId>spring-boot-starter-websocket</artifactId>\n" +
                        "\t\t</dependency>");
            }

            if(archivo.getToolClassPojo().getConverterHex()){
                sb.append("\n" +
                        "\t<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->\n" +
                        "\t\t<dependency>\n" +
                        "\t\t\t<groupId>javax.xml.bind</groupId>\n" +
                        "\t\t\t<artifactId>jaxb-api</artifactId>\n" +
                        "\t\t\t<version>2.2.3</version>\n" +
                        "\t\t</dependency>");
            }
            

            sb.append("<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->" + "\r\n" +
                    "       <dependency>" + "\r\n" +
                    "           <groupId>io.springfox</groupId>" + "\r\n" +
                    "           <artifactId>springfox-swagger2</artifactId>" +"\r\n" +
                    "           <version>2.9.2</version>" + "\r\n" +
                    "        </dependency>"+ "\r\n");

            sb.append("<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->" + "\r\n" +
                    "       <dependency>" + "\r\n" +
                    "           <groupId>io.springfox</groupId>" + "\r\n" +
                    "           <artifactId>springfox-swagger-ui</artifactId>" + "\r\n" +
                    "           <version>2.9.2</version>" + "\r\n" +
                    "       </dependency>" + "\r\n");

                        
            sb.append("       <dependency>" + "\r\n" +
                    "			<groupId>org.springframework.boot</groupId>" + "\r\n" +
                    "			<artifactId>spring-boot-starter-test</artifactId>"+ "\r\n" +
                    "			<scope>test</scope>"+ "\r\n" +
                    "			<exclusions>" + "\r\n" +
                    "				<exclusion>" + "\r\n" +
                    "					<groupId>org.junit.vintage</groupId>" + "\r\n" +
                    "					<artifactId>junit-vintage-engine</artifactId>" + "\r\n" +
                    "				</exclusion>" + "\r\n" +
                    "			</exclusions>" + "\r\n" +
                    "		</dependency>"  + "\r\n");
            
            if (archivo.getWihtSegurity()){
                sb.append("		<dependency>" + "\r\n" +
                        "			<groupId>org.springframework.security</groupId>" + "\r\n" +
                        "			<artifactId>spring-security-test</artifactId>" + "\r\n" +
                        "			<scope>test</scope>" + "\r\n" +
                        "		</dependency>" + "\r\n");

                }

            sb.append("</dependencies>" + "\r\n");
            sb.append( "\r\n");

            
            sb.append("  <build>" + "\r\n" +
            		 "		<finalName>"+archivo.getProyectoName()+"</finalName>" +
                     "		<plugins>" + "\r\n" +
                     "			<plugin>" + "\r\n" +
                     "				<groupId>org.springframework.boot</groupId>" + "\r\n" +
                     "				<artifactId>spring-boot-maven-plugin</artifactId>" + "\r\n" +
                     "			</plugin>" + "\r\n" +
                     "		</plugins>" + "\r\n" +
                     "	</build>" + "\r\n");
            
            sb.append( "\r\n");
            sb.append( "</project>"+ "\r\n" );
          
            String escrito = sb.toString();
            String nombreArchivos = "pom.xml";
            String direccion = creador.getDireccionDeCarpeta() + archivo.getProyectoName();     
            creador.crearArchivo(direccion, escrito, nombreArchivos);
            
        } catch (Exception e) {
            logger.error(e);
        }

    }
	
	
}
