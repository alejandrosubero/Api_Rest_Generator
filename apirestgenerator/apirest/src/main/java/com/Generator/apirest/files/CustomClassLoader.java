package com.Generator.apirest.files;

import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader extends URLClassLoader {
    public CustomClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // Lógica para cargar la clase desde un array de bytes
        // ... (Aquí iría tu lógica para obtener los bytes de la clase)
        byte[] classBytes = ...

        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
