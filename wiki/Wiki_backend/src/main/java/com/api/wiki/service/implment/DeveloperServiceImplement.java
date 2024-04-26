package com.api.wiki.service.implment;

import com.api.wiki.dto.DeveloperDTO;
import com.api.wiki.entitys.Developer;
import com.api.wiki.mapper.MapperDeveloper;
import com.api.wiki.repository.DeveloperRepository;
import com.api.wiki.service.DeveloperService;
import com.api.wiki.utility.TaskSate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Boolean save(DeveloperDTO developerDTO) {
        try {
            developerDTO.setFullName(developerDTO.getName()+ " "+developerDTO.getLastName());
            Developer developer = developerRepository.save(mapperDeveloper.dtoToEntity(developerDTO));
            if (developer != null && developer.getIdDeveloper() != null){
                developer.setIngreseDate(new Date());
                developerRepository.save(developer);
                return true;
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }



    @Override
    public DeveloperDTO saveOrUpdate(DeveloperDTO developerDTO) {
        try {
            developerDTO.setFullName(developerDTO.getName()+ " "+developerDTO.getLastName());
            Developer developer = developerRepository.save(mapperDeveloper.dtoToEntity(developerDTO));

            if (developer != null && developer.getIdDeveloper() != null){
                developer.setIngreseDate(new Date());
                return mapperDeveloper.entityToDto(developerRepository.save(developer));
            }

        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }



    @Override
    public String checkDeveloper(String name) {
        try {
            Optional<Developer> developer = developerRepository.findByFullName(name);
            if (developer.isEmpty()){
                List<DeveloperDTO> developers = this.findByName(name);
                if(developers != null && developers.size() > 0 ){
                    return filterFirsFullNameOfList(developers);
                } else {
                   developers = this.findByLastName(name);
                    if(developers != null && developers.size() > 0 ) {
                        return filterFirsFullNameOfList(developers);
                    }
                }
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return TaskSate.IS_NO_FOUND.toString();
    }

    private String filterFirsFullNameOfList(List<DeveloperDTO> developers ){
        if (developers!= null && developers.size() > 0){
            return developers.get(0).getFullName();
        }
        return null;
    }

}
