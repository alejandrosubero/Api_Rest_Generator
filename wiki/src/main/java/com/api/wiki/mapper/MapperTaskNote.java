package com.api.wiki.mapper;

import com.api.wiki.dto.TaskNoteDTO;
import com.api.wiki.entitys.TaskNote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperTaskNote {

    public TaskNote dtoToEntity(TaskNoteDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, TaskNote.class);
//        }
//        return null;
        return dto != null? new ModelMapper().map(dto, TaskNote.class): null;
    }

    public TaskNoteDTO entityToDto(TaskNote entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, TaskNoteDTO.class);
//        }
//        return null;
        return entity != null? new ModelMapper().map(entity, TaskNoteDTO.class):null;
    }

    public List<TaskNote> listdtoToListEntity(List<TaskNoteDTO> listDto) {
//        List<TaskNote> entitys = new ArrayList<>();
//        if (listDto != null && listDto.size() > 0) {
//            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
//        }
//        return entitys;
        return listDto.stream().map(taskNoteDTO -> this.dtoToEntity(taskNoteDTO)).collect(Collectors.toList());
    }

    public List<TaskNoteDTO> listEntityToListDTO(List<TaskNote> listEntitys) {
        return listEntitys.stream().map(taskNote -> this.entityToDto(taskNote)).collect(Collectors.toList());
    }

}
