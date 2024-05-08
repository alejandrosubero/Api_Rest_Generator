package com.Generator.apirest.modelo.back.server;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.modelo.back.tool.CreateGetPostTool;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//@Scope("singleton")
@Component
public class StartServer {

        private String proyectoName;
        private String paquete;
    //    private List<EntityPojo> entidades;
        private Creador creador;
        private String barra = java.nio.file.FileSystems.getDefault().getSeparator();
//        private int relantizar =SleepRelantizer.RELANTIZERD;
//        private int relantizar2 =SleepRelantizer.RELANTIZERD;
//        private Boolean isServer;
        private ArchivoBaseDatosPojo archivo;
        private AnotacionesJava anotacionesJava = new AnotacionesJava();


        protected static final Log logger = LogFactory.getLog(CreateGetPostTool.class);

        public void startCreateStartServer(ArchivoBaseDatosPojo archivo, Creador creadors) {
            this.archivo = archivo;
           // this.entidades = archivo.getEntidades();
            this.proyectoName = archivo.getProyectoName();
            this.paquete = creadors.getPackageNames();
            this.creador = creadors;
           
            this.anotacionesJava.activateAnotacionesJava(archivo);
            this.createServersocket();
        }


        private void createServersocket(){
            String nameOfClass = "StartServer";
            try {
              //  Thread.sleep(relantizar);
                String escritos = metods().toString();
              //  Thread.sleep(relantizar);
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
            logger.info("Create Server socket UDP metodos ");
            try {
                //Thread.sleep(relantizar);
                sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
                sb.append("\r\n");
                sb.append(this.createImport());

              //  Thread.sleep(relantizar);
                sb.append("\r\n");
                sb.append(this.createTituloClass());
                sb.append("\r\n");

              //  Thread.sleep(relantizar);
                sb.append(this.createCostructor());
                sb.append("\r\n");

              //  Thread.sleep(relantizar);
                sb.append(this.createRun());
                sb.append("\r\n");
                
//
//            Thread.sleep(relantizar);
//            sb.append(this.createThreadClass());
//            sb.append("\r\n");
//
//            Thread.sleep(relantizar);
//            sb.append(this.createsendPosta());
//            sb.append("\r\n");

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
            sb1.append("import org.slf4j.Logger;" + "\r\n");
            sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
            sb1.append("import java.net.*;" + "\r\n");
            sb1.append("import java.io.*;" + "\r\n");
            sb1.append("import java.io.BufferedReader;" + "\r\n");
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
            sb2.append("@Scope(\"singleton\")" + "\r\n");
            sb2.append("@Component" + "\r\n");
            sb2.append(" public class StartServer {" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append("    private static final Logger loger = LoggerFactory.getLogger(StartServer.class);" + "\r\n");
            sb2.append("" + "\r\n");
            sb2.append("    private ServersocketTCP serverTCP;" + "\r\n");
            sb2.append("    private ServerUDP serverUDP;" + "\r\n");
            sb2.append(" // establecer una lista de objetos o un map para los puertos" + "\r\n");
            sb2.append("    " + "\r\n");
            sb2.append("" + "\r\n");
            return sb2;
        }

        private  StringBuilder createCostructor() {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(" public StartServer() {" + "\r\n");
            sb3.append(" }" + "\r\n");
            sb3.append("" + "\r\n");
            return sb3;
        }

        private  StringBuilder createRun() {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("" + "\r\n");
            sb4.append(" public void start() {" + "\r\n");

            if (this.archivo.getToolClassPojo().getServerTcp()) {
                sb4.append("serverTCP = new ServersocketTCP();"+"\r\n");
                sb4.append("serverTCP.start(5555);"+"\r\n");
            }

            if (this.archivo.getToolClassPojo().getServerUdp()) {
                sb4.append("serverUDP = new ServerUDP(5555);"+"\r\n");
                sb4.append("serverUDP.start();"+"\r\n");
            }
            sb4.append("}" + "\r\n");
            sb4.append("" + "\r\n");
            return sb4;
        }



    }







