package com.api.wiki.businessrules;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.utility.TaskSate;

import java.util.Date;

public interface TaskBusinessRule {



    default public TaskDTO validTaskSate(TaskDTO taskDTO){

        if(taskDTO.getTaskId() == null && taskDTO.getState() == null || taskDTO.getState() != null) {
            taskDTO.setState(TaskSate.CREATE.toString());
            taskDTO.setCreateDate(new Date());
            return taskDTO;
        }
        if(taskDTO.getTaskId() != null && taskDTO.getState().equals(TaskSate.CREATE.toString())
                || taskDTO.getState().equals(TaskSate.NEW.toString())) {
            taskDTO.setState(TaskSate.IN_PROGRESS.toString());
            return taskDTO;
        }
        return taskDTO;
    }


    default public TaskDTO validTaskSateOPUT(TaskDTO taskDTO){
        if(taskDTO.getTaskId() != null && taskDTO.getState().equals(TaskSate.CREATE.toString())) {
            taskDTO.setState(TaskSate.NEW.toString());
            return taskDTO;
        }
        return taskDTO;
    }

}
