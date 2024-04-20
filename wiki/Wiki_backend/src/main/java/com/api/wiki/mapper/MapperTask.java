package com.api.wiki.mapper;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperTask {

    public Task dtoToEntity(TaskDTO dto) {
        return dto != null? new ModelMapper().map(dto, Task.class): null;
    }

    public TaskDTO entityToDto(Task entity) {
        return entity != null? new ModelMapper().map(entity, TaskDTO.class): null;
    }

    public List<Task> listdtoToListEntity(List<TaskDTO> listDto) {
        if (listDto != null && listDto.size() > 0) {
            return listDto.stream().map(taskDTO -> this.dtoToEntity(taskDTO)).collect(Collectors.toList());
        }
        return null;
    }

    public List<TaskDTO> listEntityToListdto(List<Task> entityList) throws Exception {
        if (entityList != null && entityList.size() > 0) {
            return entityList.stream().map(task -> this.entityToDto(task)).collect(Collectors.toList());
        }
        return null;
    }
}
