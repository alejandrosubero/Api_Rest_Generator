package com.Generator.apirest.files;


//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.jar.JarEntry;
//import java.util.jar.JarFile;
//import ar.lefunes.plugins.IPluginMensaje;

public class PluginManagers {
//    private static final String PLUGINS_DIRECTORY = "plugins";
//
//    public static void main(String[] args) {
//        List<Class<? extends IPluginMensaje>> pluginClasses = new ArrayList<>();
//        scanAndLoadPlugins(pluginClasses);
//        executePlugins(pluginClasses);
//    }
//
//    private static void scanAndLoadPlugins(List<Class<? extends IPluginMensaje>> pluginClasses) {
//        File pluginsDir = new File(PLUGINS_DIRECTORY);
//
//        if (pluginsDir.isDirectory()) {
//            File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));
//            if (jarFiles != null) {
//                for (File jarFile : jarFiles) {
//                    try (JarFile jar = new JarFile(jarFile)) {
//                        URL jarUrl = jarFile.toURI().toURL();
//                        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, IPluginMensaje.class.getClassLoader());
//                        Enumeration<JarEntry> entries = jar.entries();
//
//                        while (entries.hasMoreElements()) {
//                            JarEntry entry = entries.nextElement();
//                            if (entry.getName().endsWith(".class")) {
//                                String className = entry.getName().replace("/", ".").replace(".class", "");
//                                try {
//                                    Class<?> clazz = classLoader.loadClass(className);
//                                    if (IPluginMensaje.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
//                                        pluginClasses.add((Class<? extends IPluginMensaje>) clazz);
//                                    }
//                                } catch (ClassNotFoundException | NoClassDefFoundError e) {
//                                    System.err.println("Clase no encontrada o dependencias faltantes: " + className);
//                                }
//                            }
//                        }
//                    } catch (IOException e) {
//                        System.err.println("Error al leer el archivo JAR: " + jarFile.getAbsolutePath());
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    private static void executePlugins(List<Class<? extends IPluginMensaje>> pluginClasses) {
//        for (Class<? extends IPluginMensaje> pluginClass : pluginClasses) {
//            try {
//                IPluginMensaje pluginInstance = pluginClass.getDeclaredConstructor().newInstance();
//
//                String mensaje = pluginInstance.getMensaje();
//                System.out.println(mensaje);
//
//            } catch (Exception e) {
//                System.err.println("Error al ejecutar el plugin: " + pluginClass.getName());
//                e.printStackTrace();
//            }
//        }
//    }
}
