package com.api.wiki.repository;

import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.entitys.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Project findByName(String name);
}
