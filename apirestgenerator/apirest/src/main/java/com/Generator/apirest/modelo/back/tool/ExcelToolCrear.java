package com.Generator.apirest.modelo.back.tool;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//@Scope("singleton")
@Component
public class ExcelToolCrear {

    private String proyectoName;
    private String paquete;
 //   private List<EntityPojo> entidades;
    private Creador creador;
    private String barra = java.nio.file.FileSystems.getDefault().getSeparator();
//    private int relantizar = SleepRelantizer.RELANTIZER;
//    private int relantizar2 = SleepRelantizer.RELANTIZERC;
//    private Boolean isServer;


    protected static final Log logger = LogFactory.getLog(ExcelToolCrear.class);

    public void startCreateExcelToolCrear(ArchivoBaseDatosPojo archivo, Creador creadors) {

    //    this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.paquete = creadors.getPackageNames();
        this.creador = creadors;
        this.createEx();
    }

    private void createEx() {
        String nameOfClass = "ExcelToolCrear";
        try {
          //  Thread.sleep(relantizar);
            String escritos = metods().toString();
         //   Thread.sleep(relantizar);
            createArchivo(escritos, nameOfClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createArchivo(String escrito, String nameOfClass) {

        try {
           // Thread.sleep(relantizar);
            String nombreArchivo = nameOfClass + ".java";
            String entidad_paquete = "serviceImplement";
            String direction = creador.getDireccionDeCarpeta() + proyectoName + barra + "src" + barra + "main" + barra
                    + "java" + barra + creador.getCom() + barra + creador.getPackageNames1() + barra + creador.getArtifact()
                    + barra + entidad_paquete;
           // Thread.sleep(relantizar2);
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    private StringBuffer metods() {
        StringBuffer sb = new StringBuffer("\r\n");
        logger.info("Create excel metodo de leer converter metodos ");
        try {
           // Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createImport());

           // Thread.sleep(relantizar);
            sb.append("\r\n");
            sb.append(this.createTituloClass());
            sb.append("\r\n");

           // Thread.sleep(relantizar);
            sb.append(this.createContructor());
            sb.append("\r\n");

           // Thread.sleep(relantizar);
            sb.append(this.createClass());
            sb.append("\r\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}" + "\r\n");
        return sb;
    }


    private StringBuffer createImport() {
        StringBuffer sb1 = new StringBuffer();

        sb1.append("package " + paquete + ".serviceImplement;" + "\r\n");
        sb1.append("import com.google.gson.Gson;" + "\r\n");
        sb1.append("import org.slf4j.Logger;" + "\r\n");
        sb1.append("import org.slf4j.LoggerFactory;" + "\r\n");
        sb1.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb1.append("import org.apache.commons.logging.LogFactory;" + "\r\n");

        sb1.append("import java.util.Date;" + "\r\n");
        sb1.append("import java.io.File;" + "\r\n");
        sb1.append("import java.io.FileInputStream;" + "\r\n");
        sb1.append("import java.util.Iterator;" + "\r\n");

        sb1.append("import java.io.IOException;" + "\r\n");
        sb1.append("import java.io.FileNotFoundException;" + "\r\n");
        sb1.append("import java.io.FileOutputStream;" + "\r\n");
        sb1.append("import org.apache.poi.ss.usermodel.CellStyle;" + "\r\n");
        sb1.append("import org.apache.poi.ss.usermodel.Font;" + "\r\n");
        sb1.append("import org.apache.poi.xssf.usermodel.XSSFCell;" + "\r\n");
        sb1.append("import org.apache.poi.xssf.usermodel.XSSFRow;" + "\r\n");
        sb1.append("import org.apache.poi.xssf.usermodel.XSSFSheet;" + "\r\n");
        sb1.append("import org.apache.poi.xssf.usermodel.XSSFWorkbook;" + "\r\n");
        sb1.append(" " + "\r\n");
        return sb1;
    }

    private StringBuffer createTituloClass() {
        StringBuffer sb2 = new StringBuffer();
        sb2.append("" + "\r\n");
        //  sb2.append("@Component" + "\r\n");
        sb2.append("public class ExcelToolCrear {" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append("    private static final Logger LOG = LoggerFactory.getLogger(ExcelToolCrear.class);" + "\r\n");
        sb2.append("" + "\r\n");
        sb2.append(" String nombreArchivo = \"Inventario.xlsx\";" + "\r\n");
        sb2.append(" String rutaArchivo = \"C:\\\\Ficheros-Excel\\\\\" + nombreArchivo;" + "\r\n");
        sb2.append(" String hoja = \"Hoja1\";" + "\r\n");
        sb2.append("" + "\r\n");
        return sb2;
    }


    private StringBuffer createContructor() {
        StringBuffer sb3 = new StringBuffer();
        sb3.append("" + "\r\n");
        sb3.append(" public ExcelToolCrear(String nombreArchivo, String rutaArchivo,String hoja ){" + "\r\n");
        sb3.append(" this.nombreArchivo = nombreArchivo;" + "\r\n");
        sb3.append(" this.rutaArchivo = rutaArchivo;" + "\r\n");
        sb3.append(" this.hoja = hoja;" + "\r\n");
        sb3.append(" }" + "\r\n");
        sb3.append("" + "\r\n");
        sb3.append("" + "\r\n");
        return sb3;
    }


    private StringBuffer createClass() {
        StringBuffer sb4 = new StringBuffer();
        sb4.append(" private void crearFicheros() {" + "\r\n");
        sb4.append("   try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {" + "\r\n");

        sb4.append(" XSSFWorkbook libro= new XSSFWorkbook();" + "\r\n");
        sb4.append(" XSSFSheet hoja1 = libro.createSheet(hoja);" + "\r\n");
        sb4.append("//cabecera de la hoja de excel" + "\r\n");
        sb4.append("String [] header= new String[]{\"Codigo\", \"Producto\",\"Precio\",\"Unidades\"};" + "\r\n");
        sb4.append("//contenido de la hoja de excel" + "\r\n");
        sb4.append("String [][] document= new String [][]{};" + "\r\n");
        sb4.append(" " + "\r\n");

        sb4.append("// Ejemplo" + "\r\n");

        sb4.append("/* String [][] document= new String [][]{\n" +
                "                {\"AP150\",\"ACCESS POINT TP-LINK TL-WA901ND 450Mbps Wireless N 1RJ45 10-100 3Ant.\",\"112.00\",\"50\"},\n" +
                "                {\"RTP150\",\"ROUTER TP-LINK TL-WR940ND 10-100Mbpps LAN WAN 2.4 - 2.4835Ghz\",\"19.60\",\"25\"},\n" +
                "                {\"TRT300\",\"TARJETA DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.\",\"10.68\",\"15\"},\n" +
                "                {\"TRT300\",\"DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.\",\"10.68\",\"15\"},\n" +
                "                {\"TR0\",\"DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.\",\"10.68\",\"15\"}\n" +
                "        };*/" + "\r\n");

        sb4.append("//poner negrita a la cabecera" + "\r\n");
        sb4.append("CellStyle style = libro.createCellStyle();" + "\r\n");
        sb4.append("Font font = libro.createFont();" + "\r\n");
        sb4.append(" font.setBold(true); " + "\r\n");
        sb4.append(" style.setFont(font); " + "\r\n");
        sb4.append(" " + "\r\n");
        sb4.append(" " + "\r\n");

        sb4.append(" //generar los datos para el documento " + "\r\n");
        sb4.append(" for (int i = 0; i <= document.length; i++) {  " + "\r\n");
        sb4.append("XSSFRow row=hoja1.createRow(i);//se crea las filas " + "\r\n");
        sb4.append("for (int j = 0; j <header.length; j++) { " + "\r\n");
        sb4.append("if (i==0) {//para la cabecera " + "\r\n");
        sb4.append("XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posicion " + "\r\n");
        sb4.append("cell.setCellStyle(style); // se a�ade el style crea anteriormente " + "\r\n");
        sb4.append("cell.setCellValue(header[j]);//se a�ade el contenido  " + "\r\n");
        sb4.append(" }else{//para el contenido " + "\r\n");
        sb4.append("XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posici�n " + "\r\n");
        sb4.append("cell.setCellValue(document[i-1][j]); //se a�ade el contenido " + "\r\n");
        sb4.append("} " + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("  }" + "\r\n");

        sb4.append(" " + "\r\n");
        sb4.append(" " + "\r\n");

        sb4.append(" File file; " + "\r\n");
        sb4.append("file = new File(rutaArchivo); " + "\r\n");
        sb4.append(" try (FileOutputStream fileOuS = new FileOutputStream(file)){ " + "\r\n");
        sb4.append("if (file.exists()) {// si el archivo existe se elimina " + "\r\n");
        sb4.append("file.delete();  " + "\r\n");
        sb4.append("System.out.println(\"Archivo eliminado\"); " + "\r\n");
        sb4.append("  }" + "\r\n");
        sb4.append("libro.write(fileOuS); " + "\r\n");
        sb4.append("fileOuS.flush(); " + "\r\n");
        sb4.append(" fileOuS.close(); " + "\r\n");
        sb4.append(" // System.out.println(\"Archivo Creado\"); " + "\r\n");
        sb4.append("  } catch (FileNotFoundException e) { " + "\r\n");
        sb4.append(" e.printStackTrace(); " + "\r\n");
        sb4.append("  }catch (IOException e) { " + "\r\n");
        sb4.append("  e.printStackTrace();" + "\r\n");
        sb4.append(" }" + "\r\n");
        sb4.append("  }" + "\r\n");
        sb4.append("   }" + "\r\n");
        sb4.append(" " + "\r\n");
        sb4.append(" " + "\r\n");

        return sb4;
    }

}






