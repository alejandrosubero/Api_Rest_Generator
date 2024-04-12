package com.Generator.apirest.ServiceImpl.capas;


import com.Generator.apirest.core.ArchivosBase;
import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.PomxmlCreator;
import com.Generator.apirest.modelo.back.java07.PomxmlCreator07;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("singleton")
@Component
public class CreateArchivosBase {

    @Autowired
    private ArchivosBase archivosBase;

    @Autowired
    private PomxmlCreator pomxmlCreator;

    @Autowired
    private PomxmlCreator07 pomxmlCreator07;
    
    
    protected static final Log logger = LogFactory.getLog(CreateArchivosBase.class);


    public void StartCreateArchivosBase(ArchivoBaseDatosPojo archivo, Creador creador){

        logger.info("Creando Archivos Base");
        archivosBase.iniciarArchivosBase2(archivo, creador, 0);
        archivosBase.iniciarArchivosBase2(archivo, creador, 1);
    	
        if (archivo.getCapaPojo().getCreateCapaJavaBase7()) {
        	pomxmlCreator07.iniciarPomxml2(archivo, creador);
        }else {
            logger.info("iniciando pomxmlCreator.iniciarPomxml");
            try {
            	pomxmlCreator.iniciarPomxml2(archivo, creador);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
        }
       
    }


}
