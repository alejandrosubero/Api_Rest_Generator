package com.api.wiki.mapper;

import com.api.wiki.dto.DeveloperDTO;

import com.api.wiki.entitys.Developer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperDeveloper {

    public Developer dtoToEntity(DeveloperDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, Developer.class);
//        }
//        return null;
        return dto != null?  new ModelMapper().map(dto, Developer.class): null;
    }

    public DeveloperDTO entityToDto(Developer entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, DeveloperDTO.class);
//        }
//        return null;
        return entity != null?new ModelMapper().map(entity, DeveloperDTO.class):null;
    }

    public List<Developer> listdtoToListEntity(List<DeveloperDTO> listDto) {
        List<Developer> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }

    public List<DeveloperDTO> listEntityToListDTO(List<Developer> listEntity) {
//        List<DeveloperDTO> ListDTO = new ArrayList<>();
//        if (listEntity != null && listEntity.size() > 0) {
//            listEntity.forEach(entity -> ListDTO.add(this.entityToDto(entity)));
//        }
//        return ListDTO;
        return listEntity.stream().map(developer -> this.entityToDto(developer)).collect(Collectors.toList());


    }

}
