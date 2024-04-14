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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    public void buildNewVersionControl(ProjectDTO project) {
        VersionControlDTO versionControl = this.getNewerVersion(project.getVersionControlList());
        VersionControlDTO newVersionControl = VersionControlDTO.builder()
                .version("0.0.0")
                .description("...")
                .documentList(this.documentForNewVersionControl(versionControl.getDocumentList()))
                .build();
        project.getVersionControlList().add(newVersionControl);
        ProjectDTO projectDto = this.saveUpdate(project);
    }

    @Override
    public void addDocument(DocumentDTO documentDTO, String version, ProjectDTO project) {
        try{
            Optional<VersionControlDTO> versionControl = project.getVersionControlList().stream()
                    .filter(versionControlDTO -> versionControlDTO.getIdVersionControl().equals(version)).findFirst();
            project.getVersionControlList().stream().forEach(elementVersionControl -> {
                if(elementVersionControl.getVersion().equals(version)){
                    elementVersionControl.getDocumentList().add(documentDTO);
                }
            });
            this.saveUpdate(project);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
