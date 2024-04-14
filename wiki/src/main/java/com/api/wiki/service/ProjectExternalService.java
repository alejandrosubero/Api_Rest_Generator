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

    public static void addDocument(DocumentDTO documentDTO, String version, Long id){
        projectService.addDocument(documentDTO,version,id);
    }

}

