package com.Generator.apirest.modelo.back.tool;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


//@Scope("singleton")
@Component
public class CreateGetPostTool {

    private String proyectoName;
    private String paquete;
  //  private List<EntityPojo> entidades;
    private Creador creador;
    private String barra =java.nio.file.FileSystems.getDefault().getSeparator();
//    private int relantizar = SleepRelantizer.RELANTIZER;
//    private int relantizar2 = SleepRelantizer.RELANTIZERC;
//    private Boolean isToolGetPost;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);


    public void startCreateGetPostTool(ArchivoBaseDatosPojo archivo, Creador creadors) {
      //  this.isToolGetPost = archivo.getGetPostCreateTool();
       // this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
     //   this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createGetPostTool();
    }

    private void createGetPostTool(){
                String nameOfClass = "GenericApacheHttpClient";
                try {
                       // Thread.sleep(relantizar);
                        String escritos = metods().toString();
                    //    Thread.sleep(relantizar);
                        createArchivoGetPostTool(escritos, nameOfClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    private void createArchivoGetPostTool( String escrito, String nameOfClass  ) {

        try {
           // Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "serviceImplement";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
          //  Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private StringBuilder metods() {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create Apache Http Client metodos ");
        try {
         //   Thread.sleep(relantizar);
            sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
            sb.append("\r\n");
            sb.append(this.createImport());

          //  Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

           // Thread.sleep(relantizar);
            sb.append(this.createSend());
            sb.append("\r\n");

          //  Thread.sleep(relantizar);
            sb.append(this.createsendGET());
            sb.append("\r\n");

         //   Thread.sleep(relantizar);
            sb.append(this.createsendPost());
            sb.append("\r\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}"+"\r\n");
        sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        return sb;
    }

    private  StringBuilder createImport() {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
        sb1.append("import com.google.gson.Gson;" + "\r\n");
        sb1.append("import org.apache.http.client.methods.CloseableHttpResponse;" + "\r\n");
        sb1.append("import org.apache.http.client.methods.HttpGet;" + "\r\n");
        sb1.append("import org.apache.http.client.methods.HttpPost;" + "\r\n");
        sb1.append("import org.apache.http.entity.StringEntity;" + "\r\n");
        sb1.append("import org.apache.http.impl.client.CloseableHttpClient;" + "\r\n");
        sb1.append("import org.apache.http.impl.client.HttpClients;" + "\r\n");
        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import java.io.IOException;" + "\r\n");
        sb1.append("import java.io.InputStreamReader;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append("import org.springframework.context.annotation.Scope;" + "\r\n");
        sb1.append("import org.springframework.stereotype.Component;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }


    private  StringBuilder createTituloClass() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("" + "\r\n");
        sb2.append("@Component" + "\r\n");
        sb2.append("public class GenericApacheHttpClient {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final String USER_AGENT = \"Mozilla/5.0\";" + "\r\n");
        sb2.append("    private String GET_URL = \"\";" + "\r\n");
        sb2.append("    private String POST_URL = \"\";" + "\r\n");
        sb2.append("/*" + "\r\n");
        sb2.append(" * Por ahora a esta clase hay que codificar el return especifco para cada metodo" + "\r\n");
        sb2.append(" * esto es una clase de ayuda y no de funcionabilidad como tal" + "\r\n");
        sb2.append(" * */" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private  StringBuilder createSend() {
        StringBuilder sb3 = new StringBuilder();
        sb3.append("    public <T> void send(String envio, String url, T t) {" + "\r\n");
        sb3.append("        if (envio.equals(\"GET\")) {" + "\r\n");
        sb3.append("            try {" + "\r\n");
        sb3.append("                GET_URL = url;" + "\r\n");
        sb3.append("                this.sendGET();" + "\r\n");
        sb3.append("            } catch (IOException e) {" + "\r\n");
        sb3.append("                 e.printStackTrace();" + "\r\n");
        sb3.append("            }" + "\r\n");
        sb3.append("        } else if (envio.equals(\"POST\")) {" + "\r\n");
        sb3.append("            try {" + "\r\n");
        sb3.append("                    POST_URL = url;" + "\r\n");
        sb3.append("                    this.sendPOST(t);" + "\r\n");
        sb3.append("              } catch (IOException e) {" + "\r\n");
        sb3.append("                e.printStackTrace();" + "\r\n");
        sb3.append("               }" + "\r\n");
        sb3.append("            }" + "\r\n");
        sb3.append("        }" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }

    private  StringBuilder createsendGET() {
        StringBuilder sb4 = new StringBuilder();
        sb4.append("" + "\r\n");
        sb4.append("    private void sendGET() throws IOException {" + "\r\n");
        sb4.append("        String inputLine;" + "\r\n");
        sb4.append("        HttpGet httpGet = new HttpGet(GET_URL);" + "\r\n");
        sb4.append("        CloseableHttpClient httpClient = HttpClients.createDefault();" + "\r\n");
        sb4.append("        httpGet.addHeader(\"User-Agent\", USER_AGENT); //header" + "\r\n");
        sb4.append("        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);" + "\r\n");
        sb4.append("    // cambiar por un logger" + "\r\n");
        sb4.append("    System.out.println(\"GET Response Status:: \" + httpResponse.getStatusLine().getStatusCode());" + "\r\n");
        sb4.append("        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));" + "\r\n");
        sb4.append("        StringBuffer response = new StringBuffer();" + "\r\n");
        sb4.append("        while ((inputLine = reader.readLine()) != null) {  response.append(inputLine);  }" + "\r\n");
        sb4.append("        reader.close();" + "\r\n");
        sb4.append("    // print result" + "\r\n");
        sb4.append("    System.out.println(\"Respuesta del get: \" + response.toString()); // cambiar por un logger" + "\r\n");
        sb4.append("        httpClient.close();" + "\r\n");
        sb4.append("    }" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }

    private  StringBuilder createsendPost() {
        StringBuilder sb5 = new StringBuilder();
        sb5.append("    private <T> void sendPOST(T t) throws IOException {" + "\r\n");
        sb5.append("        Gson gson = new Gson();" + "\r\n");
        sb5.append("        CloseableHttpClient httpClient = HttpClients.createDefault();" + "\r\n");
        sb5.append("        HttpPost httpPost = new HttpPost(POST_URL);" + "\r\n");
        sb5.append("        httpPost.addHeader(\"User-Agent\", USER_AGENT); //header" + "\r\n");
        sb5.append("        httpPost.addHeader(\"Content-type\", \"application/json\"); //header" + "\r\n");
        sb5.append("        StringEntity postingString = new StringEntity(gson.toJson(t));//gson.tojson() converts your pojo to json" + "\r\n");
        sb5.append("        httpPost.setEntity(postingString);" + "\r\n");
        sb5.append("        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);" + "\r\n");
        sb5.append("    // cambiar por un logger" + "\r\n");
        sb5.append("    System.out.println(\"POST Response Status:: \" + httpResponse.getStatusLine().getStatusCode());" + "\r\n");
        sb5.append("        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));" + "\r\n");
        sb5.append("        String inputLine;" + "\r\n");
        sb5.append("        StringBuffer response = new StringBuffer();" + "\r\n");
        sb5.append("        while ((inputLine = reader.readLine()) != null) {" + "\r\n");
        sb5.append("        response.append(inputLine);" + "\r\n");
        sb5.append("      }" + "\r\n");
        sb5.append("        reader.close();" + "\r\n");
        sb5.append("   // print result" + "\r\n");
        sb5.append("   System.out.println(\"respuesta Post:\" + response.toString()); // cambiar por un logger" + "\r\n");
        sb5.append("        httpClient.close();" + "\r\n");
        sb5.append("    }" + "\r\n");
        sb5.append("" + "\r\n");
        sb5.append("" + "\r\n");
      return sb5;
    }



}
