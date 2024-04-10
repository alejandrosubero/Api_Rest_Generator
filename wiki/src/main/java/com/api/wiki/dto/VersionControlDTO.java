package com.api.wiki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersionControlDTO {

    private Long idVersionControl;
    private String version;
    private String description;
    private @Builder.Default List<DocumentDTO> documentList = new ArrayList<>();
}
