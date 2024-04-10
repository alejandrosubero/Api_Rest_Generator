package com.api.wiki.service.implment;

import com.api.wiki.dto.DeveloperDTO;
import com.api.wiki.mapper.MapperDeveloper;
import com.api.wiki.repository.DeveloperRepository;
import com.api.wiki.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImplement implements DeveloperService {

    private DeveloperRepository developerRepository;
    private MapperDeveloper mapperDeveloper;

    @Autowired
    public DeveloperServiceImplement(DeveloperRepository developerRepository, MapperDeveloper mapperDeveloper) {
        this.developerRepository = developerRepository;
        this.mapperDeveloper = mapperDeveloper;
    }

    @Override
    public List<DeveloperDTO> findByName(String name) {
       try{
           return mapperDeveloper.listEntityToListDTO(developerRepository.findByName(name));
       }catch (DataAccessException e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public List<DeveloperDTO> findByLastName(String lastName) {
        try{
            return mapperDeveloper.listEntityToListDTO(developerRepository.findByLastName(lastName));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DeveloperDTO> getAll() {
        try{
            return mapperDeveloper.listEntityToListDTO(developerRepository.findAll());
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
