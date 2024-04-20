package com.api.wiki.services;

import com.api.wiki.dto.DeveloperDTO;
import com.api.wiki.entitys.Developer;
import com.api.wiki.mapper.MapperDeveloper;
import com.api.wiki.repository.DeveloperRepository;
import com.api.wiki.service.implment.DeveloperServiceImplement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private MapperDeveloper mapperDeveloper;

    @InjectMocks
    private DeveloperServiceImplement developerService;


    @Test
    public void saveDeveloperReturnDeveloperDTO(){

        Developer developer = Developer.builder().name("alejandro").lastName("subero").idDeveloper(1L).ingreseDate(new Date()).build();
        DeveloperDTO developerDTO = DeveloperDTO.builder().name("alejandro").lastName("subero").idDeveloper(2L).ingreseDate(new Date()).build();

        when(mapperDeveloper.dtoToEntity(developerDTO)).thenReturn(developer);
        when(developerRepository.save(Mockito.any(Developer.class))).thenReturn(developer);
        when(mapperDeveloper.entityToDto(developer)).thenReturn(developerDTO);

        DeveloperDTO saveDeveloperDTO = developerService.saveOrUpdate(developerDTO);

        Assertions.assertThat(saveDeveloperDTO).isNotNull();

    }

}
