package com.Generator.apirest.files;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;


public class CustomClassLoader extends URLClassLoader {


    private ResourceLoader resourceLoader;


    public CustomClassLoader(URL[] urls) {
        super(urls);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        Resource resource = resourceLoader.getResource("classpath:" + name);
        try {
            classBytes = IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return defineClass(name, classBytes, 0, classBytes.length);
    }


        public Class<?> loadPluginClass(String classPathResource) throws IOException, ClassNotFoundException {
        Resource resource = resourceLoader.getResource("classpath:" + classPathResource);
        byte[] classBytes = IOUtils.toByteArray(resource.getInputStream());
        ClassLoader classLoader = getClass().getClassLoader();
        String className = classPathResource.replace('/', '.').replace(".class", "");
        return defineClass(className, classBytes, 0, classBytes.length);
    }

}
