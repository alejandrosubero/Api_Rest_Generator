package com.api.wiki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTOModel implements Serializable {
    private String titleD;
    private String packageD;
    private String typeD;
    private String injectD;
    private String extendsD;
    private String implementsD;
    private String useD;
    private String descriptionD;
}
