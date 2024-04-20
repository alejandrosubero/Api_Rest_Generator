package com.api.wiki.mapper;


import com.api.wiki.dto.PaquetePackageDTO;

import com.api.wiki.entitys.PaquetePackage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperPaquetePackage {


    public PaquetePackage dtoToEntity(PaquetePackageDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, PaquetePackage.class);
//        }
//        return null;
        return dto != null?  new ModelMapper().map(dto, PaquetePackage.class): null;
    }

    public PaquetePackageDTO entityTodto(PaquetePackage entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, PaquetePackageDTO.class);
//        }
//        return null;
        return entity != null?new ModelMapper().map(entity, PaquetePackageDTO.class):null;
    }

    public List<PaquetePackage> listdtoToListEntity(List<PaquetePackageDTO> listDto) {
        List<PaquetePackage> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(DTO -> entitys.add(this.dtoToEntity(DTO)));
        }
        return entitys;
    }
}
