package com.api.wiki.service.implment;

import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.mapper.MapperSubTask;
import com.api.wiki.repository.SubTaskRepository;
import com.api.wiki.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskImplement implements SubTaskService {

    private SubTaskRepository taskRepository;
    private MapperSubTask mapperSubTask;

    @Autowired
    public SubTaskImplement(SubTaskRepository taskRepository, MapperSubTask mapperSubTask) {
        this.taskRepository = taskRepository;
        this.mapperSubTask = mapperSubTask;
    }


    @Override
    public SubTaskDTO findByIdSubTask(Long subTaskId) {
        try{
            return mapperSubTask.entityToDto(taskRepository.findById(subTaskId).orElse(null));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public SubTaskDTO findByTitleSubTask(String titleTask) {
        try{
            return mapperSubTask.entityToDto(taskRepository.findByTitleSubTask(titleTask));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTaskDTO> findByState(String state) {
        try{
            return mapperSubTask.listEntityToListDTO(taskRepository.findByState(state));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTaskDTO> findByTaskType(String taskType) {
        try{
            return mapperSubTask.listEntityToListDTO(taskRepository.findByTaskType(taskType));
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTaskDTO> getAll() {
        try{
            return mapperSubTask.listEntityToListDTO(taskRepository.findAll());
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
