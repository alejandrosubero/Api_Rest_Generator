package com.generator.model.pomxml;



import com.generator.core.interfaces.IImportModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.back.Creador;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.List;


public class PomxmlCreator07 implements IImportModel, PomxmlInterface {

    protected static final Log logger = LogFactory.getLog(PomxmlCreator07.class);

    public void iniciarPomxml2(ArchivoBaseDatosPojo archivo, Creador creadors) {
    	 this.createPomxml(archivo, creadors);
    }


    @Override
    public void initPomxml(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.createPomxml(archivo, creadors);
    }

    private void createPomxml(ArchivoBaseDatosPojo archivo, Creador creador){

        StringBuffer sb = new StringBuffer();
        String ax2="";
        String ax1="";

        try {
            sb.append(stringEnsamble("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", BREAK_LINE
                    , "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",BREAK_LINE
                    , "	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">",BREAK_LINE
                    , "	<modelVersion>4.0.0</modelVersion>",BREAK_LINE
                    , BREAK_LINE));

            sb.append( stringEnsamble(List.of(
                      TAB, "<parent>", BREAK_LINE
                    ,DOUBLETAB, "<groupId>org.springframework.boot</groupId>", BREAK_LINE
                    ,DOUBLETAB, "<artifactId>spring-boot-starter-parent</artifactId>", BREAK_LINE
                    ,DOUBLETAB, "<version>"+archivo.getSpringBootVersion()+"</version>", BREAK_LINE
                    ,DOUBLETAB, "<relativePath/> ",BREAK_LINE
                    ,DOUBLETAB, "<!-- lookup parent from repository -->", BREAK_LINE
                    ,TAB, "</parent>", BREAK_LINE
                    ,DOUBLETAB, DOUBLEBREAK_LINE)));

           sb.append(stringEnsamble(List.of(
                    DOUBLETAB,"<groupId>", creador.getCom(), ".",creador.getPackageNames1(),"</groupId>",BREAK_LINE
                   ,DOUBLETAB, "	<artifactId>",creador.getArtifact(),"</artifactId>",BREAK_LINE
                   ,DOUBLETAB, "	<version>0.0.1-SNAPSHOT</version>",BREAK_LINE
                   ,DOUBLETAB, "<packaging>war</packaging>",BREAK_LINE
                   ,DOUBLETAB, "	<name>" + archivo.getProyectoName() + "</name>",BREAK_LINE
                   ,DOUBLETAB, "	<description>" + creador.getDescription() + "</description>",BREAK_LINE
                   ,DOUBLEBREAK_LINE)));

            sb.append(stringEnsamble(List.of(TAB,"<properties>",BREAK_LINE)));
            sb.append(stringEnsamble(List.of(DOUBLETAB,"<java.version>"+archivo.getJavaVersion(),"</java.version>", BREAK_LINE)));
            sb.append(stringEnsamble(List.of(TAB,"</properties>",BREAK_LINE)));
            sb.append(DOUBLEBREAK_LINE);
            sb.append(stringEnsamble(List.of(TAB,"<dependencies>",BREAK_LINE)));

           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-starter-data-jpa</artifactId>",BREAK_LINE
                   ,TAB,"</dependency>",DOUBLEBREAK_LINE )));
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-starter-web</artifactId>" ,BREAK_LINE
                   ,TAB,"</dependency>" ,DOUBLEBREAK_LINE)));
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>" ,BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-devtools</artifactId>",BREAK_LINE
                   ,DOUBLETAB,"<scope>runtime</scope>",BREAK_LINE
                   ,DOUBLETAB,"<optional>true</optional>",BREAK_LINE
                   ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-starter-thymeleaf</artifactId>" ,BREAK_LINE
                   ,TAB,"</dependency>",DOUBLEBREAK_LINE)));

           sb.append("<!-- https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->");
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB, "<groupId>org.modelmapper</groupId>",BREAK_LINE
                   ,DOUBLETAB, "<artifactId>modelmapper</artifactId>",BREAK_LINE
                   ,DOUBLETAB, "<version>2.1.1</version>",BREAK_LINE
                   ,TAB, "</dependency>", DOUBLEBREAK_LINE)));
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<!-- <dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.modelmapper</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>modelmapper</artifactId>",BREAK_LINE
                   ,DOUBLETAB,"<version>2.3.9</version>",BREAK_LINE
                   ,TAB,"</dependency> --> ",DOUBLEBREAK_LINE)));
           
           sb.append(stringEnsamble(List.of(
                   TAB,"<!--<dependency>-->" ,BREAK_LINE
                   ,DOUBLETAB,"	<!--<groupId>org.springframework.boot</groupId>-->",BREAK_LINE
                   ,DOUBLETAB,"	<!--<artifactId>spring-boot-starter-tomcat</artifactId>-->" ,BREAK_LINE
                   ,DOUBLETAB,"	<!--<scope>provided</scope>-->",BREAK_LINE
                   ,TAB,"<!--</dependency>-->" ,DOUBLEBREAK_LINE)));

           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-starter-actuator</artifactId>",BREAK_LINE
	       			,TAB,"</dependency>",DOUBLEBREAK_LINE)));

           sb.append(stringEnsamble(List.of(
                   TAB,"<dependency>",BREAK_LINE
                   ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                   ,DOUBLETAB,"<artifactId>spring-boot-starter-mail</artifactId>" ,BREAK_LINE
                   ,TAB,"</dependency>",DOUBLEBREAK_LINE)));

            if(archivo.getWihtSegurity()){
             sb.append(stringEnsamble(List.of(
                     TAB,"<dependency>",BREAK_LINE
                     ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                     ,DOUBLETAB,"<artifactId>spring-boot-starter-security</artifactId>",BREAK_LINE
                     ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
         }

            if (archivo.getDatabaseTest()){
                ax2 ="-->";
                ax1="<!--";
            }

            if (archivo.getDataBase()) {
              if(archivo.getTipoDatabase() == 1){
                  sb.append("\r\n");
                  sb.append(stringEnsamble(List.of(
                          TAB,ax1,"<dependency>",ax2,BREAK_LINE
                          ,DOUBLETAB,ax1," <groupId>mysql</groupId>",ax2,BREAK_LINE
                          ,DOUBLETAB,ax1," <artifactId>mysql-connector-java</artifactId>",ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<scope>runtime</scope>",ax2,BREAK_LINE
                         ,TAB, ax1,"</dependency>",ax2,DOUBLEBREAK_LINE)));
              }else if(archivo.getTipoDatabase() == 2){
                  sb.append(stringEnsamble(List.of(
                          TAB,ax1,"<dependency>",ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<groupId>com.oracle.ojdbc</groupId>" , ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<artifactId>ojdbc8</artifactId>" , ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<scope>runtime</scope>",ax2,BREAK_LINE
                          ,TAB,ax1,"</dependency>" ,ax2,DOUBLEBREAK_LINE )));

              }else if (archivo.getTipoDatabase() == 4) {
		            sb.append(stringEnsamble(List.of(
                            TAB,ax1,"<dependency>", ax2,BREAK_LINE
                            ,DOUBLETAB,ax1,"<groupId>com.microsoft.sqlserver</groupId>",ax2,BREAK_LINE
                            ,DOUBLETAB,ax1, "<artifactId>mssql-jdbc</artifactId>",ax2,BREAK_LINE
                            ,DOUBLETAB,ax1,"<version>8.4.0.jre11</version>",ax2,BREAK_LINE
                           , TAB, ax1,"</dependency>" + ax2,DOUBLEBREAK_LINE)));

              } else if (archivo.getTipoDatabase() == 3)  {
                  sb.append(stringEnsamble(List.of(
                          TAB,ax1,"<dependency>",ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<groupId>com.h2database</groupId>" + ax2,BREAK_LINE
                          ,DOUBLETAB,ax1,"<artifactId>h2</artifactId>" + ax2 ,BREAK_LINE
                          ,DOUBLETAB, ax1,"<scope>runtime</scope>" + ax2 ,BREAK_LINE
                          ,TAB,ax1,"</dependency>"+ax2,DOUBLEBREAK_LINE)));
              }
            }

            if(archivo.getDatabaseTest()){
                sb.append(stringEnsamble(List.of(
                        TAB,"<dependency>",BREAK_LINE
                        ,DOUBLETAB,"<groupId>com.h2database</groupId>",BREAK_LINE
                        ,DOUBLETAB,"<artifactId>h2</artifactId>",BREAK_LINE
                        ,DOUBLETAB,"<scope>runtime</scope>",BREAK_LINE
                        ,DOUBLETAB,"</dependency>",DOUBLEBREAK_LINE )));
            }

            if(archivo.getToolClassPojo().getGetPostCreateTool()){
                sb.append(stringEnsamble(List.of(
                        TAB,"<dependency>",BREAK_LINE
                        ,DOUBLETAB,"<groupId>org.apache.httpcomponents</groupId>" ,BREAK_LINE
                        ,DOUBLETAB,"<artifactId>httpclient</artifactId>" ,BREAK_LINE
                        ,DOUBLETAB,"<version>4.5.8</version>" ,BREAK_LINE
                        ,TAB,"</dependency>",DOUBLEBREAK_LINE, DOUBLEBREAK_LINE
                        ,TAB,"<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->" ,BREAK_LINE
                        ,TAB,"<dependency>",BREAK_LINE
                        ,DOUBLETAB,"<groupId>com.google.code.gson</groupId>",BREAK_LINE
                        ,DOUBLETAB,"<artifactId>gson</artifactId>",BREAK_LINE
                        ,DOUBLETAB,"<version>2.8.5</version>",BREAK_LINE
                        ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
            }

            if(archivo.getToolClassPojo().getArchivosManamentTool()){
                sb.append(stringEnsamble(List.of(
                        TAB,"<dependency>",BREAK_LINE
                        ,DOUBLETAB,"<groupId>net.lingala.zip4j</groupId>" ,BREAK_LINE
                        ,DOUBLETAB,"<artifactId>zip4j</artifactId>" ,BREAK_LINE
                        ,DOUBLETAB,"<version>1.2.7</version>" ,BREAK_LINE
                        ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
            }

            // ak servidor soket
            if(archivo.getToolClassPojo().getServerTcp() || archivo.getToolClassPojo().getServerUdp()){
                sb.append(stringEnsamble(List.of(
                        BREAK_LINE, TAB,"<dependency>",BREAK_LINE
                        ,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                        ,DOUBLETAB,"<artifactId>spring-boot-starter-websocket</artifactId>" ,BREAK_LINE
                        ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
            }

            if(archivo.getToolClassPojo().getConverterHex()){
                sb.append(stringEnsamble(List.of(
                        BREAK_LINE, TAB, "<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->",BREAK_LINE
                        ,TAB,"<dependency>" ,BREAK_LINE
                        ,DOUBLETAB,"<groupId>javax.xml.bind</groupId>",BREAK_LINE
                        ,DOUBLETAB,"<artifactId>jaxb-api</artifactId>",BREAK_LINE
                        ,DOUBLETAB,"<version>2.2.3</version>" ,BREAK_LINE
                        ,TAB,"</dependency>",DOUBLEBREAK_LINE)));
            }

            sb.append(stringEnsamble(List.of(
                     TAB, "<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->",BREAK_LINE
                    ,TAB,"<dependency>",BREAK_LINE
                    ,DOUBLETAB,"<groupId>io.springfox</groupId>",BREAK_LINE
                    ,DOUBLETAB, "<artifactId>springfox-swagger2</artifactId>",BREAK_LINE
                    ,DOUBLETAB, "<version>2.9.2</version>",BREAK_LINE
                    ,TAB,"</dependency>",DOUBLEBREAK_LINE)));

            sb.append(stringEnsamble(List.of(
                    TAB,"<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->" ,BREAK_LINE
                    ,DOUBLETAB,"<dependency>",BREAK_LINE
                    ,DOUBLETAB,"<groupId>io.springfox</groupId>",BREAK_LINE
                    ,DOUBLETAB,"<artifactId>springfox-swagger-ui</artifactId>",BREAK_LINE
                    ,DOUBLETAB,"<version>2.9.2</version>",BREAK_LINE
                    ,TAB,"</dependency>",DOUBLEBREAK_LINE)));

                        
            sb.append(stringEnsamble(List.of(
                    TAB,"       <dependency>" ,BREAK_LINE
                    ,DOUBLETAB,"			<groupId>org.springframework.boot</groupId>" ,BREAK_LINE
                    ,DOUBLETAB, "			<artifactId>spring-boot-starter-test</artifactId>",BREAK_LINE
                    ,DOUBLETAB, "			<scope>test</scope>",BREAK_LINE
                    ,DOUBLETAB, "			<exclusions>" ,BREAK_LINE
                    ,DOUBLETAB, "				<exclusion>" ,BREAK_LINE
                    ,DOUBLETAB, "					<groupId>org.junit.vintage</groupId>" ,BREAK_LINE
                    ,DOUBLETAB, "					<artifactId>junit-vintage-engine</artifactId>",BREAK_LINE
                    ,DOUBLETAB,"				</exclusion>" ,BREAK_LINE
                    ,DOUBLETAB, "			</exclusions>" ,BREAK_LINE
                    ,TAB,"		</dependency>"  ,DOUBLEBREAK_LINE)));
            
            if (archivo.getWihtSegurity()){
                sb.append(stringEnsamble(List.of(
                    TAB,"<dependency>" ,BREAK_LINE
                    ,DOUBLETAB,"<groupId>org.springframework.security</groupId>" ,BREAK_LINE
                    ,DOUBLETAB, "<artifactId>spring-security-test</artifactId>" ,BREAK_LINE
                    ,DOUBLETAB,"<scope>test</scope>" ,BREAK_LINE
                    ,TAB, "</dependency>",DOUBLEBREAK_LINE)));
                }

            sb.append(stringEnsamble(List.of(TAB,"</dependencies>",DOUBLEBREAK_LINE)));
            
            sb.append(stringEnsamble(List.of(
                    TAB,"<build>" ,BREAK_LINE
                    ,DOUBLETAB,"<finalName>",archivo.getProyectoName(),"</finalName>",BREAK_LINE
                    ,TAB,DOUBLETAB,"<plugins>",BREAK_LINE
                    ,DOUBLETAB,DOUBLETAB,"<plugin>" ,BREAK_LINE
                    ,TAB,DOUBLETAB,DOUBLETAB,"<groupId>org.springframework.boot</groupId>",BREAK_LINE
                    ,TAB,DOUBLETAB,DOUBLETAB,"<artifactId>spring-boot-maven-plugin</artifactId>" ,BREAK_LINE
                    ,DOUBLETAB,DOUBLETAB,"</plugin>" ,BREAK_LINE
                    ,TAB,DOUBLETAB,"</plugins>" ,BREAK_LINE
                    ,TAB,"</build>" ,DOUBLEBREAK_LINE)));

            sb.append( stringEnsamble(List.of("</project>",DOUBLEBREAK_LINE)));
          
            String escrito = sb.toString();
            String nombreArchivos = "pom.xml";
            String direccion = creador.getDireccionDeCarpeta() + archivo.getProyectoName();     

            // todo; return String;
            
        } catch (Exception e) {
            logger.error(e);
        }

    }


}
