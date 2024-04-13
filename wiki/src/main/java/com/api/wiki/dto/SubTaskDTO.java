package com.api.wiki.dto;


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
    private String titleSubTask;
    private Long IdProject;
    private Long taskReferenceId;
    private String state;
    private String taskType;
    private Long time; // hours
    private String Description;
    private String Solution;
    private Date createDate;
    private Date endDate;
    private String personCreate;
    private String personWorked;
    private @Builder.Default List<TaskNoteDTO> taskNote = new ArrayList<>();
    private @Builder.Default List<PaquetePackageDTO> packages = new ArrayList<>();

}
