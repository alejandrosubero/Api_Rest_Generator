package com.Generator.apirest.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JarFileConverter {

    private String inputJarFilePath = "path/to/your/input.jar";
    private String outputJarFilePath = "path/to/your/output.jar";


    public String getStringFronJar(String inputJarFilePath) {
        String jarString = "";
        byte[] jarBytes = null;
        try {
            jarBytes = Files.readAllBytes(Paths.get(inputJarFilePath));
            if (jarBytes != null) {
                jarString = new String(jarBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return jarString;
        }
    }


    public void convertSringToJarFile( String jarString , String outputJarFilePath){
        byte[] newJarBytes = null;
        try {
            if(jarString != null && outputJarFilePath!=null ){
                newJarBytes = jarString.getBytes();
            }

            if(newJarBytes != null && newJarBytes.length > 0){
                Files.write(Paths.get(outputJarFilePath), newJarBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
