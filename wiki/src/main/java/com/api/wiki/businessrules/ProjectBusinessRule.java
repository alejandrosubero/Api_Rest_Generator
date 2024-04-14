package com.api.wiki.businessrules;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.VersionControlDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ProjectBusinessRule {

    default VersionControlDTO getNewerVersion(List<VersionControlDTO> versionControlList) {

       if (versionControlList != null && versionControlList.size() > 0){
           Optional<VersionControlDTO> maxVersionControlReference = versionControlList.stream()
                   .max(Comparator.comparing(VersionControlDTO::getVersion));
           if (maxVersionControlReference.isPresent()) {
               return maxVersionControlReference.get();
           }
        }
       return null;
    }

    default List<DocumentDTO> documentForNewVersionControl(List<DocumentDTO> documentList){
        documentList.stream().forEach(documentDTO -> );

    }

}
