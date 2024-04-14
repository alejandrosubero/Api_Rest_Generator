package com.api.wiki.service.implment;

import com.api.wiki.businessrules.TaskBusinessRule;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Task;
import com.api.wiki.mapper.MapperTask;
import com.api.wiki.repository.TaskRepository;
import com.api.wiki.service.ProjectExternalService;
import com.api.wiki.service.ProjectService;
import com.api.wiki.service.TaskService;
import com.api.wiki.utility.TaskSate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplement implements TaskService, TaskBusinessRule {

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
            if (task != null) {
                return this.mapperTask.entityToDto(task);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> findByState(String state) {
        try {
            List<Task> taskList = this.taskRepository.findByState(state);
            if (taskList != null && !taskList.isEmpty()) {
                return mapperTask.listEntityToListdto(taskList);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> findByTaskType(String taskType) {

        try {
            List<Task> taskList = this.taskRepository.findByTaskType(taskType);
            if (taskList != null && !taskList.isEmpty()) {
                return mapperTask.listEntityToListdto(taskList);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TaskDTO> getAll() {
        try {
            return mapperTask.listEntityToListdto(taskRepository.findAll());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskDTO saveOrUpdate(TaskDTO taskRecive) {
        try {
            //Validation
            TaskDTO taskDTO = this.validTaskSate(taskRecive);
            //Update


            if (taskDTO.getTaskId() != null && !taskDTO.getState().equals(TaskSate.COMPLETE.toString())) {
                return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(taskDTO)));
            }

            if (taskDTO.getTaskId() != null && taskDTO.getState().equals(TaskSate.COMPLETE.toString())) {
                //TODO: IN THIS POINT START TO CREATE A DOCUMEN OFF TASK AND SUBTASK
                //TODO: add document to the proyect.
                
                return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(taskDTO)));
            }
            //Sve a new
            if (taskDTO.getTaskId() == null) {
                Task task = taskRepository.save(mapperTask.dtoToEntity(taskDTO));
                if (task.getSubTasks() != null && task.getSubTasks().size() > 0) {
                    task.getSubTasks().stream().forEach(subTask -> subTask.setTaskReferenceId(task.getTaskId()));
                }
                ProjectExternalService.buildNewVersionControl(task.getIdProject());
                return mapperTask.entityToDto(taskRepository.save(task));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
