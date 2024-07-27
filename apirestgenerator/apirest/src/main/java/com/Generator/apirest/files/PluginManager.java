package com.Generator.apirest.files;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.Generator.apirest.core.build.interfaces.plugin.IPluginConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PluginManager {

    private Map<String, Object> plugins = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    private static final String PLUGINS_DIRECTORY = "plugins";

    public List<String> getJarUrls() {
        File pluginsDir = new File(PLUGINS_DIRECTORY);
        List<String> jarPaths = new ArrayList<>();
        if (pluginsDir.isDirectory()) {
            File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
            if (jarFiles != null) {
                for (File jarFile : jarFiles) {
                    try (JarFile jar = new JarFile(jarFile)) {
                        URL jarUrl = jarFile.toURI().toURL();
                        String path = jarUrl.toURI().toString().replace("file:/", "/");
                        jarPaths.add(path);

                    } catch (IOException e) {
                        System.err.println("Error read file .JAR: " + jarFile.getAbsolutePath());
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return jarPaths;
    }

    public void loadPlugins() {
        try {
            loadPlugin();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void loadPlugin() throws Exception {

        File pluginsDir = new File(PLUGINS_DIRECTORY);

        if (pluginsDir.isDirectory()) {
            File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
            if (jarFiles != null) {
                for (File jarFile : jarFiles) {
                    try (JarFile jar = new JarFile(jarFile)) {

                        // Crear un URLClassLoader para cargar las clases desde el JAR
//                        File pluginJar = new File(jarPath);
                        URL[] urls = {jarFile.toURI().toURL()};
                        URLClassLoader classLoader = new URLClassLoader(urls, getClass().getClassLoader());
                        Enumeration<JarEntry> entries = jar.entries();

                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            if (entry.getName().endsWith(".class")) {
                                String className = entry.getName().replace("/", ".").replace(".class", "");
                                try {
                                    // Obtener la clase del plugin (suponiendo que implementa IPluginConnection)
                                    Class<?> pluginClass = classLoader.loadClass(className);

                                    if (IPluginConnection.class.isAssignableFrom(pluginClass) && !pluginClass.isInterface()) {
                                        // Crear una instancia del plugin
                                        Object pluginInstance = pluginClass.newInstance();

                                        // Registrar el plugin en el contexto de Spring
                                        applicationContext.getAutowireCapableBeanFactory().autowireBean(pluginInstance);

                                        // Obtener el identificador del modelo del plugin
                                        String modelId = (String) pluginClass.getMethod("modelIdentifier").invoke(pluginInstance);

                                        // Almacenar el plugin en el mapa con su identificador
                                        plugins.put(modelId, pluginInstance);
                                    }

                                    // Verificar si implementa la interfaz IPluginConnection
//                                    if (!IPluginConnection.class.isAssignableFrom(pluginClass)) {
//                                        throw new IllegalArgumentException("El plugin no implementa IPluginConnection");
//                                    }

                                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                                    System.err.println("the Class no was found: " + className);
                                }
                            }
                        }
                    }
                }
            }
        }


//        plugins.keySet();
    }

    public Object getPlugin(String modelId) {
        return plugins.get(modelId);
    }

    public Map<String, Object> getPluginsMap() {
        return plugins;
    }

}

