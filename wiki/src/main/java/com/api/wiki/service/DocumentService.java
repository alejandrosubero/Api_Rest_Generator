package com.api.wiki.service;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.TaskDTO;

import java.util.List;

public interface DocumentService {

    public DocumentDTO findByIdDocument(Long id);
    public DocumentDTO findByTitle(String title);
    public List<DocumentDTO> findByDocReferentNumber(String docReferentNumber);
    public List<DocumentDTO> findByReferentVersion(String referentVersion);
    public List<DocumentDTO> findByActualVersion(String actualVersion);
    public List<DocumentDTO> findByActive(Boolean active);
    public List<DocumentDTO> getAll();
    public DocumentDTO createDocumentDTOFromTask(TaskDTO taskDTO);
}
