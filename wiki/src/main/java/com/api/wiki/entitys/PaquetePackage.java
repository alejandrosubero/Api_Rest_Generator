package com.api.wiki.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaquetePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaquetePackage;

    @Column(name = "package_name")
    private String packageName;

}
