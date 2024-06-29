package com.Generator.apirest.modelo.back.tool;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//@Scope("singleton")
@Component
public class CoverterHexTool {

    private String proyectoName;
    private String paquete;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();
    private AnotacionesJava anotacionesJava = new AnotacionesJava();


    protected static final Log logger = LogFactory.getLog(CoverterHexTool.class);

    public void startCreateCoverterHexTool(ArchivoBaseDatosPojo archivo, Creador creadors) {

    
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.anotacionesJava.activateAnotacionesJava(archivo);
        this.createHex();
    }

    private void createHex(){
        String nameOfClass = "CoverterHex";
        try {
            
            String escritos = metods().toString();
           
            createArchivo(escritos, nameOfClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createArchivo( String escrito, String nameOfClass  ) {

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
        logger.info("Create hex converter metodos ");
        try {
           
           sb.append(this.anotacionesJava.creatNotaClase() + "\r\n");
            sb.append("\r\n");
            sb.append(this.createImport());

            
            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

           
            sb.append(this.createhexToAscii());
            sb.append("\r\n");

            
            sb.append(this.createasciiToHex());
            sb.append("\r\n");

            
            sb.append(this.createmetodoAsc());
            sb.append("\r\n");

           
            sb.append(this.createascii_To_Hex());
            sb.append("\r\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}"+"\r\n");
        sb.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        return sb;
    }


    private  StringBuffer createImport() {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
        sb1.append("import com.google.gson.Gson;" + "\r\n");
        sb1.append("import org.slf4j.Logger;" + "\r\n");
        sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        sb1.append("import java.net.*;" + "\r\n");
        sb1.append("import java.io.*;" + "\r\n");
        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import java.io.IOException;" + "\r\n");
        sb1.append("import javax.xml.bind.DatatypeConverter;" + "\r\n");



        return sb1;
    }

    private  StringBuffer createTituloClass() {
        StringBuffer sb2 = new StringBuffer();

        sb2.append("" + "\r\n");
        sb2.append("public class CoverterHex {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger LOG = LoggerFactory.getLogger(CoverterHex.class);" + "\r\n");

        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private  StringBuffer createhexToAscii() {
        StringBuffer sb3 = new StringBuffer();

        sb3.append("" + "\r\n");
        sb3.append(" private static String hexToAscii(String hexStr) {" + "\r\n");
        sb3.append(" StringBuffer output = new StringBuffer(\"\");" + "\r\n");
        sb3.append(" for (int i = 0; i < hexStr.length(); i += 2) {" + "\r\n");
        sb3.append(" String str = hexStr.substring(i, i + 2);" + "\r\n");
        sb3.append("output.append((char) Integer.parseInt(str, 16));" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("return output.toString();" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }


    private  StringBuffer createasciiToHex() {
        StringBuffer sb4 = new StringBuffer();
        sb4.append(" private static String asciiToHex(String asciiStr) {" + "\r\n");
        sb4.append(" char[] chars = asciiStr.toCharArray();" + "\r\n");
        sb4.append(" StringBuffer hex = new StringBuffer();" + "\r\n");
        sb4.append("  for (char ch : chars) {" + "\r\n");
        sb4.append("  hex.append(Integer.toHexString((int) ch));" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append(" return hex.toString();" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("" + "\r\n");
        return sb4;
    }



    private  StringBuffer createmetodoAsc() {
        StringBuffer sb5 = new StringBuffer();
        sb5.append(" private void metodoAsc(String hex){" + "\r\n");
        sb5.append("StringBuffer output = new StringBuffer();" + "\r\n");
        sb5.append("  for (int i = 0; i < hex.length(); i+=2) { " + "\r\n");
        sb5.append(" String str = hex.substring(i, i+2);" + "\r\n");
        sb5.append(" output.append((char)Integer.parseInt(str, 16));" + "\r\n");
        sb5.append(" }" + "\r\n");
        sb5.append(" System.out.println(output);" + "\r\n");
        sb5.append(" }" + "\r\n");
        sb5.append("// cambiar esta a un return en la practica" + "\r\n");
        sb5.append("" + "\r\n");
        return sb5;
    }



    private  StringBuffer createascii_To_Hex() {
        StringBuffer sb6 = new StringBuffer();
        sb6.append("" + "\r\n");
        sb6.append(" private static String ascii_To_Hex(String asciiStr) {" + "\r\n");
        sb6.append("byte[] s = DatatypeConverter.parseHexBinary(asciiStr);" + "\r\n");
        sb6.append("String asc = new String(s);" + "\r\n");
        sb6.append(" return asc;" + "\r\n");
        sb6.append(" }" + "\r\n");
        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");
//        sb6.append("" + "\r\n");

        return sb6;
    }




}
