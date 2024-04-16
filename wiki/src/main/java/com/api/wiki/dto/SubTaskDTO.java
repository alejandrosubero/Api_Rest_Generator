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
public class SubTaskDTO implements Serializable {

    private Long idSubTask;
    private Long IdProject;
    private Long taskReferenceId;
    private String state;
    private Long time; // hours
    private String Solution;
    private Date createDate;
    private Date endDate;

    private @Builder.Default List<TaskNoteDTO> taskNote = new ArrayList<>();
    private @Builder.Default List<PaquetePackageDTO> packages = new ArrayList<>();

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String titleSubTask;

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String taskType;

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String Description;

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String personCreate;

    @NotNull(message = "titleTask cannot be null")
    @NotBlank(message = "titleTask cannot be blank")
    private String personWorked;

}
