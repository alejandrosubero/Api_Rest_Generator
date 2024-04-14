package com.api.wiki.service.implment;

import com.api.wiki.businessrules.ProjectBusinessRule;
import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.dto.VersionControlDTO;
import com.api.wiki.entitys.Document;
import com.api.wiki.entitys.Project;
import com.api.wiki.entitys.VersionControl;
import com.api.wiki.mapper.MapperProject;
import com.api.wiki.repository.ProjectRepository;
import com.api.wiki.service.ProjectService;
import com.api.wiki.utility.VersionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProjectServiceImplemet implements ProjectService, ProjectBusinessRule {

    private ProjectRepository projectRepository;
    private MapperProject mapperProject;


    @Autowired
    public ProjectServiceImplemet(ProjectRepository projectRepository, MapperProject mapperProject) {
        this.projectRepository = projectRepository;
        this.mapperProject = mapperProject;

    }


    @Override
    public List<ProjectDTO> getAllProjects() {
        return mapperProject.listEntityToListDTO(projectRepository.findAll());
    }

    @Override
    public ProjectDTO findByName(String name) {
        return mapperProject.entityToDto(projectRepository.findByName(name));
    }

    @Override
    public ProjectDTO saveUpdate(ProjectDTO project) {
        ProjectDTO projectDto = null;
        Project projectBase = null;
        try {
            if (project != null) {
                if (project.getIdProject() == null || project.getVersionControlList() == null
                        || project.getVersionControlList().size() > 0) {
                    project.setVersionControlList(new ArrayList<VersionControlDTO>());
                    project.getVersionControlList().add(this.getNewVersionControl(new ArrayList<DocumentDTO>()));
                }
                projectBase = mapperProject.dtoToEntity(project);
                projectDto = mapperProject.entityToDto(projectRepository.save(projectBase));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return projectDto;
    }

    @Override
    public void buildNewVersionControl(Long id) {
        Optional<VersionControlDTO> vControl = null;
        try {
            ProjectDTO project = mapperProject.entityToDto(projectRepository.findById(id).orElse(null));
            if (project != null) {
                vControl = project.getVersionControlList().stream().filter(versionControlDTO ->
                                versionControlDTO.getVersion().equals(VersionConstant.NONE_VERSION)).findFirst();
                if (vControl.isEmpty()) {
                    VersionControlDTO newVersionControl = this.getNewVersionControl(
                            this.getNewerVersion(project.getVersionControlList()).getDocumentList());
                    project.getVersionControlList().add(newVersionControl);
                    this.saveUpdate(project);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocument(DocumentDTO documentDTO, String version, Long id) {
        try {
            ProjectDTO project = mapperProject.entityToDto(projectRepository.findById(id).orElse(null));
            if (project != null && project.getIdProject() != null) {

                project.getVersionControlList().stream().forEach(elementVersionControl -> {
                    if (elementVersionControl.getVersion().equals(version)) {
                        if (elementVersionControl.getDocumentList() != null) {
                            elementVersionControl.getDocumentList().add(documentDTO);
                        } else {
                            elementVersionControl.setDocumentList(new ArrayList<DocumentDTO>());
                            elementVersionControl.getDocumentList().add(documentDTO);
                        }
                    }
                });
                this.saveUpdate(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProjectDTO findProjectById(Long id) {
        try {
            this.mapperProject.entityToDto(this.projectRepository.findById(id).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
