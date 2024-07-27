package com.generator.core.format.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTextFile {

    public void create(String fileName, String folderName,String textContent) {

        File folder = new File(folderName);

        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("Folder \"" + folderName + "\" created.");
        }

        File file = new File(folder, fileName);
        writeTextInFile(file,textContent);
    }

  private void writeTextInFile(File file, String textContent){
      try {
          if (file.exists()) {
              System.out.println("File \"" + file.getName() + "\" already exists. Overwriting...");
          }
          // Create or overwrite file
          file.createNewFile();

          FileWriter writer = new FileWriter(file);
          writer.write(textContent);
          writer.close();
          System.out.println("File \"" + file.getName() + "\" created successfully!");
      } catch (IOException e) {
          System.out.println("Error creating file: " + e.getMessage());
      }
  }

}
