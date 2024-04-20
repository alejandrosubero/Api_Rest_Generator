package com.api.wiki.service.implment;

import com.api.wiki.dto.TaskNoteDTO;
import com.api.wiki.mapper.MapperTaskNote;
import com.api.wiki.repository.TaskNoteRepository;
import com.api.wiki.service.TaskNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class TaskNoteServiceImplement implements TaskNoteService {

    private TaskNoteRepository taskNoteRepository;
    private MapperTaskNote mapperTaskNote;

    @Autowired
    public TaskNoteServiceImplement(TaskNoteRepository taskNoteRepository, MapperTaskNote mapperTaskNote) {
        this.taskNoteRepository = taskNoteRepository;
        this.mapperTaskNote = mapperTaskNote;
    }

    @Override
    public TaskNoteDTO findByIdTaskNote(Long idTaskNote) {
        try{
            return mapperTaskNote.entityToDto(taskNoteRepository.findById(idTaskNote).orElse(null));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public TaskNoteDTO findByTitleTaskNote(String titleTaskNote) {
        try{
            return mapperTaskNote.entityToDto(taskNoteRepository.findByTitleTaskNote(titleTaskNote));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TaskNoteDTO> getAll() {
        try{
            return mapperTaskNote.listEntityToListDTO(taskNoteRepository.findAll());
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
