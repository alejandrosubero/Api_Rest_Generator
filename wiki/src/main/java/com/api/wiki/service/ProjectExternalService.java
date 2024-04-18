package com.api.wiki.service;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Project;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProjectExternalService {

    private static ProjectService projectService;

    public ProjectExternalService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public static void buildNewVersionControl(Long id) {
        projectService.buildNewVersionControl(id);
    }

    public static void addDocumentToProjectInNoneVersion(DocumentDTO documentDTO, String version, Long id) {
        projectService.addDocument(documentDTO, version, id);
    }

    public static ProjectDTO addTaskToProject(TaskDTO taskDTO) {
        ProjectDTO projectUpdate = null;
        try {
            ProjectDTO project = projectService.findProjectById(taskDTO.getIdProject());
            if (project != null) {
                taskDTO.setIdProject(project.getIdProject());
                if (taskDTO.getSubTasks() != null && taskDTO.getSubTasks().size() > 0) {
                    taskDTO.getSubTasks().stream().forEach(subTask -> {
                        subTask.setIdProject(project.getIdProject());
                        subTask.setCreateDate(new Date());
                    });
                }
                project.getTaskList().add(taskDTO);
                projectUpdate = projectService.saveUpdate(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return projectUpdate;
        }
        return projectUpdate;
    }

    public static void generateVersionEnd() {
        // TODO: cual es la ruler para referentVersion y actualVersion == cuando VersionConstant.NONE_VERSION
//        ...
    }
}

