package com.api.wiki.dto;

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
public class TaskDTO implements Serializable {
    private Long taskId;
    private Long time;
    private String state;
    private String solution;
    private Date createDate;
    private Date endDate;

    private @Builder.Default List<TaskNoteDTO> taskNote = new ArrayList<>();
    private @Builder.Default List<SubTaskDTO> subTasks = new ArrayList<>();
    private @Builder.Default List<PaquetePackageDTO> packages = new ArrayList<>();

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String titleTask;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "taskType cannot be null")
    @NotBlank(message = "taskType cannot be blank")
    private String taskType;

    @NotNull(message = "personCreate cannot be null")
    @NotBlank(message = "personCreate cannot be blank")
    private String personCreate;

    @NotNull(message = "personWorked cannot be null")
    @NotBlank(message = "personWorked cannot be blank")
    private String personWorked;

    @NotNull(message = "Description cannot be null")
    private Long idProject;

}
