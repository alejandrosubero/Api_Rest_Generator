package com.Generator.apirest.modelo.back.base;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.controller.CreateControlles;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

//@Scope("singleton")
@Component
public class EntityResponseClass {


    private ArchivoBaseDatosPojo archivo;

    protected static final Log logger = LogFactory.getLog(CreateControlles.class);

    public void startCreateEntityResponseClass(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.create(creadors, archivo);
    }

    private void create(Creador creador, ArchivoBaseDatosPojo archivo){
        createMapper(creador, archivo);
        createPojo(creador, archivo);
    }

    private void createPojo(Creador creador, ArchivoBaseDatosPojo archivo){

            try {
                    String escritos = metodPojo(archivo, creador).toString();
                    createArchivoController(escritos,  "EntityRespone", "dto", creador,archivo.getProyectoName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void createMapper(Creador creador, ArchivoBaseDatosPojo archivo){
            try {
                    String escritos = metodMapper(archivo, creador).toString();
                    createArchivoController(escritos, "MapperEntityRespone", "mapper", creador, archivo.getProyectoName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    private void createArchivoController( String escrito, String nameOfClass, String entidad_paquete, Creador creador, String proyectoName) {
        try {
        	String barra = java.nio.file.FileSystems.getDefault().getSeparator();
            //String nombreArchivo = nameOfClass + ".java";
           // String entidad_paquete = "controller";
            String direction = creador.getDireccionDeCarpeta() + proyectoName 
					            		+ barra + "src" + barra + "main" + barra
					                    + "java" + barra + creador.getCom() 
					                    + barra + creador.getPackageNames1() 
					                    + barra + creador.getArtifact()
					                    + barra + entidad_paquete;
            creador.crearArchivo(direction, escrito, nameOfClass + ".java");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private  StringBuffer metodPojo(ArchivoBaseDatosPojo archivo, Creador creador) {
        Random rand = new Random();
        long rand_lub1 = rand.nextLong();
        StringBuffer sb1 = new StringBuffer();
        
//        AnotacionesJava anotacionesJava = new AnotacionesJava(archivo);
//        sb1.append(anotacionesJava.creatNotaClase());
        
        sb1.append(new AnotacionesJava(archivo).creatNotaClase().toString()+"\r\n");
        sb1.append("package " + creador.getPackageNames() + ".dto;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("import java.util.Date;"+"\r\n");
        sb1.append("import java.io.Serializable;\n" +
        "import java.util.List;\n" +
        "\n" +
        "\n" +
        "public class EntityRespone implements Serializable {");

        sb1.append("\r\n");
        sb1.append("private static final long serialVersionUID = "+rand_lub1+"L;" + "\r\n");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("    private String error;\n" +
                "   private String mensaje;\n" +
                "   private List<Object> entidades;");
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("\r\n");

        sb1.append(" public String getError() {\n" +
                "        return error;\n" +
                "    }");

        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("    public void setError(String error) {\n" +
                "        this.error = error;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public String getMensaje() {\n" +
                "        return mensaje;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public void setMensaje(String mensaje) {\n" +
                "        this.mensaje = mensaje;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public List<Object> getEntidades() {\n" +
                "        return entidades;\n" +
                "    }\n" +
                "\n" + "\n" +
                "    public void setEntidades(List<Object> entidades) {\n" +
                "        this.entidades = entidades;\n" +
                "    }");

        sb1.append("\r\n");
        sb1.append("}");
        sb1.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb1;
    }



    private  StringBuffer metodMapper(ArchivoBaseDatosPojo archivo, Creador creador) {
  	  		
        StringBuffer sb2 = new StringBuffer();
        
//      AnotacionesJava anotacionesJava = new AnotacionesJava(archivo);
//      sb2.append(anotacionesJava.creatNotaClase());    
        sb2.append(new AnotacionesJava(archivo).creatNotaClase().toString()+"\r\n");
        sb2.append("package " + creador.getPackageNames() + ".mapper;" + "\r\n");
        sb2.append("import " + creador.getPackageNames() + ".dto.EntityRespone;"+"\r\n");

        sb2.append("\r\n");
        sb2.append("\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Component\n" +
                "public class MapperEntityRespone {\n");

        sb2.append("\r\n");
        sb2.append("public <T> EntityRespone setEntityT(List<T> t) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> list = (List<Object>) t;\n" +
                "        entityRespone.setEntidades(list);\n" +
                "        return entityRespone;\n" +
                "    }");

        sb2.append("\r\n");
        sb2.append("public <T> EntityRespone setEntityTobj(T t ) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> ob = new ArrayList<>();\n" +
                "        Object objects = t;\n" +
                "       ob.add(objects);\n" +
                "       entityRespone.setEntidades(ob);\n" +
                "        return entityRespone;\n" +
                "    }");


        sb2.append("\r\n");
        sb2.append(" public <T> EntityRespone setEntityResponT(T t , String mensaje, String error) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> ob = new ArrayList<>();\n" +
                "        Object objects = t;\n" +
                "        ob.add(objects);\n" +
                "        entityRespone.setError(error);\n" +
                "        entityRespone.setMensaje(mensaje);\n" +
                "        entityRespone.setEntidades(ob);\n" +
                "        return entityRespone;\n" +
                "    }\n");


        sb2.append("\r\n");
        sb2.append("  public <T> EntityRespone setEntityResponseT(List<T> t, String mensaje, String error) {\n" +
                "        EntityRespone entityRespone = new EntityRespone();\n" +
                "        List<Object> objects = (List<Object>) t;\n" +
                "        entityRespone.setError(error);\n" +
                "        entityRespone.setMensaje(mensaje);\n" +
                "        entityRespone.setEntidades(objects);\n" +
                "        return entityRespone;\n" +
                "    }");

        sb2.append("\r\n");
        sb2.append("}");
        sb2.append("\r\n");
        sb2.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return sb2;
    }



}
