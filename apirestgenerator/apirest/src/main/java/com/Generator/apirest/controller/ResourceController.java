package com.Generator.apirest.controller;


import com.Generator.apirest.files.PluginLoader;
import com.Generator.apirest.files.PluginManager;
import com.Generator.apirest.files.PluginManagers;
import com.Generator.apirest.files.PluginResourceLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resource")
public class ResourceController {

    protected static final Log logger = LogFactory.getLog(ResourceController.class);

    @Autowired
    private PluginManager pluginManager;

    @Autowired
    private PluginManagers pluginManagers;

    @Autowired
    PluginLoader pluginLoader;

    @GetMapping("/identifiers")
    public List<String> getAllModelsIdentifiers() {
        logger.info("Loading Models Identifiers");
//        pluginManager.loadPlugins();
//        List<String> list = new ArrayList<>();
//        pluginManager.getPluginsMap().keySet().forEach(s ->  list.add(s));
//        pluginLoader.getModelsIdentifiers();

        return  pluginManagers.getAllModelsIdentifiers();
    }


//    @GetMapping("/identifiers/methods/{identifier}")
//    public List<String> getModelMethods(@PathVariable String identifier) {
//        this.pluginManagers.scanAndLoadPlugins();
//        logger.info("Loading Models Methods");
//        return this.pluginManagers.getModelMethods(identifier);
//    }



}
