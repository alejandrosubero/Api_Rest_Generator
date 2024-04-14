package com.api.wiki.service;

import com.api.wiki.dto.DocumentDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectExternalService {

    private static ProjectService projectService;

    public ProjectExternalService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public static void buildNewVersionControl(Long id){
        projectService.buildNewVersionControl(id);
    }

    public static void addDocumentToProjectInNoneVersion(DocumentDTO documentDTO, String version, Long id){
        projectService.addDocument(documentDTO,version,id);
    }

    public static void generateVersionEnd(){
        // TODO: cual es la ruler para referentVersion y actualVersion == cuando VersionConstant.NONE_VERSION
//        ...
    }
}

