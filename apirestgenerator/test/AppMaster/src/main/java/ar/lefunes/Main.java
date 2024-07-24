package ar.lefunes;

import ar.lefunes.plugins.PluginLoader;
import ar.lefunes.plugins.PluginManagers;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        PluginLoader loader = new PluginLoader();
        PluginManagers pluginManagers = new PluginManagers(loader);


        List<String> ident =  pluginManagers.getAllModelsIdentifiers();
        for (String ind : ident){
            System.out.println(ind);
        }

    }
}