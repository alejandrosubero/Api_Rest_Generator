package com.api.wiki.service;

import com.api.wiki.dto.VersionControlDTO;

import java.util.List;

public interface VersionControlService {
    public VersionControlDTO findByIdVersionControl(Long idVersionControl);
    public List<VersionControlDTO> findByVersion(String version);
}
