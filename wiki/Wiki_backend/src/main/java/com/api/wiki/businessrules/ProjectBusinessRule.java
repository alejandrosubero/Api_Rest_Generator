package com.api.wiki.businessrules;

import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.VersionControlDTO;
import com.api.wiki.utility.VersionConstant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ProjectBusinessRule {

    default VersionControlDTO getNewerVersion(List<VersionControlDTO> versionControlList) {

        if (versionControlList != null && versionControlList.size() > 0) {
            Optional<VersionControlDTO> maxVersionControlReference = versionControlList.stream()
                    .max(Comparator.comparing(VersionControlDTO::getVersion));
            if (maxVersionControlReference.isPresent()) {
                return maxVersionControlReference.get();
            }
        }
        return null;
    }

    default List<DocumentDTO> documentForNewVersionControl(List<DocumentDTO> documentList) {
        if (documentList != null && documentList.size() > 0) {
            documentList.stream().forEach(documentDTO -> documentDTO.setActualVersion(VersionConstant.NONE_VERSION));
        }
        return documentList;
    }


    default VersionControlDTO getNewVersionControl(List<DocumentDTO> documentList) {
        List<DocumentDTO> documentListForSet = null;

        if (documentList != null) {
            if (documentList.size() > 0) {documentListForSet = documentList;}
        } else {
            List<DocumentDTO> docList  = new ArrayList<DocumentDTO>();
            documentListForSet = docList;
        }
      return getversion(documentListForSet);

//        return VersionControlDTO.builder()
//                .version(VersionConstant.NONE_VERSION)
//                .description("...")
//                .documentList(this.documentForNewVersionControl(documentListForSet))
//                .build();
    }


    private VersionControlDTO getversion(List<DocumentDTO> documentList){
        VersionControlDTO versionControlList = null;

        if(documentList!= null && documentList.size() > 0){
            versionControlList =  VersionControlDTO.builder()
                    .version(VersionConstant.NONE_VERSION)
                    .description("...")
                    .documentList(this.documentForNewVersionControl(documentList))
                    .build();
        }else {
            versionControlList =  VersionControlDTO.builder()
                    .version(VersionConstant.NONE_VERSION)
                    .description("...")
                    .documentList(documentList)
                    .build();
        }
        return versionControlList;
    }

}
