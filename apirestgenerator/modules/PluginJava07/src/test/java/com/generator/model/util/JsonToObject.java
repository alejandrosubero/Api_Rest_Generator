package com.generator.model.util;

import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.interfaces.IImportModel;
import com.generator.core.pojos.ArchivoBaseDatosPojo;
import com.google.gson.Gson;
import java.io.FileReader;

public class JsonToObject  implements IImportModel {

    public static String BREAK_LINE = System.lineSeparator();
    public static String pathSeparator = java.nio.file.FileSystems.getDefault().getSeparator();
    public static String systemUserDirectorio = System.getProperty("user.dir");

    public JsonToObject() {
    }

    public  ArchivoBaseDatosPojo getArchivoBaseDatosPojo() {

        ArchivoBaseDatosPojo archivoBaseDatosPojoObject = null;
        Gson gson = new Gson();
        try {
            String filePath = stringEnsamble(
                    systemUserDirectorio,
                    pathSeparator,
                    "src",
                    pathSeparator,
                    "test",pathSeparator,
                    "resources",pathSeparator,
                    "jsonfile", pathSeparator,
                    "usertestModel.json"
            );
            archivoBaseDatosPojoObject = gson.fromJson(new FileReader(filePath), ArchivoBaseDatosPojo.class);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return archivoBaseDatosPojoObject;
    }
}



