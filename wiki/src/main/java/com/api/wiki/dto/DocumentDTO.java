package com.api.wiki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO implements Serializable {
    private Long idDocument;
    private String title;
    private String content;
    private Date createDate;
    private String docReferentNumber;
    private String referentVersion;
    private String actualVersion;
    private Boolean active;
}
