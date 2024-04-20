package com.api.wiki.service;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Task;

import java.util.List;

public interface TaskService {
    public TaskDTO findByTaskId(Long taskId);
    public TaskDTO findByTitleTask(String titleTask);
    public List<TaskDTO> findByState(String state);
    public List<TaskDTO> findByTaskType (String taskType);
    public List<TaskDTO> getAll();
    public TaskDTO inicializaNewTask(TaskDTO task);
    public TaskDTO saveOrUpdate(TaskDTO taskDTO);
    public List<TaskDTO> findByIdProject(Long idProject);
}
