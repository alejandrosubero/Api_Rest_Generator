package com.Generator.apirest.modelo.back.basefile;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropetiesFile implements IImportModel {

    protected static final Log logger = LogFactory.getLog(ApplicationPropetiesFile.class);

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
            sb.append("#SWAGGER URL #"+ BREAK_LINE);

            if(archivo.getCapaPojo().getCreateCapaJavaBase7()) {
                sb.append("#http://localhost:1111/swagger-ui.html");
                sb.append(BREAK_LINE);
            }else {
                sb.append("#http://localhost:1111/"+creador.getContext()+"/swagger-ui.html");
                sb.append(BREAK_LINE);
            }

            sb.append(DOUBLEBREAK_LINE);

            creador.crearArchivo(path(Lists.newArrayList(creador.getDireccionDeCarpeta() + archivo.getProyectoName(), "src", "main", "resources", " ")),
                    sb.toString(),	"application.properties");

        } catch (Exception e) {	logger.error(e); }
    }

}
