package com.api.wiki.service;

import com.api.wiki.dto.DeveloperDTO;



import java.util.List;

public interface DeveloperService {
    public List<DeveloperDTO> findByName(String name);
    public List<DeveloperDTO> findByLastName(String lastName);
    public List<DeveloperDTO> getAll();
    public Boolean save(DeveloperDTO developerDTO);
    public String checkDeveloper(String name);
    public DeveloperDTO saveOrUpdate(DeveloperDTO developerDTO);
}
