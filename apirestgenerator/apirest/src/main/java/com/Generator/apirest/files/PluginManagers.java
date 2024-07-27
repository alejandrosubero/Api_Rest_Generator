package com.Generator.apirest.files;



import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.interfaces.FileCreateService;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import com.Generator.apirest.core.pojos.back.Creador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;




public class PluginManagers {

    private FileCreateService fileService;
    private PluginLoader pluginLoader;
    private PluginResourceLoader pluginResourceLoader;


    public PluginManagers(FileCreateService fileService,  PluginResourceLoader pluginResourceLoader) {
        this.fileService = fileService;
        this.pluginLoader = new PluginLoader();
        this.pluginResourceLoader = pluginResourceLoader;
    }


    public List<String> getAllModelsIdentifiers(){
//         this.pluginResourceLoader.scanAndLoadPlugins();
        try {
            this.pluginResourceLoader.loadPlugins(this.pluginResourceLoader.getJarUrls());
        } catch (IOException e) {
           e.printStackTrace();
        }
//        return this.pluginLoader.getModelsIdentifiers();
//        this.pluginResourceLoader.getPluginClasses();
        return this.pluginResourceLoader.updateModelsIdentifiers(this.pluginResourceLoader.getPluginClasses());
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
