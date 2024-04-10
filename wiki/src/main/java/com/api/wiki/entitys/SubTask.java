package com.api.wiki.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubTask;
    private String titleSubTask;
    private Long IdProject;
    private String state;
    private String taskType;
    private Long time; // hours
    private String description;
    private String solution;
    private Date createDate;
    private Date endDate;
    private String personCreate;
    private String personWorked;
    private String taskReference;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_sub_task")
    private @Builder.Default List<TaskNote> taskNote = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_sub_task")
    private @Builder.Default List<PaquetePackage> packages = new ArrayList<>();

}
