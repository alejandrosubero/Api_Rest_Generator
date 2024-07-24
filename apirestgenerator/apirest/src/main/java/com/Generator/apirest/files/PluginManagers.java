package com.Generator.apirest.files;



import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.interfaces.FileCreateService;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.pojos.back.Creador;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;



@Component
public class PluginManagers {

    private FileCreateService fileService;
    private PluginLoader pluginLoader;

    public PluginManagers(FileCreateService fileService, PluginLoader pluginLoader) {
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

    public void  scanAndLoadPlugins(){
        this.pluginLoader.scanAndLoadPlugins();
    }

}
