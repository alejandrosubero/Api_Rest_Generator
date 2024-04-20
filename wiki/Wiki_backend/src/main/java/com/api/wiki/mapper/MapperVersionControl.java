package com.api.wiki.mapper;

import com.api.wiki.dto.VersionControlDTO;
import com.api.wiki.entitys.VersionControl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperVersionControl {

    public VersionControl dtoToEntity(VersionControlDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, VersionControl.class);
//        }
//        return null;
        return dto != null? new ModelMapper().map(dto, VersionControl.class): null;
    }

    public VersionControlDTO entityToDto(VersionControl entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, VersionControlDTO.class);
//        }
//        return null;
        return entity != null? new ModelMapper().map(entity, VersionControlDTO.class):null;
    }

    public List<VersionControl> listdtoToListEntity(List<VersionControlDTO> listDto) {
        List<VersionControl> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }

    public List<VersionControlDTO> listEntityToListDTO(List<VersionControl> listEntity) {
//        List<VersionControl> entitys = new ArrayList<>();
//        if (listDto != null && listDto.size() > 0) {
//            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
//        }
//        return entitys;
        try {
            return listEntity.stream().map(versionControl -> this.entityToDto(versionControl)).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
