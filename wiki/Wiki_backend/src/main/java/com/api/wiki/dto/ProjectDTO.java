package com.api.wiki.dto;

import com.api.wiki.entitys.Task;
import com.api.wiki.entitys.VersionControl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String repositoryLink;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String description;

    private Date startDate;

    private Long time;
    private @Builder.Default List<VersionControlDTO> versionControlList = new ArrayList<>();
    private @Builder.Default List<TaskDTO> taskList = new ArrayList<>();
}

