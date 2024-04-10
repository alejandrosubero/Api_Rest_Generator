package com.api.wiki.repository;

import com.api.wiki.entitys.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    public Document findByIdDocument(Long id);

    public Document findByTitle(String title);

    public List<Document> findByDocReferentNumber(String docReferentNumber);

    public List<Document> findByReferentVersion(String referentVersion);

    public List<Document> findByActualVersion(String actualVersion);

    public List<Document> findByActive(Boolean active);

}
