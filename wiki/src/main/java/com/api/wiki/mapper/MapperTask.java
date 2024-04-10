package com.api.wiki.mapper;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperTask {

    public Task dtoToEntity(TaskDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, Task.class);
//        }
//        return null;
        return dto != null? new ModelMapper().map(dto, Task.class): null;
    }

    public TaskDTO entityToDto(Task entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, TaskDTO.class);
//        }
//        return null;
        return entity != null? new ModelMapper().map(entity, TaskDTO.class): null;
    }

    public List<Task> listdtoToListEntity(List<TaskDTO> listDto) {
        List<Task> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }

    public List<TaskDTO> listEntityToListdto(List<Task> entityList) {
        List<TaskDTO> dtoList = new ArrayList<>();
        if (entityList != null && entityList.size() > 0) {
            entityList.forEach(entidad -> dtoList.add(this.entityToDto(entidad)));
        }
        return dtoList;
    }

}
