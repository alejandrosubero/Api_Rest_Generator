package ar.lefunes.plugins;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.generator.plugin.build.interfaces.plugin.IPluginConnection;

public class PluginManagersTest {
    private static final String PLUGINS_DIRECTORY = "plugins";

    public static void main(String[] args) {
        List<Class<? extends IPluginConnection>> pluginClasses = new ArrayList<>();
        scanAndLoadPlugins(pluginClasses);
        executePlugins(pluginClasses);
    }

    private static void scanAndLoadPlugins(List<Class<? extends IPluginConnection>> pluginClasses) {
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
                                    System.err.println("Clase no encontrada o dependencias faltantes: " + className);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer el archivo JAR: " + jarFile.getAbsolutePath());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void executePlugins(List<Class<? extends IPluginConnection>> pluginClasses) {
        for (Class<? extends IPluginConnection> pluginClass : pluginClasses) {
            try {
                IPluginConnection pluginInstance = pluginClass.getDeclaredConstructor().newInstance();

                String mensaje = pluginInstance.modelIdentifier();
                System.out.println(mensaje);

            } catch (Exception e) {
                System.err.println("Error al ejecutar el plugin: " + pluginClass.getName());
                e.printStackTrace();
            }
        }
    }
}
