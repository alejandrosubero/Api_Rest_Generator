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
public class Project {

 @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_project")
    private Long idProject;

    private String name;
    private String repositoryLink;
    private String description;
    private Date startDate;
    private Long time; // horas

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id_doc")
    private @Builder.Default List<VersionControl> versionControlList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id_task")
    private @Builder.Default List<Task> taskList = new ArrayList<>();

}
