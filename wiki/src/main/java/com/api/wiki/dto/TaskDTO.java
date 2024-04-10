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
public class TaskDTO implements Serializable {
    private Long taskId;
    private Long time;
    private String Description;
    private String titleTask;
    private String state;
    private String solution;
    private String taskType;
    private Date createDate;
    private Date endDate;
    private String personCreate;
    private String personWorked;
    private @Builder.Default List<TaskNoteDTO> taskNote = new ArrayList<>();
    private @Builder.Default List<SubTaskDTO> subTasks = new ArrayList<>();
    private @Builder.Default List<PaquetePackageDTO> packages = new ArrayList<>();
    private Long idProject;

}
