package com.api.wiki.service.implment;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Task;
import com.api.wiki.mapper.MapperTask;
import com.api.wiki.repository.TaskRepository;
import com.api.wiki.service.TaskService;
import com.api.wiki.utility.TaskSate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImplement implements TaskService {

    private TaskRepository taskRepository;
    private MapperTask mapperTask;

    @Autowired
    public TaskServiceImplement(TaskRepository taskRepository, MapperTask mapperTask) {
        this.taskRepository = taskRepository;
        this.mapperTask = mapperTask;
    }

    @Override
    public TaskDTO findByTaskId(Long taskId) {
        return this.mapperTask.entityToDto(this.taskRepository.findById(taskId).orElse(null));
    }

    @Override
    public TaskDTO findByTitleTask(String titleTask) {
        try {
            Task task = this.taskRepository.findByTitleTask(titleTask);
            if(task != null){
                return this.mapperTask.entityToDto(task);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> findByState(String state) {
        try{
            List<Task> taskList = this.taskRepository.findByState(state);
            if(taskList != null && !taskList.isEmpty()){
                return mapperTask.listEntityToListdto(taskList);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> findByTaskType(String taskType) {

        try{
            List<Task> taskList = this.taskRepository.findByTaskType(taskType);
            if(taskList != null && !taskList.isEmpty()){
                return mapperTask.listEntityToListdto(taskList);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> getAll() {
        try{
            return mapperTask.listEntityToListdto(taskRepository.findAll());
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskDTO saveOrUpdate(TaskDTO taskDTO) {

        try {
            if(taskDTO.getTaskId() != null && !taskDTO.getState().equals(TaskSate.COMPLETE)){
               return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(taskDTO)));
            }

            if(taskDTO.getTaskId() == null && taskDTO.getState() != null || !taskDTO.getState().equals(TaskSate.COMPLETE)){
              taskDTO.setCreateDate(new Date());
              taskDTO.setState();
                return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(taskDTO)));
            }

        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }



        return null;
    }

}
