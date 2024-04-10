package com.api.wiki.dto;

import com.api.wiki.entitys.Task;
import com.api.wiki.entitys.VersionControl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO implements Serializable {
    private Long idProject;
    private String name;
    private String repositoryLink;
    private String description;
    private Date startDate;
    private Long time;
    private @Builder.Default List<VersionControlDTO> versionControlList = new ArrayList<>();
    private @Builder.Default List<TaskDTO> taskList = new ArrayList<>();
}
