package com.Generator.apirest.modelo.back.server;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class ServersocketTCP {

    private String proyectoName;
    private String paquete;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();

    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(ServersocketTCP.class);

    public void startCreateServersocket_TCP(ArchivoBaseDatosPojo archivo, Creador creadors) {

      //  this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
       // this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createServersocket();
    }

    private void createServersocket(){
        String nameOfClass = "ServersocketTCP";
        try {
            String escritos = metods().toString();
            createArchivoGetPostTool(escritos, nameOfClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createArchivoGetPostTool( String escrito, String nameOfClass  ) {

        try {
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "serviceImplement";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private StringBuffer metods() {
    StringBuffer sb = new StringBuffer("\r\n");
        logger.info("Create Server socket TCP metodos ");
        try {

       sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sb.append("\r\n");
        sb.append(this.createImport());

        sb.append("\r\n");
        sb.append(this.createTituloClass());
        sb.append("\r\n");

        sb.append(this.createStart());
        sb.append("\r\n");

        sb.append(this.createStop());
        sb.append("\r\n");

        sb.append(this.createThreadClass());
        sb.append("\r\n");

        sb.append(this.createsendPosta());
        sb.append("\r\n");
        sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");

    } catch (Exception e) {
        e.printStackTrace();
    }
        return sb;
}


    private  StringBuffer createImport() {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
        sb1.append("import com.google.gson.Gson;" + "\r\n");
        sb1.append("import org.slf4j.Logger;" + "\r\n");
        sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
        sb1.append("import java.net.*;" + "\r\n");
        sb1.append("import java.io.*;" + "\r\n");
        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import java.io.IOException;" + "\r\n");
        sb1.append("import java.io.InputStreamReader;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }

    private  StringBuffer createTituloClass() {
        StringBuffer sb2 = new StringBuffer();

        sb2.append("" + "\r\n");
      //  sb2.append("@Component" + "\r\n");
        sb2.append("public class ServersocketTCP {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger LOG = LoggerFactory.getLogger(ServersocketTCP.class);" + "\r\n");
        sb2.append("    private ServerSocket serverSocket;" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }

    private  StringBuffer createStart() {
        StringBuffer sb3 = new StringBuffer();
        sb3.append(" public void start(int port) {" + "\r\n");
        sb3.append("try {" + "\r\n");
        sb3.append("  serverSocket = new ServerSocket(port);" + "\r\n");
        sb3.append("    LOG.info(\"el servidor inicio\");" + "\r\n");
        sb3.append(" while (true)" + "\r\n");
        sb3.append("new ClientHandler(serverSocket.accept()).start();" + "\r\n");
        sb3.append("  } catch (IOException e) {" + "\r\n");
        sb3.append(" e.printStackTrace();" + "\r\n");
        sb3.append(" LOG.info(\"el servidor se paro\");" + "\r\n");
        sb3.append("LOG.error(\" error ocurrio => \"+e);" + "\r\n");
        sb3.append("  } finally {" + "\r\n");
        sb3.append("   stop();" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("}" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }

    private  StringBuffer createStop() {
        StringBuffer sb4 = new StringBuffer();
        sb4.append("" + "\r\n");
        sb4.append(" public void stop() {" + "\r\n");
        sb4.append(" try {" + "\r\n");
        sb4.append(" serverSocket.close();" + "\r\n");
        sb4.append("} catch (IOException e) {" + "\r\n");
        sb4.append(" e.printStackTrace();" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }


    private  StringBuffer createThreadClass() {
        StringBuffer sb5 = new StringBuffer();
        sb5.append("" + "\r\n");
        sb5.append(" private static class ClientHandler extends Thread {" + "\r\n");
        sb5.append(" private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);" + "\r\n");
        sb5.append(" private Socket clientSocket;" + "\r\n");
        sb5.append(" private PrintWriter out;" + "\r\n");
        sb5.append(" private BufferedReader in;" + "\r\n");
        sb5.append(" public ClientHandler(Socket socket) {" + "\r\n");
        sb5.append(" this.clientSocket = socket;" + "\r\n");
        sb5.append(" }" + "\r\n");
        sb5.append("" + "\r\n");
        return sb5;
    }



    private  StringBuffer createsendPosta() {
        StringBuffer sb6 = new StringBuffer();
        sb6.append("" + "\r\n");
        sb6.append(" public void run() {" + "\r\n");
        sb6.append("try {" + "\r\n");
        sb6.append("out = new PrintWriter(clientSocket.getOutputStream(), true);" + "\r\n");
        sb6.append("in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));" + "\r\n");
        sb6.append("String inputLine;" + "\r\n");
        sb6.append("while ((inputLine = in.readLine()) != null) {" + "\r\n");
        sb6.append("logger.info(\"mensaje =>\" + inputLine);" + "\r\n");
        sb6.append("if (\".\".equals(inputLine)) {" + "\r\n");
        sb6.append(" out.println(\"bye\");" + "\r\n");
        sb6.append("break;" + "\r\n");
        sb6.append(" }else {" + "\r\n");
        sb6.append(" out.println(\"entendido\");" + "\r\n");
        sb6.append(" }" + "\r\n");
        sb6.append("out.println(\"out.println ==> \"+inputLine);" + "\r\n");
        sb6.append("}" + "\r\n");
        sb6.append(" in.close();" + "\r\n");
        sb6.append(" out.close();" + "\r\n");
        sb6.append("clientSocket.close();" + "\r\n");
        sb6.append("} catch (IOException e) {" + "\r\n");
        sb6.append("LOG.debug(e.getMessage());" + "\r\n");
        sb6.append("}" + "\r\n");
        sb6.append("}" + "\r\n");
        sb6.append("}" + "\r\n");
        sb6.append("}" + "\r\n");
        sb6.append("" + "\r\n");
        sb6.append("" + "\r\n");
        return sb6;
    }

}






