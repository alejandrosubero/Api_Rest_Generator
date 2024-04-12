package com.Generator.apirest.modelo.back.tool;

import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateArchivosManamentTool {


    private String proyectoName;
    private String paquete;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();
  
    protected static final Log logger = LogFactory.getLog(CreateArchivosManamentTool.class);


    public void startCreateArchivo(ArchivoBaseDatosPojo archivo, Creador creadors) {

        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.createGetPostTool(archivo);
    }
    

    private void createGetPostTool(ArchivoBaseDatosPojo archivo){
        String nameOfClass = "ArchivosManament";
        try {
            String escritos = metods(archivo).toString();
            createArchivoGetPostTool(escritos, nameOfClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createArchivoGetPostTool( String escrito, String nameOfClass ) {

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

    private StringBuilder metods(ArchivoBaseDatosPojo archivo) {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("Create create Archivos Manament Tool ");
        try {
            
        	  
            sb.append(new AnotacionesJava(archivo).creatNotaClase() + "\r\n");
            sb.append("\r\n");
            sb.append(this.createImport());

         
            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

        
            sb.append(this.createX3());
            sb.append("\r\n");

         
            sb.append(this.createX4());
            sb.append("\r\n");

            
            sb.append(this.createX5());
            sb.append("\r\n");

           
            sb.append(this.createX6());
            sb.append("\r\n");

          
            sb.append(this.createX7());
            sb.append("\r\n");

           
            sb.append(this.createX8());
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
        sb1.append("import net.lingala.zip4j.core.ZipFile; " + "\r\n");
        sb1.append("import net.lingala.zip4j.exception.ZipException; " + "\r\n");
        sb1.append("import net.lingala.zip4j.model.ZipParameters; " + "\r\n");
        sb1.append("import net.lingala.zip4j.util.Zip4jConstants; " + "\r\n");
        sb1.append("import org.apache.commons.logging.Log; " + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory; " + "\r\n");
        sb1.append("import org.springframework.context.annotation.Scope;" + "\r\n");
        sb1.append("import org.springframework.stereotype.Component;\n " + "\r\n");
        sb1.append("import javax.swing.*;" + "\r\n");
        sb1.append("import java.io.*; " + "\r\n");
        sb1.append("import java.net.InetAddress; " + "\r\n");
        sb1.append("import java.net.UnknownHostException; " + "\r\n");
        sb1.append("import java.util.List;" + "\r\n");
        sb1.append( "import java.util.Date;"+"\r\n");
        sb1.append("import java.util.logging.Level;" + "\r\n");
        sb1.append("import java.util.logging.Logger;" + "\r\n");
//        sb1.append("import java.io.BufferedReader;" + "\r\n");
        sb1.append("import java.io.IOException;" + "\r\n");
//        sb1.append("import java.io.InputStreamReader;" + "\r\n");
        sb1.append(" " + "\r\n");
        return sb1;
    }


    private  StringBuilder  createTituloClass() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("" + "\r\n");
        sb2.append("@Component" + "\r\n");
        sb2.append("public class ArchivosManament {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("protected static final Log logger = LogFactory.getLog(ArchivosManament.class);" + "\r\n");
        sb2.append("private boolean isEncryptFiles = false;" + "\r\n");
        sb2.append("private String password = \"12345\";" + "\r\n");
        sb2.append("private String barra =\"\\\\\";" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private  StringBuilder createX3() {
        StringBuilder sb3 = new StringBuilder();
        sb3.append("    public void crearArchivo(String direccion, String escrito, String nombreArchivo) {" + "\r\n");
        sb3.append("         String carpetas = direccion;" + "\r\n");
        sb3.append("         String archivos = barra + nombreArchivo;" + "\r\n");
        sb3.append("         String contenido1 = escrito;" + "\r\n");
        sb3.append("         File create_carpeta = new File(carpetas);" + "\r\n");
        sb3.append("         File create_archivo = new File(carpetas + archivos);" + "\r\n");
        sb3.append("         if (create_archivo.exists()) {" + "\r\n");
        sb3.append("    } else {" + "\r\n");
        sb3.append("         create_carpeta.mkdirs();" + "\r\n");
        sb3.append("    try {" + "\r\n");
        sb3.append("    if (create_archivo.createNewFile()) {" + "\r\n");
        sb3.append("         FileWriter fw = new FileWriter(create_archivo);" + "\r\n");
        sb3.append("         BufferedWriter bw = new BufferedWriter(fw);" + "\r\n");
        sb3.append("         bw.write(contenido1);" + "\r\n");
        sb3.append("         bw.close();" + "\r\n");
        sb3.append("    } else {" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append("    } catch (IOException e) {" + "\r\n");
        sb3.append("         e.printStackTrace();" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append("    }" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }

    private  StringBuilder createX4() {
        StringBuilder sb4 = new StringBuilder();

        sb4.append("" + "\r\n");
        sb4.append("    public static byte[] readBytesFromFile(String filePaths) {");
        sb4.append("        String filePath = filePaths; ");
        sb4.append("        FileInputStream fileInputStream = null;");
        sb4.append("        byte[] bytesArray = null;");
        sb4.append("    try {");
        sb4.append("        File file = new File(filePath);");
        sb4.append("        bytesArray = new byte[(int) file.length()];");
        sb4.append("        fileInputStream = new FileInputStream(file);");
        sb4.append("        fileInputStream.read(bytesArray);");
        sb4.append("    } catch (IOException e) {");
        sb4.append("        e.printStackTrace();");
        sb4.append("    } finally {");
        sb4.append("        if (fileInputStream != null) {");
        sb4.append("        try {");
        sb4.append("         fileInputStream.close();");
        sb4.append("    } catch (IOException e) {");
        sb4.append("           e.printStackTrace();");
        sb4.append("    }");
        sb4.append("    }");
        sb4.append("    }");
        sb4.append("        return bytesArray;");
        sb4.append("    }");
        return sb4;
    }

    private  StringBuilder createX5() {
        StringBuilder sb5 = new StringBuilder();
        sb5.append("" + "\r\n");
        sb5.append(" public void borrarFolder(String directorio) {" );
        sb5.append("    File f = new File(directorio);");
        sb5.append("    this.borrarcarpetas(f);");
        sb5.append("        }");
        sb5.append("" + "\r\n");
        return sb5;
    }

    private  StringBuilder createX6() {
        StringBuilder sb6 = new StringBuilder();
        sb6.append("" + "\r\n");
        sb6.append("    private void borrarcarpetas(File fileDel) {");
        sb6.append("     if (fileDel.isDirectory()) {");
        sb6.append("        if (fileDel.list().length == 0) {");
        sb6.append("            fileDel.delete();");
        sb6.append("    } else {");
        sb6.append("        for (String temp : fileDel.list()) {");
        sb6.append("        File fileDelete = new File(fileDel, temp);");
        sb6.append("        borrarcarpetas(fileDelete);");
        sb6.append("    }");
        sb6.append("    if (fileDel.list().length == 0) {fileDel.delete();}");
        sb6.append("    }");
        sb6.append("    } else {");
        sb6.append("        fileDel.delete();");
        sb6.append("    }");
        sb6.append("    }");
        sb6.append("" + "\r\n");
        return sb6;
    }

    private  StringBuilder createX7() {
        StringBuilder sb7 = new StringBuilder();

        sb7.append("" + "\r\n");
        sb7.append("     public void folderzip(String carpetaAcomprimir, String direccionDeCarpeta, String nombreArchivoZip) throws Exception {" + "\r\n");
        sb7.append("        String folderpathZipInput = direccionDeCarpeta + carpetaAcomprimir; " + "\r\n");
        sb7.append("        String pathOutputZip = direccionDeCarpeta + nombreArchivoZip + \".zip\"; " + "\r\n");
        sb7.append("    try {" + "\r\n");
        sb7.append("        ZipFile zipFile = new ZipFile(pathOutputZip);" + "\r\n");
        sb7.append("        ZipParameters parameters = new ZipParameters();" + "\r\n");
        sb7.append("        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);" + "\r\n");
        sb7.append("        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);" + "\r\n");
        sb7.append("        parameters.setEncryptFiles(isEncryptFiles);" + "\r\n");
        sb7.append("        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);" + "\r\n");
        sb7.append("        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);" + "\r\n");
        sb7.append("        parameters.setPassword(password);" + "\r\n");
        sb7.append("        zipFile.addFolder(folderpathZipInput, parameters);" + "\r\n");
        sb7.append("// JOptionPane.showMessageDialog(null, \"El archivo zip se creo\");" + "\r\n");
        sb7.append("    } catch (ZipException e) {" + "\r\n");
       sb7.append("// JOptionPane.showMessageDialog(null, \"Error: \" + e.getMessage());" + "\r\n");
        sb7.append("        e.printStackTrace();" + "\r\n");
       sb7.append("// JOptionPane.showMessageDialog(null, \"El archivo zip no se creo \" + e);" + "\r\n");
        sb7.append("         }" + "\r\n");
        sb7.append("    }" + "\r\n");
        sb7.append("" + "\r\n");
        return sb7;
    }
    private  StringBuilder createX8() {
        StringBuilder sb8 = new StringBuilder();
        sb8.append("   " + "\r\n");
        sb8.append(" public String OsEjecutandose() {" + "\r\n");
        sb8.append(" String usar = \"\";" + "\r\n");
        sb8.append(" try {" + "\r\n");
        sb8.append("        String hostName = java.net.InetAddress.getLocalHost().getHostAddress();" + "\r\n");
        sb8.append("        InetAddress addr = InetAddress.getByName(hostName);" + "\r\n");
        sb8.append("        String hostname = addr.getHostName();" + "\r\n");
        sb8.append("        String so = System.getProperty(\"os.name\");" + "\r\n");
        sb8.append("        if (so.equals(\"Windows 10\")) {" + "\r\n");
        sb8.append("        usar = \"\\\\\";" + "\r\n");
        sb8.append("  }else {" + "\r\n");
        sb8.append("        usar = \"//\";" + "\r\n");
        sb8.append("  }" + "\r\n");
        sb8.append("        return usar;" + "\r\n");
        sb8.append("  } catch (UnknownHostException e) {" + "\r\n");
        sb8.append("        return \"NO SE EJECUTOEL SCRIP EL ERROR: \" + e;" + "\r\n");
        sb8.append("     }" + "\r\n");
        sb8.append("  }" + "\r\n");
        sb8.append("" + "\r\n");
        return sb8;
    }
}
