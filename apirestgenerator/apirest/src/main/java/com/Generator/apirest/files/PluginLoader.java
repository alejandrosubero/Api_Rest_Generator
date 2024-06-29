package com.Generator.apirest.files;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private List<Plugin> plugins = new ArrayList<>();

    public void loadPlugins(String directory) throws Exception {
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist: " + directory);
        }

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".jar")) {
                URL[] urls = {dir.toURI().toURL()};
                URLClassLoader classLoader = new URLClassLoader(urls);
                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                Class<?> cls = classLoader.loadClass(className);
                if (Plugin.class.isAssignableFrom(cls)) {
                    Plugin plugin = (Plugin) cls.getDeclaredConstructor().newInstance();
                    plugins.add(plugin);
                }
            }
        }
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}

