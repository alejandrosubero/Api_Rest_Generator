package com.Generator.apirest.modelo.back.server;



import com.Generator.apirest.files.Creador;
import com.Generator.apirest.modelo.back.tool.CreateGetPostTool;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//@Scope("singleton")
@Component
public class ClienteTCP {

    private String proyectoName;
    private String paquete;
   // private List<EntityPojo> entidades;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();

 //  private Boolean isServer;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);

    public void startCreateServerSocketTCP(ArchivoBaseDatosPojo archivo, Creador creadors) {

     //   this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
       // this.barra = creador.getBarra();
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createClientesocket();
    }

    private void createClientesocket(){
        String nameOfClass = "ClienteTCP";
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
        logger.info("Create Server socket UDP metodos ");
        try {
            sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
            sb.append("\r\n");
            sb.append(this.createImport());

            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

            sb.append(this.createstartConnection());
            sb.append("\r\n");

            sb.append(this.createsendMessage());
            sb.append("\r\n");

            sb.append(this.createSop());
            sb.append("\r\n");
            sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
        //  sb.append("}"+"\r\n");
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
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        return sb1;
    }

    private  StringBuffer createTituloClass() {
        StringBuffer sb2 = new StringBuffer();

        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append(" public class ClienteTCP {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger loger = LoggerFactory.getLogger(ClienteTCP.class);" + "\r\n");
        sb2.append("    private Socket clientSocket;" + "\r\n");
        sb2.append("    private PrintWriter out;" + "\r\n");
        sb2.append("    private BufferedReader in;" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }

    private  StringBuffer createstartConnection() {
        StringBuffer sb3 = new StringBuffer();

        sb3.append(" public void startConnection(String ip, int port) {" + "\r\n");
        sb3.append("    loger.info(\"Iniciado el Cliente-TCP\");" + "\r\n");
        sb3.append("    try {" + "\r\n");
        sb3.append("    clientSocket = new Socket(ip, port);" + "\r\n");
        sb3.append("    out = new PrintWriter(clientSocket.getOutputStream(), true);" + "\r\n");
        sb3.append("    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("    } catch (SocketException e, IOException e) {" + "\r\n");
        sb3.append("    e.printStackTrace(); " + "\r\n");
        sb3.append("    LOG.debug(\"Error when initializing connection\", e);" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }



    private  StringBuffer createsendMessage() {
        StringBuffer sb4 = new StringBuffer();
        sb4.append("    public String sendMessage(String msg) {" + "\r\n");
        sb4.append("       try {" + "\r\n");
        sb4.append("          out.println(msg);" + "\r\n");
        sb4.append("          return in.readLine();" + "\r\n");
        sb4.append("         } catch (Exception e) {" + "\r\n");
        sb4.append("      return null;" + "\r\n");
        sb4.append("      }" + "\r\n");
        sb4.append("    }" + "\r\n");
        sb4.append("" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }


    private  StringBuffer createSop() {
        StringBuffer sb5 = new StringBuffer();
        sb5.append("" + "\r\n");
        sb5.append("    public void stopConnection() {" + "\r\n");
        sb5.append("        try {" + "\r\n");
        sb5.append("         in.close();" + "\r\n");
        sb5.append("         out.close();" + "\r\n");
        sb5.append("         clientSocket.close();" + "\r\n");
        sb5.append("        } catch (IOException e) {" + "\r\n");
        sb5.append("    LOG.debug(\"error when closing\", e);" + "\r\n");
        sb5.append("            }" + "\r\n");
        sb5.append("    }" + "\r\n");
        sb5.append("}" + "\r\n");
        return sb5;
    }


}
