package com.Generator.apirest.core;

import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class Creador {


    //TODO: CREAR LA INTERFACE QUE EXTEND FROM IBaseModel PARA EL MANEJO DE LOS PATH Y LAS CONSTANTES DE ESTA CLASE
    protected static final Log logger = LogFactory.getLog(Creador.class);

    private static final char DOT = '.';
    private String context;
    private String proyectoName = "";
    private String packageNames = "";
    private String description = "";
    private String packageNames1 = "";
    private String artifact = "";
    private String com = "";
    private String sDirectorioTrabajo;
    private String direccionDeCarpeta;
    private String direccionOriginal;


    public Creador() {
    }


    public void setDatos(ArchivoBaseDatosPojo archivo) {
        logger.info("Inicia Metodo setDatos()");
        String barra = java.nio.file.FileSystems.getDefault().getSeparator();
        this.generatePaths(barra);
        com = "";
        artifact = archivo.getArtifact();
        packageNames1 = archivo.getContext();

        this.setContext(archivo.getContext());
        this.setDescription(archivo.getDescription());
        this.setPackageNames(archivo.getPackageNames());
        logger.info("// NOMBRE DEL PAQUETE?" + archivo.getPackageNames());
        this.setProyectoName(archivo.getProyectoName());
        this.valoresPackage();
        this.crearCarpeta();
        this.CopiarAndMoveFile(barra);
    }

    public void setDato(String proyectoName, String packageNames, String description) {
        logger.info("Inicia Metodo setDatos()");
        this.proyectoName = proyectoName;
        this.packageNames = packageNames;
        this.description = description;
    }


    private void generatePaths(String barra) {
        barra = java.nio.file.FileSystems.getDefault().getSeparator();
        sDirectorioTrabajo = System.getProperty("user.dir");
        direccionDeCarpeta = sDirectorioTrabajo + barra + "lib" + barra;
        direccionOriginal = sDirectorioTrabajo + barra + "libbase";
        directionForJava();
    }


    private String path(List<String> paths) {
        String newPath = "";
        String barra = java.nio.file.FileSystems.getDefault().getSeparator();

        for (int i = 0; paths.size() > i; i++) {
            if (i != 0 && i + 1 != paths.size() && paths.get(i) != " ") {
                newPath += barra + paths.get(i);
            } else if (paths.get(i) != " " && i + 1 == paths.size()) {
                newPath += barra + paths.get(i);
            } else if (paths.get(i) != " ") {
                newPath += paths.get(i);
            }

            if (paths.get(i) == " ") {
                newPath += barra;
            }
        }
        return newPath;
    }


    public void valoresPackage() {
        logger.info("Inicia Metodo valoresPackage()");
        for (int i = 0; i < packageNames.indexOf(DOT); i++) {
            com = com + packageNames.charAt(i);
        }
    }


    // para uso dentro de las clases del modelo
    public String directionForJava() {
        String direction = this.path(
                Lists.newArrayList(direccionDeCarpeta + proyectoName,
                        "src",
                        "main",
                        "java"
                        , this.getCom(),
                        this.getPackageNames1(),
                        this.getArtifact(),
                        " ")
        );
        return direction;
    }


    public void crearCarpeta() {

        logger.info("Inicia Metodo crearCarpeta()");

        List<String> listaCarpetas = new ArrayList<String>();
        String lugarCarpeta = this.path(Lists.newArrayList(direccionDeCarpeta + proyectoName));
        String carpetas = this.path(Lists.newArrayList(lugarCarpeta, "src"));
        listaCarpetas.add(carpetas);
        String carpetasa = this.path(Lists.newArrayList(lugarCarpeta, ".mvn"));
        listaCarpetas.add(carpetasa);
        String carpetasb = this.path(Lists.newArrayList(carpetasa, "wrapper"));
        listaCarpetas.add(carpetasb);
        String carpetas2 = this.path(Lists.newArrayList(carpetas, "main"));
        listaCarpetas.add(carpetas2);
        String carpetas3 = this.path(Lists.newArrayList(carpetas, "test"));
        listaCarpetas.add(carpetas3);
        String carpetas4 = this.path(Lists.newArrayList(carpetas3, "java", com, packageNames1, artifact));
        listaCarpetas.add(carpetas4);
        String carpetas5 = this.path(Lists.newArrayList(carpetas2, "java"));
        listaCarpetas.add(carpetas5);
        String carpetas6 = this.path(Lists.newArrayList(carpetas2, "resources"));
        listaCarpetas.add(carpetas6);
        String carpetas7 = this.path(Lists.newArrayList(carpetas5, com, packageNames1, artifact));
        listaCarpetas.add(carpetas7);
        String carpetas8 = this.path(Lists.newArrayList(carpetas6, "static"));
        listaCarpetas.add(carpetas8);
        String carpetas9 = this.path(Lists.newArrayList(carpetas6, "templates"));
        listaCarpetas.add(carpetas9);

        for (String carpeta : listaCarpetas) {
            File create_carpeta = new File(carpeta);
            if (create_carpeta.exists()) {
                logger.info("THE FOLDER EXISTS");
            } else {
                logger.info("THE FOLDER DOES NOT EXIST IT WILL BE CREATED");
                create_carpeta.mkdirs();
            }
        }
    }


    //	TODO: GENERAR LOS ARCHIVOS EN VES DE COPIARLOS? O ESTABLECER CARPETA DE UPDATE?
    public void CopiarAndMoveFile(String barra) {

        logger.info("Inicia Metodo prueba2()");
        String mvn = barra + "mvn";

        try {
            CopiarArchivo2(direccionOriginal + barra + "HELP.md", barra + "HELP.md");
            CopiarArchivo2(direccionOriginal + barra + "mvnw", barra + "mvnw");
            CopiarArchivo2(direccionOriginal + barra + "mvnw.cmd", barra + "mvnw.cmd");
            CopiarArchivo2(direccionOriginal + barra + "gitignore", barra + "gitignore");
            CopiarArchivo2(direccionOriginal + mvn + barra + "wrapper" + barra + "maven-wrapper.jar", barra + ".mvn" + barra + "wrapper" + barra + "maven-wrapper.jar");
            CopiarArchivo2(direccionOriginal + mvn + barra + "wrapper" + barra + "MavenWrapperDownloader.java", barra + ".mvn" + barra + "wrapper" + barra + "MavenWrapperDownloader.java");
            CopiarArchivo2(direccionOriginal + mvn + barra + "wrapper" + barra + "maven-wrapper.properties", barra + ".mvn" + barra + "wrapper" + barra + "maven-wrapper.properties");

        } catch (Exception e) {
            logger.error(e);

        }
    }


    public void CopiarArchivo2(String a, String b) {

        logger.info("Inicia CopiarArchivo2()");
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            File archivoOriginal = new File(a);
            File archivoCopia = new File(direccionDeCarpeta + proyectoName + b);
            inputStream = new FileInputStream(archivoOriginal);
            outputStream = new FileOutputStream(archivoCopia);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
            logger.info("Archivo copiado.");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }


    public void crearArchivo(String direccion, String escrito, String nombreArchivo) {

        logger.info("Inicia CrearArchivo");

        String carpetas = direccion;
        String archivos = java.nio.file.FileSystems.getDefault().getSeparator() + nombreArchivo;
        String contenido1 = escrito;

        File create_carpeta = new File(carpetas);
        File create_archivo = new File(carpetas + archivos);

        if (create_archivo.exists()) {
            logger.info("THE File EXISTS");
        } else {
            logger.info("THE File DOES NOT EXIST IT WILL BE CREATED");
            create_carpeta.mkdirs();
            try {
                if (create_archivo.createNewFile()) {
                    FileWriter fw = new FileWriter(create_archivo);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido1);
                    bw.close();
                    logger.info("THE FILE WAS CREATED");
                } else {
                    logger.info("THE FILE WAS NOT CREATED");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Logger.getLogger(Creador.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    public String getProyectoName() {
        return proyectoName;
    }

    public void setProyectoName(String proyectoName) {
        this.proyectoName = proyectoName;
    }

    public String getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(String packageNames) {
        this.packageNames = packageNames;
    }

    public String getPackageNames1() {
        return packageNames1;
    }

    public void setPackageNames1(String packageNames1) {
        this.packageNames1 = packageNames1;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getDireccionDeCarpeta() {
        return direccionDeCarpeta;
    }

    public void setDireccionDeCarpeta(String direccionDeCarpeta) {
        this.direccionDeCarpeta = direccionDeCarpeta;
    }

    public String getsDirectorioTrabajo() {
        return sDirectorioTrabajo;
    }

    public void setsDirectorioTrabajo(String sDirectorioTrabajo) {
        this.sDirectorioTrabajo = sDirectorioTrabajo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}


