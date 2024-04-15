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
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    private Long time; // hours
    private String description;
    private String titleTask;
    private String state;
    private String solution;
    private String taskType;
    private Date createDate;
    private Date endDate;
    private String personCreate;
    private String personWorked;
    private String taskReferenceNumber;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_task_note")
    private @Builder.Default List<TaskNote> taskNote = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_task_sub_task")
    private @Builder.Default List<SubTask> subTasks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_task_paquete")
    private @Builder.Default List<PaquetePackage> packages = new ArrayList<>();

    @Column(name = "project_id_ref")
    private Long idProject;

}
