package com.api.wiki.businessrules;

import com.api.wiki.dto.TaskDTO;
import com.api.wiki.service.ProjectService;
import com.api.wiki.utility.TaskSate;

import java.util.Date;


//TODO: NO ESTA CLARO LOS TIEMPOS DE EJECUSION POR QUE UNA TAREA NO SE PUEDE SALVAR INICIALMENTE SIN SALVAR EL PROYECTO
// PARA LA SUBTAREA SURGE LA PREGUNTA DEL ESTADO Y EL TIEMPO EN QUE SE DEBE DE SALVAR.

public interface TaskBusinessRule {

    default public TaskDTO validTaskSate(TaskDTO taskDTO){
        if(taskDTO.getTaskId() != null && taskDTO.getState() == null && taskDTO.getState().equals(TaskSate.CREATE.toString() )
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
