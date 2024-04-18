package com.api.wiki.service.implment;

import com.api.wiki.businessrules.TaskBusinessRule;
import com.api.wiki.dto.DocumentDTO;
import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.entitys.Project;
import com.api.wiki.entitys.Task;
import com.api.wiki.mapper.MapperTask;
import com.api.wiki.repository.TaskRepository;
import com.api.wiki.service.DocumentExternalService;
import com.api.wiki.service.ProjectExternalService;
import com.api.wiki.service.ProjectService;
import com.api.wiki.service.TaskService;
import com.api.wiki.utility.TaskSate;
import com.api.wiki.utility.VersionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        List<TaskDTO> response = null;
        try {
            List<Task> taskList = this.taskRepository.findByState(state);
            if (taskList != null && !taskList.isEmpty()) {
                response = mapperTask.listEntityToListdto(taskList);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return response;
        }
    }

    @Override
    public List<TaskDTO> findByTaskType(String taskType) {
        List<TaskDTO> response = null;
        try {
            List<Task> taskList = this.taskRepository.findByTaskType(taskType);
            if (taskList != null && !taskList.isEmpty()) {
                response = mapperTask.listEntityToListdto(taskList);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return response;
        }
    }

    @Override
    public List<TaskDTO> getAll() {
        List<TaskDTO> response = null;
        try {
            response = mapperTask.listEntityToListdto(taskRepository.findAll());
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return response;
        }
    }

    @Override
    public TaskDTO saveOrUpdate(TaskDTO taskRecive) {
        try {
            if (taskRecive == null) {
                throw new IllegalArgumentException("the Task cannot be null or empty");
            }
            //TODO: falta el cambio de estado para la sub tarea .... nota esto hay que analizar
            // para que sea dinamico.

            TaskDTO taskDTO = this.validTaskSate(taskRecive);
            if (taskDTO.getTaskId() != null && !taskDTO.getState().equals(TaskSate.COMPLETE.toString())) {
                return saveOrUpdateSecctionUpdate(taskDTO);
            }
            if (taskDTO.getTaskId() != null && taskDTO.getState().equals(TaskSate.COMPLETE.toString())) {
                return saveOrUpdateSecctionCloseTask(taskDTO);
            }
            if (taskDTO.getTaskId() == null) {
                return saveOrUpdateSecctionNewTask(taskDTO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    private TaskDTO saveOrUpdateSecctionUpdate(TaskDTO taskDTO) {
        if (taskDTO.getSubTasks() != null && taskDTO.getSubTasks().size() > 0) {
            taskDTO.getSubTasks().stream().forEach(subTask -> {
                if (subTask.getTaskReferenceId() == null) {
                    subTask.setTaskReferenceId(taskDTO.getTaskId());
                }
            });
        }
        return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(taskDTO)));
    }

    private TaskDTO saveOrUpdateSecctionCloseTask(TaskDTO taskDTO) {
        taskDTO.setState(TaskSate.COMPLETE.toString());
        Task taskFinal = taskRepository.save(mapperTask.dtoToEntity(taskDTO));
        DocumentDTO buildDocument = DocumentExternalService.createDocumentDTOFromTask(taskDTO);
        ProjectExternalService.addDocumentToProjectInNoneVersion(buildDocument, VersionConstant.NONE_VERSION, taskDTO.getIdProject());
        return mapperTask.entityToDto(taskFinal);
    }

    private TaskDTO saveOrUpdateSecctionNewTask(TaskDTO taskDTO) {
        TaskDTO taskResponse = null;
        try {
            taskDTO.setState(TaskSate.CREATE.toString());
            taskDTO.setCreateDate(new Date());
            ProjectDTO projectUpdate = ProjectExternalService.addTaskToProject(taskDTO);
            Optional<TaskDTO> taskDTOOptional = findFirstTaskWithNullReferenceId(projectUpdate.getTaskList());
            if (taskDTOOptional.isPresent()) {
                TaskDTO taskOperationSet = taskDTOOptional.get();
                taskOperationSet.getSubTasks().forEach(subTaskDTO -> {
                    if (subTaskDTO.getTaskReferenceId() == null) {
                        subTaskDTO.setTaskReferenceId(taskOperationSet.getTaskId());
                    }
                    if (subTaskDTO.getState() == null || subTaskDTO.getState().equals("")) {
                        subTaskDTO.setState(TaskSate.CREATE.toString());
                    }
                });
                ProjectExternalService.buildNewVersionControl(projectUpdate.getIdProject());
                taskResponse = this.saveOrUpdate(taskOperationSet);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return taskResponse;
        }

        return this.validTaskSateOPUT(taskResponse);
    }


    private Optional<TaskDTO> findFirstTaskWithNullReferenceId(List<TaskDTO> tasks) {
        return tasks.stream()
                .filter(task -> task.getSubTasks().stream()
                        .anyMatch(subTask -> subTask.getTaskReferenceId() == null)
                ).findFirst();
    }

    @Override
    public List<TaskDTO> findByIdProject(Long idProject) {
        List<TaskDTO> response = null;
        try {
            if (idProject != null) {
                response = mapperTask.listEntityToListdto(taskRepository.findByIdProject(idProject));
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return response;
        }
    }

    public TaskDTO inicializaNewTask(TaskDTO task) {
        try {
            if (task.getState() == null) {
                task.setState(TaskSate.CREATE.toString());
                task.setCreateDate(new Date());
                if (task.getSubTasks() != null && task.getSubTasks().size() > 0) {
                    task.getSubTasks().stream().forEach(subTask -> subTask.setTaskReferenceId(task.getTaskId()));
                }
                ProjectExternalService.buildNewVersionControl(task.getIdProject());
                return mapperTask.entityToDto(taskRepository.save(mapperTask.dtoToEntity(task)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return task;
        }
        return task;
    }

}
