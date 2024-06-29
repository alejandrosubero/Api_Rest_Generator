package com.Generator.apirest.files;


import com.Generator.apirest.modelo.back.basefile.*;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.interfaces.IImportModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class BaseFiles implements IImportModel {

	protected static final Log logger = LogFactory.getLog(BaseFiles.class);

	private ApplicationFile applicationFile;
	private ApplicationPropetiesFile applicationPropetiesFile;
	private ApplicationControllerFile applicationControllerFile;
	private ApplicationTestsFile applicationTestsFile;
	private ServletInitializerFile servletInitializerFile;
	private SwaggerClassFile swaggerClassFile;
	private BuilderBannerProyect builderBannerProyect;

	public BaseFiles(ApplicationFile applicationFile, ApplicationPropetiesFile applicationPropetiesFile, ApplicationControllerFile applicationControllerFile, ApplicationTestsFile applicationTestsFile, ServletInitializerFile servletInitializerFile, SwaggerClassFile swaggerClassFile, BuilderBannerProyect builderBannerProyect) {
		this.applicationFile = applicationFile;
		this.applicationPropetiesFile = applicationPropetiesFile;
		this.applicationControllerFile = applicationControllerFile;
		this.applicationTestsFile = applicationTestsFile;
		this.servletInitializerFile = servletInitializerFile;
		this.swaggerClassFile = swaggerClassFile;
		this.builderBannerProyect = builderBannerProyect;
	}

	public void iniciarArchivosBase2(ArchivoBaseDatosPojo archivo, Creador creadors, int numero) {
		this.generate(numero, archivo, creadors);
	}

	private void generate(int numero, ArchivoBaseDatosPojo archivo, Creador creadors) {

		if(numero == 0){
			this.applicationTestsFile.createApplicationTests(archivo, creadors);
			this.applicationFile.createApplication(archivo, creadors);
			this.servletInitializerFile.createServletInitializer(archivo, creadors);
		}

		if (numero == 1) {
			this.applicationPropetiesFile.createApplicationPropeties(archivo, creadors);
			this.builderBannerProyect.createBanner(archivo, creadors);
			this.applicationControllerFile.createApplicationController(archivo, creadors);
			this.swaggerClassFile.createSwaggerClass(archivo, creadors);
		}
	}



}














