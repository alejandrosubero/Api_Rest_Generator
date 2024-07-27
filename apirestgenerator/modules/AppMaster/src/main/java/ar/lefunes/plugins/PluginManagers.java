package ar.lefunes.plugins;




import com.generator.plugin.build.models.ModelOup;
import com.generator.plugin.interfaces.FileCreateService;
import com.generator.plugin.pojos.ArchivoBaseDatosPojo;
import com.generator.plugin.pojos.Creador;

import java.util.LinkedList;
import java.util.List;


public class PluginManagers {

    private FileCreateService fileService;
    private PluginLoader pluginLoader;

    public PluginManagers( PluginLoader pluginLoader) {
        this.fileService = new FileCreateService() {
            @Override
            public void crearArchivo(String s, String s1, String s2) {

            }

            @Override
            public void createFileClassJava(String s, String s1, StringBuffer stringBuffer, String s2) {

            }

            @Override
            public void createFileClass(String s, String s1, StringBuffer stringBuffer, String s2, String s3) {

            }

            @Override
            public void createFileClassJavaNoAddres(String s, String s1, StringBuffer stringBuffer) {

            }
        };
        this.pluginLoader = pluginLoader;
    }

    public PluginManagers(FileCreateService fileService, PluginLoader pluginLoader) {
        this.fileService = fileService;
        this.pluginLoader = pluginLoader;
    }

    public List<String> getAllModelsIdentifiers(){
        return this.pluginLoader.getModelsIdentifiers();
    }

    public List<String> updateModelsIdentifiers(){
        return this.pluginLoader.updateModelsIdentifiers();
    }

    public List<String> getModelMethods(String modelsIdentifiers) {
        return this.pluginLoader.getMethods(modelsIdentifiers);
    }

    public void executeBuild(ArchivoBaseDatosPojo baseFilePojo, Creador creator, String identifier){

        if(baseFilePojo != null && creator != null && identifier !=null) {

            LinkedList<ModelOup> executedModel = this.pluginLoader.executeGetModel(baseFilePojo, creator, identifier);

            for (ModelOup modelOup : executedModel) {
                if (modelOup.getPackageNane() == null) {
                    this.fileService.crearArchivo(
                            modelOup.getDirectoryForJava(),
                            modelOup.getClassInString(),
                            modelOup.getNameOfClass());
                }

                if (modelOup.getPackageNane() != null) {
                    this.fileService.createFileClassJava(
                            modelOup.getNameOfClass(),
                            modelOup.getPackageNane(),
                            new StringBuffer(modelOup.getClassInString()),
                            modelOup.getDirectoryForJava());
                }
            }
        }
    }

    public void  scanAndLoadPlugins(){
        this.pluginLoader.scanAndLoadPlugins();
    }

}
