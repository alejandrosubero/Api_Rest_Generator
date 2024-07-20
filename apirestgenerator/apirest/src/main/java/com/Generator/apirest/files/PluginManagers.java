package com.Generator.apirest.files;


import com.Generator.apirest.core.build.interfaces.plugin.IPluginConnection;
import com.Generator.apirest.core.interfaces.FileCreateService;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@Service
public class PluginManagers {

    private FileCreateService fileService;
    private PluginLoader pluginLoader;

    public PluginManagers(FileCreateService fileService, PluginLoader pluginLoader) {
        this.fileService = fileService;
        this.pluginLoader = pluginLoader;
    }

    public List<String> getAllModelsIdentifiers(){
        return null;
    }

    public List<String> updateModelsIdentifiers(){
        return null;
    }

    public List<String> getModelMethods(String modelsIdentifiers) {
        return null;
    }

    public void executeBuild(ArchivoBaseDatosPojo baseFilePojo, Creador creator, String identifier){

    }

    public void  scanAndLoadPlugins(){


    }



}
