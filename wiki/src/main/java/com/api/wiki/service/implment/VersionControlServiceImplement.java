package com.api.wiki.service.implment;

import com.api.wiki.dto.VersionControlDTO;
import com.api.wiki.mapper.MapperVersionControl;
import com.api.wiki.repository.VersionControlRepository;
import com.api.wiki.service.VersionControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionControlServiceImplement implements VersionControlService {

    private VersionControlRepository versionControlRepository;
    private MapperVersionControl mapperVersionControl;

    @Autowired
    public VersionControlServiceImplement(VersionControlRepository versionControlRepository, MapperVersionControl versionControl) {
        this.versionControlRepository = versionControlRepository;
        this.mapperVersionControl = versionControl;
    }

    @Override
    public VersionControlDTO findByIdVersionControl(Long idVersionControl) {
        try{
            return mapperVersionControl.entityToDto(this.versionControlRepository.findById(idVersionControl).orElse(null));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<VersionControlDTO> findByVersion(String version) {
        try{
            return mapperVersionControl.listEntityToListDTO(this.versionControlRepository.findByVersion(version));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

}
