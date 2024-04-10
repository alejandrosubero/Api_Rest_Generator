package com.api.wiki.service;

import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.dto.TaskDTO;


import java.util.List;

public interface SubTaskService {

    public SubTaskDTO findByIdSubTask(Long subTaskId);
    public SubTaskDTO findByTitleSubTask(String titleTask);
    public List<SubTaskDTO> findByState(String state);
    public List<SubTaskDTO> findByTaskType (String taskType);
    public List<SubTaskDTO> getAll();
}
