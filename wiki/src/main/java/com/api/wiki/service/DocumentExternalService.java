package com.api.wiki.service;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Document;
import com.api.wiki.service.DocumentService;
import org.springframework.stereotype.Component;

@Component
public class DocumentExternalService {

    private static DocumentService documentService;

    public DocumentExternalService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public static DocumentDTO createDocumentDTOFromTask(TaskDTO taskDTO){
        return documentService.createDocumentDTOFromTask(taskDTO);
    }

}
