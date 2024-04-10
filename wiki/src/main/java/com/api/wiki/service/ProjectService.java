package com.api.wiki.service;

import com.api.wiki.dto.ProjectDTO;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    public List<ProjectDTO> getAllProjects();
    public ProjectDTO findByName(String name);

    public ProjectDTO saveUpdate( ProjectDTO project);
}
