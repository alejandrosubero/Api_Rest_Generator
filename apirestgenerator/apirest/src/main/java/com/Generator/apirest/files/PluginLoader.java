package com.Generator.apirest.files;

import com.Generator.apirest.core.build.interfaces.plugin.IPluginConnection;
import com.Generator.apirest.core.build.models.ModelOup;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
public class PluginLoader {

    private static final String PLUGINS_DIRECTORY = "plugins";
    private List<Class<? extends IPluginConnection>> pluginClasses;
    private List<String> modelsIdentifiers;
    private Map<String, List<String>> modelMethods ;




    public PluginLoader() {
        pluginClasses = new ArrayList<>();
        modelMethods = new HashMap<String, List<String>>();
        modelsIdentifiers = new ArrayList<>();

        scanAndLoadPlugins(pluginClasses);

        if(pluginClasses !=null && pluginClasses.size() > 0 && modelsIdentifiers != null ){
            this.updateModelsIdentifiers(pluginClasses);
            this.getModelMethods(pluginClasses);
        }
    }

    public List<String> updateModelsIdentifiers(){
        return this.updateModelsIdentifiers(pluginClasses);
    }

    public Map<String, List<String>> updateModelMethods(){
        this.getModelMethods(pluginClasses);
        return this.getModelMethods();
    }

    public List<String> getModelsIdentifiers() {
        if(this.modelsIdentifiers != null && this.modelsIdentifiers.size() >0){
            return modelsIdentifiers;
        }else {
            return updateModelsIdentifiers();
        }
    }

    public Map<String, List<String>> getModelMethods() {
        if (this.modelMethods == null || this.modelMethods.size() == 0) {
            this.getModelMethods(pluginClasses);
        }
        return modelMethods;
    }

//    public List<Class<? extends IPluginConnection>> getPluginClasses() {
//        if (pluginClasses == null || pluginClasses.size() == 0) {
//            scanAndLoadPlugins(pluginClasses);
//        }
//        return pluginClasses;
//    }

    public void scanAndLoadPlugins(List<Class<? extends IPluginConnection>> pluginClasses) {
        File pluginsDir = new File(PLUGINS_DIRECTORY);

        if (pluginsDir.isDirectory()) {
            File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
            if (jarFiles != null) {
                for (File jarFile : jarFiles) {
                    try (JarFile jar = new JarFile(jarFile)) {
                        URL jarUrl = jarFile.toURI().toURL();
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, IPluginConnection.class.getClassLoader());
                        Enumeration<JarEntry> entries = jar.entries();

                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            if (entry.getName().endsWith(".class")) {
                                String className = entry.getName().replace("/", ".").replace(".class", "");
                                try {
                                    Class<?> clazz = classLoader.loadClass(className);
                                    if (IPluginConnection.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                                        pluginClasses.add((Class<? extends IPluginConnection>) clazz);
                                    }
                                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                                    System.err.println("the Class no was found: " + className);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error read file .JAR: " + jarFile.getAbsolutePath());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private List<String> updateModelsIdentifiers(List<Class<? extends IPluginConnection>> pluginClasses) {
        this.modelsIdentifiers = new ArrayList<>();
        for (Class<? extends IPluginConnection> pluginClass : pluginClasses) {
            try {
                IPluginConnection pluginInstance = pluginClass.getDeclaredConstructor().newInstance();
                String identifier = pluginInstance.modelIdentifier();
                this.modelsIdentifiers.add(identifier);
            } catch (Exception e) {
                System.err.println("Error al ejecutar el plugin: " + pluginClass.getName());
                e.printStackTrace();
            }
        }
        return this.modelsIdentifiers;
    }

    private void getModelMethods(List<Class<? extends IPluginConnection>> pluginClasses) {
        for (Class<? extends IPluginConnection> pluginClass : pluginClasses) {
            try {
                IPluginConnection pluginInstance = pluginClass.getDeclaredConstructor().newInstance();
                modelMethods.put(pluginInstance.modelIdentifier(), pluginInstance.methodList());
            } catch (Exception e) {
                System.err.println("Error al ejecutar el plugin: " + pluginClass.getName());
                e.printStackTrace();
            }
        }
    }


    public LinkedList<ModelOup> executeGetModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator, String identifier){
        if(pluginClasses !=null && pluginClasses.size() > 0 && modelsIdentifiers != null ){
           return this.getModels(this.pluginClasses, baseFilePojo,creator, identifier).get(identifier);
        }
        return new LinkedList<>();
    }


    private Map<String, LinkedList<ModelOup>> getModels(List<Class<? extends IPluginConnection>> pluginClasses, ArchivoBaseDatosPojo baseFilePojo, Creador creator, String identifier) {

        Map<String,  LinkedList<ModelOup>> models = new HashMap<String,  LinkedList<ModelOup>>();

        for (Class<? extends IPluginConnection> pluginClass : pluginClasses) {
            try {
                IPluginConnection pluginInstance = pluginClass.getDeclaredConstructor().newInstance();
               if(identifier.equals(pluginInstance.modelIdentifier())){
                   models.put(pluginInstance.modelIdentifier(), pluginInstance.getModel(baseFilePojo, creator));
               }
            } catch (Exception e) {
                System.err.println("Error al ejecutar el plugin: " + pluginClass.getName());
                e.printStackTrace();
            }
        }
        return models;
    }



}

