package com.api.wiki.service;

import com.api.wiki.businessrules.TaskBusinessRule;
import com.api.wiki.dto.TaskDTO;

public class TaskExternalService implements TaskBusinessRule {


    private static TaskService taskService;

    public TaskExternalService(TaskService taskService) {
        this.taskService = taskService;
    }

    public static TaskDTO inicializaNewTask(TaskDTO task){
        return taskService.inicializaNewTask(task);
    }


}
