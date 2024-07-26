package com.Generator.apirest.files;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader extends URLClassLoader {
    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    @Autowired
    private ResourceLoader resourceLoader;

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


}
