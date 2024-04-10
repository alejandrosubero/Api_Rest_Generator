package com.api.wiki.mapper;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.entitys.Document;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperDocument {
    public Document dtoToEntity(DocumentDTO dto) {
//        if (dto != null) {
//            return new ModelMapper().map(dto, Document.class);
//        }
//        return null;
        return   dto != null?  new ModelMapper().map(dto, Document.class): null;
    }

    public DocumentDTO entityToDto(Document entity) {
//        if (entity != null) {
//           return new ModelMapper().map(entity, DocumentDTO.class);
//        }
//        return null;
        return entity != null?new ModelMapper().map(entity, DocumentDTO.class):null;
    }

    public List<Document> listdtoToListEntity(List<DocumentDTO> listDto) {
        List<Document> entitys = new ArrayList<>();
        if (listDto != null && listDto.size() > 0) {
            listDto.forEach(documentDTO -> entitys.add(this.dtoToEntity(documentDTO)));
        }
        return entitys;
    }


    public List<DocumentDTO> listentityToListDTO(List<Document> listDto) {
      return listDto.stream().map(document -> this.entityToDto(document)).collect(Collectors.toList());
    }

}
