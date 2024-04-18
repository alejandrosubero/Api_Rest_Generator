package com.api.wiki.businessrules;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.service.ProjectService;
import com.api.wiki.utility.TaskSate;

import java.util.Date;


public interface TaskBusinessRule {

    default public TaskDTO validTaskSate(TaskDTO taskDTO){
        if( taskDTO!= null && taskDTO.getTaskId() != null && taskDTO.getState() != null
                && taskDTO.getState().equals(TaskSate.CREATE.toString() ) || taskDTO.getState().equals(TaskSate.NEW.toString())) {
            taskDTO.setState(TaskSate.IN_PROGRESS.toString());
            return taskDTO;
        }
        return taskDTO;
    }


    default public TaskDTO validTaskSateOPUT(TaskDTO taskDTO){
        if(taskDTO.getTaskId() != null && taskDTO.getState().equals(TaskSate.CREATE.toString())) {
            taskDTO.setState(TaskSate.NEW.toString());
            taskDTO.getSubTasks().forEach(subTaskDTO -> {
                if(subTaskDTO.getState() != null || subTaskDTO.getState().equals(TaskSate.CREATE.toString())){
                    subTaskDTO.setState(TaskSate.NEW.toString());
                }
            });
            return taskDTO;
        }
        return taskDTO;
    }

}
