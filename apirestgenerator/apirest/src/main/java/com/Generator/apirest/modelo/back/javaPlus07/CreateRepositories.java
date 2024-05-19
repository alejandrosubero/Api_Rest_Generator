package com.Generator.apirest.modelo.back.javaPlus07;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateRepositories implements IImportModel {

    private static final Log logger = LogFactory.getLog(CreateRepositories.class);

    private ArchivoBaseDatosPojo archivo;
    private String proyectoName;
    private String packageNames;
    private List<EntityPojo> entidades;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();

   @Autowired
    private AnotacionesJava anotacionesJava;


    public void initCreate(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.packageNames = archivo.getPackageNames();
        this.creador = creadors;
        this.archivo = archivo;
        this.anotacionesJava.activateAnotacionesJava(archivo);

        try {
            this.create();
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
            e.printStackTrace();
        }
    }

    private void create() throws InterruptedException {

        if (this.entidades != null && this.entidades.size() > 0) {
            this.entidades.stream().forEach(entityPojo -> {
                try {
                    logger.info("Inicia la creacion de Repository ===>" + " Repository" + entityPojo.getNombreClase());
                    if(entityPojo.getIsEntity()) {
                        this.createRepository(entityPojo);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }



    private void createRepository(EntityPojo entidad) throws InterruptedException {
        StringBuilder sb1 = new StringBuilder("\r\n");
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String nameOfClass = entidad.getNombreClase() + "Repository";
        logger.info("createRepository" + "  for Entity:  " + entidad.getNombreClase());
        String datoTipo = "";

        for (AttributePojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        sb1.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sb1.append("package " + packageNames + ".repository;\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.List;");
        sb1.append("import java.util.Date;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.Optional;");
        sb1.append("\r\n");
        sb1.append("import org.springframework.data.repository.CrudRepository;");
        sb1.append("\r\n");
        // sb.append("import org.springframework.data.jpa.repository.JpaRepository;");

        sb1.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("public interface " + nameOfClass + " extends CrudRepository< " + entidad.getNombreClase() + ", "
                + datoTipo + "> {\r\n ");
        sb1.append("\r\n");

        for (AttributePojo atributos : listAtributos) {
            String cadenaOriginal = atributos.getAtributoName();
            String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
            String restoDeLaCadena = cadenaOriginal.substring(1);
            String atributoName = primeraLetra + restoDeLaCadena;

            if (!atributos.getsId()) {
                sb1.append("		public Optional<" + entidad.getNombreClase() + "> findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
                sb1.append("\r\n");

                sb1.append("		public List<" + entidad.getNombreClase() + "> findBy" + atributoName + "Containing(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + ");");
                sb1.append("\r\n");
            }
        }
        sb1.append("\r\n");
        sb1.append("}\r\n");
        sb1.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        this.createFileClass(nameOfClass, "repository", sb1);
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb)
            throws InterruptedException {

        String nameFile = entidad_getNombreClase + ".java";
        String singleString = sb.toString();
        String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                + barra + entidad_paquete;
        creador.crearArchivo(direction, singleString, nameFile);
        logger.info("Finalizo la creacion de CreateFileClass" + "  NOMBRE = " + entidad_getNombreClase);
    }


}
