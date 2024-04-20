package com.api.wiki.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VersionControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVersionControl;
    private String version;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "version_control_id")
    private @Builder.Default List<Document> documentList = new ArrayList<>();

}
