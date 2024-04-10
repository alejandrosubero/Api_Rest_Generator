package com.api.wiki.mapper;


import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.entitys.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperProject {

    public Project dtoToEntity(ProjectDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, PaquetePackage.class);
//        }
//        return null;
        return dto != null?  new ModelMapper().map(dto, Project.class): null;
    }

    public ProjectDTO entityToDto(Project entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, PaquetePackageDTO.class);
//        }
//        return null;
        return entity != null? new ModelMapper().map(entity, ProjectDTO.class):null;
    }

    public List<Project> listdtoToListEntity(List<ProjectDTO> listDto) {
        List<Project> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }

    public List<ProjectDTO> listEntityToListDTO(List<Project> listEntity) {
      return listEntity.stream().map(project -> this.entityToDto(project)).collect(Collectors.toList());
    }
}
