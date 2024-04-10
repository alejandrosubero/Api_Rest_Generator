package com.api.wiki.mapper;


import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.entitys.SubTask;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperSubTask {


    public SubTask dtoToEntity(SubTaskDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, SubTask.class);
//        }
//        return null;
        return dto != null?  new ModelMapper().map(dto, SubTask.class): null;
    }

    public SubTaskDTO entityToDto(SubTask entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, SubTaskDTO.class);
//        }
//        return null;
        return entity != null? new ModelMapper().map(entity, SubTaskDTO.class):null;
    }

    public List<SubTask> listdtoToListEntity(List<SubTaskDTO> listDto) {
        List<SubTask> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }

    public List<SubTaskDTO> listEntityToListDTO(List<SubTask> listEntity) {
        List<SubTaskDTO> listDTO = new ArrayList<>();
        if (listEntity != null && listEntity.size() > 0) {
            listEntity.forEach(entity -> listDTO.add(this.entityToDto(entity)));
        }
        return listDTO;
    }
}
