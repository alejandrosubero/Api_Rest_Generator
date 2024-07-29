package com.Generator.apirest.files;



;
import com.generator.core.build.models.ModelOup;
import com.generator.core.interfaces.FileCreateService;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.generator.core.pojos.Creador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;



@Component
public class PluginManagers {

    private FileCreateService fileService;
    private PluginLoader pluginLoader;


    @Autowired
    public PluginManagers(FileCreateService fileService,  PluginLoader pluginLoader) {
        this.fileService = fileService;
        this.pluginLoader = pluginLoader;
    }


    public List<String> getAllModelsIdentifiers(){
           return this.pluginLoader.getModelsIdentifiers();
    }

    public List<String> updateModelsIdentifiers(){
        return this.pluginLoader.updateModelsIdentifiers();
    }

    public List<String> getModelMethods(String modelsIdentifiers) {
        return this.pluginLoader.getMethods(modelsIdentifiers);
    }

    public void executeBuild(ArchivoBaseDatosPojo baseFilePojo, Creador creator, String identifier){

        if(baseFilePojo != null && creator != null && identifier !=null) {

            LinkedList<ModelOup> executedModel = this.pluginLoader.executeGetModel(baseFilePojo, creator, identifier);

            for (ModelOup modelOup : executedModel) {

                if ( modelOup.getClassInString() != null && modelOup.getNameOfClass() != null) {
                    if (modelOup.getPackageNane() == null) {
                        this.fileService.crearArchivo(
                                modelOup.getDirectoryForJava(),
                                modelOup.getClassInString(),
                                modelOup.getNameOfClass());
                    }

                    if (modelOup.getPackageNane() != null) {
                        this.fileService.createFileClassJava(
                                modelOup.getNameOfClass(),
                                modelOup.getPackageNane(),
                                new StringBuffer(modelOup.getClassInString()),
                                modelOup.getDirectoryForJava());
                    }
                }
            }
        }
    }

    public void  scanAndLoadPlugins(){
        this.pluginLoader.scanAndLoadPlugins();
    }

}
