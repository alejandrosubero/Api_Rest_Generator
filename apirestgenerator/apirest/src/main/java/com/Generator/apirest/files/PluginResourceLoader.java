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
    private Map<String, List<String>> modelMethods ;

    @Autowired
    private ResourceLoader resourceLoader;


    public PluginResourceLoader() {
        pluginClasses = new ArrayList<>();
        modelMethods = new HashMap<String, List<String>>();
        modelsIdentifiers = new ArrayList<>();

//        scanAndLoadPlugins(pluginClasses);
//
//        if(pluginClasses !=null && pluginClasses.size() > 0 && modelsIdentifiers != null ){
////            this.updateModelsIdentifiers(pluginClasses);
////            this.getModelMethods(pluginClasses);
//        }
    }

    public void scanAndLoadPlugins(List<Class<? extends IPluginConnection>> pluginClasses) {

        if(pluginClasses != null) {
            File pluginsDir = new File(PLUGINS_DIRECTORY);

            if (pluginsDir.isDirectory()) {
                File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));

                if (jarFiles != null) {
                    for (File jarFile : jarFiles) {
                        try (JarFile jar = new JarFile(jarFile)) {
                            Enumeration<JarEntry> entries = jar.entries();
                            while (entries.hasMoreElements()) {
                                JarEntry entry = entries.nextElement();

                                if (entry.getName().endsWith(".class")) {
                                    String classPathResource = entry.getName().replace('/', '.').replace(".class", "");



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
    }


    public Class<?> loadPluginClass(String classPathResource) throws IOException, ClassNotFoundException {
        Resource resource = resourceLoader.getResource("classpath:" + classPathResource);
        byte[] classBytes = IOUtils.toByteArray(resource.getInputStream());
        ClassLoader classLoader = getClass().getClassLoader();
        String className = classPathResource.replace('/', '.').replace(".class", "");
        return classLoader.defineClass(className, classBytes, 0, classBytes.length);
    }


    public void loadPlugins(List<String> pluginPaths) throws IOException, ClassNotFoundException {
        for (String pluginPath : pluginPaths) {
            // Crear un URLClassLoader para el plugin
            URL[] urls = {new URL("file://" + pluginPath)};
            CustomClassLoader classLoader = new CustomClassLoader(urls);

            // Buscar clases en el JAR y cargarlas
            // ... (Código para buscar clases y cargarlas como se explicó anteriormente)
        }
    }


    
}
