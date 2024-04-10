package com.api.wiki.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDTO implements Serializable {

    private Long idDeveloper;
    private String name;
    private String lastName;
    private java.util.Date ingreseDate;

}
