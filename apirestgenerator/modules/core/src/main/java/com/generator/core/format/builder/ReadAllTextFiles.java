package com.generator.core.format.builder;

import java.io.*;

public class ReadAllTextFiles {


    public static String ReadSpecificFile(String folderPath, String fileName) {

        String textFromFile = "";
        File folder = new File(folderPath);
        File file = new File(folder, fileName);

        if (folder.exists() && folder.isDirectory()) {
            textFromFile = readFile(file);
        } else {
            textFromFile = "Error: Folder not found or cannot be accessed.";
        }
        return textFromFile;
    }

    public static String readFile(File file) {

        StringBuilder allText = new StringBuilder();
        String line;

        if (file.exists() && file.isFile()) {
//                System.out.println("Reading file: " + file.getName());
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while (true) {
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    allText.append(line).append("\n");
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error: File not found or cannot be accessed.");
        }
        return allText.toString();
    }

    public static File ReadSpecificFileReturnFile(String folderPath, String fileName) {
        String textFromFile = "";
        File folder = new File(folderPath);
        File file = new File(folder, fileName);
        return file;
    }

    /***
     *
     * @param folderPath = "/samples"
     * @param formatOfFile = ".txt"
     * @return
     */
    public static String ReadFromFolder(String folderPath, String formatOfFile) {

        String textFromFile = "";
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(formatOfFile)) {
                    textFromFile = readFile(file);
                }
            }
        } else {
            textFromFile = "Error: Folder not found or cannot be accessed.";
        }
        return textFromFile;
    }

}
