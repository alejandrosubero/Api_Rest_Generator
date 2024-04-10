package com.api.wiki.service.implment;

import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.entitys.Project;
import com.api.wiki.mapper.MapperProject;
import com.api.wiki.repository.ProjectRepository;
import com.api.wiki.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImplemet implements ProjectService {

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
}
