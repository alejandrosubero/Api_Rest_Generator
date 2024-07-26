package com.Generator.apirest.files;

import com.Generator.apirest.core.build.interfaces.plugin.IPluginConnection;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
public class PluginResourceLoader {

    private static final String PLUGINS_DIRECTORY = "plugins";
    private List<Class<? extends IPluginConnection>> pluginClasses;
    private List<String> modelsIdentifiers;
    private Map<String, List<String>> modelMethods;
    private List<URL> jarPaths;
    private List<String> classNames;

    @Autowired
    private ResourceLoader resourceLoader;


    public PluginResourceLoader() {
        this.jarPaths = new ArrayList<>();
        this.classNames = new ArrayList<>();
        this.pluginClasses = new ArrayList<>();
        this.modelMethods = new HashMap<String, List<String>>();
        this.modelsIdentifiers = new ArrayList<>();

        this.scanAndLoadPlugins();
//
//        if(pluginClasses !=null && pluginClasses.size() > 0 && modelsIdentifiers != null ){
////            this.updateModelsIdentifiers(pluginClasses);
////            this.getModelMethods(pluginClasses);
//        }
    }

    public void scanAndLoadPlugins() {
        try {
             this.loadPlugins(this.getJarUrls());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> scanClassNameFromJar() {

//        if (pluginClasses != null) {
            File pluginsDir = new File(PLUGINS_DIRECTORY);

            if (pluginsDir.isDirectory()) {
                File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

                if (jarFiles != null) {
                    for (File jarFile : jarFiles) {
                        try (JarFile jar = new JarFile(jarFile)) {
                            URL jarUrl = jarFile.toURI().toURL();
                            this.jarPaths.add(jarUrl);
                            Enumeration<JarEntry> entries = jar.entries();
                            while (entries.hasMoreElements()) {
                                JarEntry entry = entries.nextElement();

                                if (entry.getName().endsWith(".class")) {
                                    String classPathResource = entry.getName().replace('/', '.').replace(".class", "");
                                    this.classNames.add(classPathResource);
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error read file .JAR: " + jarFile.getAbsolutePath());
                            e.printStackTrace();
                        }
                    }
                }
            }
//        }
        return this.classNames;
    }

    public List<URL> getJarUrls() {
        File pluginsDir = new File(PLUGINS_DIRECTORY);
        if (pluginsDir.isDirectory()) {
            File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
            if (jarFiles != null) {
                for (File jarFile : jarFiles) {
                    try (JarFile jar = new JarFile(jarFile)) {
                        URL jarUrl = jarFile.toURI().toURL();
                        this.jarPaths.add(jarUrl);
                    } catch (IOException e) {
                        System.err.println("Error read file .JAR: " + jarFile.getAbsolutePath());
                        e.printStackTrace();
                    }
                }
            }
        }
        return this.jarPaths;
    }


//    public Class<?> loadPluginClass(String classPathResource) throws IOException, ClassNotFoundException {
//        Resource resource = resourceLoader.getResource("classpath:" + classPathResource);
//        byte[] classBytes = IOUtils.toByteArray(resource.getInputStream());
//        ClassLoader classLoader = getClass().getClassLoader();
//        String className = classPathResource.replace('/', '.').replace(".class", "");
//        return classLoader.defineClass(className, classBytes, 0, classBytes.length);
//    }


    public void loadPlugins(List<URL> listUrls) throws IOException, ClassNotFoundException, URISyntaxException {

        for (URL url : listUrls) {
            // Crear un URLClassLoader para el plugin
            URL[] urls = {new URL("file://" + url.toURI().toString())};
            CustomClassLoader classLoader = new CustomClassLoader(urls);

            // Abrir el JAR
            JarFile jarFile = new JarFile(new File(url.toURI().toString()));
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    // Construir el nombre de la clase
                    String className = entry.getName().substring(0, entry.getName().lastIndexOf('.')).replace('/', '.');

                    try {
                        // Cargar la clase
//                        Class<?> clazz = classLoader.findClass(className);
                        Class<?> clazz = classLoader.loadClass(className);

                        if (IPluginConnection.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                            pluginClasses.add((Class<? extends IPluginConnection>) clazz);
                        }
                    } catch (ClassNotFoundException | NoClassDefFoundError e) {
                        System.err.println("the Class no was found: " + className);
                    }

                }
            }
            jarFile.close();
        }
    }


    public List<Class<? extends IPluginConnection>> getPluginClasses() {
        return pluginClasses;
    }


    public List<String> updateModelsIdentifiers(List<Class<? extends IPluginConnection>> pluginClasses) {
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


}
