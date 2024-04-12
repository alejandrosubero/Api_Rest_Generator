package com.Generator.apirest.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class BuilderBannerProyect {

    String escrito = "";
    String direccion="";
    String nombre="";
    StringBuilder builder2 = new StringBuilder();

    public BuilderBannerProyect(String direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }


    public void builderBannercreate() {

        BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.BOLD, 17));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(nombre.toUpperCase(), 5, 24);
        try {
            ImageIO.write(image, "png", new File("text.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int y = 0; y < 32; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 144; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "@" : "@");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            builder2 .append(sb + "\n");
            // System.out.println(sb);
        }
        System.out.println(builder2 );
        crearArchivo(builder2);
    }


    public void crearArchivo( StringBuilder builder) {

        String carpetas = direccion;
        String archivos = "banner.txt";
        String FilePath = carpetas+archivos;
        File create_carpeta = new File(carpetas);
        File create_archivo = new File(carpetas + archivos);

        if (create_archivo.exists()) {

        } else {
            create_carpeta.mkdirs();
            try {
                if (create_archivo.createNewFile()) {
                    FileWriter fw = new FileWriter(create_archivo);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(String.valueOf(builder));
                    bw.close();
                    this.removeEmptyLines(FilePath);
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeEmptyLines(String FilePath) throws IOException {

        File inputFile = new File(FilePath);
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String inputFileReader;
        ArrayList<String> DataArray = new ArrayList<String>();
        while((inputFileReader=reader.readLine())!=null){
            if(inputFileReader.length()==0){
                continue;
            }
            DataArray.add(inputFileReader);
        }
        reader.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FilePath));
        for(int i=0;i<DataArray.size();i++){
            bw.write(DataArray.get(i));
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

}

