package com.Generator.apirest.controller;


import com.Generator.apirest.files.PluginManagers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resource")
public class ResourceController {

    protected static final Log logger = LogFactory.getLog(ResourceController.class);

    private PluginManagers pluginManagers;

    public ResourceController(PluginManagers pluginManagers) {
        this.pluginManagers = pluginManagers;
    }

    @GetMapping("/identifiers")
    public List<String> getAllModelsIdentifiers() {
        logger.info("Loading Models Identifiers");
        return this.pluginManagers.getAllModelsIdentifiers();
    }


    @GetMapping("/identifiers/methods/{identifier}")
    public List<String> getModelMethods(@PathVariable String identifier) {
        logger.info("Loading Models Methods");
        return this.pluginManagers.getModelMethods(identifier);
    }



}
