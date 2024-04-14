package com.api.wiki.service.implment;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Document;
import com.api.wiki.mapper.MapperDocument;
import com.api.wiki.repository.DocumentRepository;
import com.api.wiki.service.DocumentService;
import com.api.wiki.utility.VersionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImplment implements DocumentService {


    private DocumentRepository documentRepository;
    private MapperDocument mapperDocument;

    @Autowired
    public DocumentServiceImplment(DocumentRepository documentRepository, MapperDocument mapperDocument) {
        this.documentRepository = documentRepository;
        this.mapperDocument = mapperDocument;
    }

    @Override
    public DocumentDTO findByIdDocument(Long id) {
        try {
            return mapperDocument.entityToDto(documentRepository.findById(id).orElse(null));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DocumentDTO findByTitle(String title) {
        try {
            return mapperDocument.entityToDto(documentRepository.findByTitle(title));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> findByDocReferentNumber(String docReferentNumber) {
        try {
            return mapperDocument.listentityToListDTO(documentRepository.findByDocReferentNumber(docReferentNumber));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> findByReferentVersion(String referentVersion) {
        try {
            return mapperDocument.listentityToListDTO(documentRepository.findByReferentVersion(referentVersion));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> findByActualVersion(String actualVersion) {
        try {
            return mapperDocument.listentityToListDTO(documentRepository.findByActualVersion(actualVersion));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> findByActive(Boolean active) {
        try {
            return mapperDocument.listentityToListDTO(documentRepository.findByActive(active));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentDTO> getAll() {
        try {
            return mapperDocument.listentityToListDTO(documentRepository.findAll());
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DocumentDTO createDocumentDTOFromTask(TaskDTO taskDTO) {

        //TODO: CREAR EL METODO PARA CREAR EL COMENTARIO DE LA DOCUMENTACION
        // cual es la ruler para referentVersion y cuando se set
        // generacion del docReferentNumber

        DocumentDTO documentDTO =  DocumentDTO.builder()
                .actualVersion(VersionConstant.NONE_VERSION)
                .referentVersion(VersionConstant.NONE_VERSION)
                .title(taskDTO.getTitleTask())
                .active(true)
                .content("")
                .createDate(new Date())
                .docReferentNumber("h3321-b")
                .build();

        return null;
    }
}
