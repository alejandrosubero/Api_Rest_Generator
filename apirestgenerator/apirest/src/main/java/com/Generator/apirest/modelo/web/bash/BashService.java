package com.Generator.apirest.modelo.web.bash;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class BashService {

	public BashService() {	}
	
	public static void startBash(String projectName) {
		File file = generateWebProject(projectName); 
	}
	
	
	private static  File generateWebProject(String projectName) {
        try {
           
            File file = new File(projectName);
            file.getAbsolutePath();
            String command = "cmd /c  start ng new  " + projectName + "Web --strict=true --routing=true --style=scss --skip-install";
            Process child = Runtime.getRuntime().exec(command);

            // Get output stream to write from it
            OutputStream out = child.getOutputStream();
            System.out.println(out);

            out.write("cd C:/ /r/n".getBytes());
            out.flush();
            out.write("dir /r/n".getBytes());
            out.close();
            return file;
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }


//	public static void main(String[] args) {
//	generateWebProject("pruebasAlejandro2");
//}
	
}
