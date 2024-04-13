package com.api.wiki.Contoller;

import com.api.wiki.dto.DeveloperDTO;
import com.api.wiki.dto.EntityRespone;
import com.api.wiki.dto.TaskDTO;
import com.api.wiki.mapper.MapperEntityRespone;
import com.api.wiki.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/task")
public class TaskController {

    private TaskService taskService;
    private MapperEntityRespone mapperEntityRespone;

    public TaskController(TaskService taskService, MapperEntityRespone mapperEntityRespone) {
        this.taskService = taskService;
        this.mapperEntityRespone = mapperEntityRespone;
    }

    @PostMapping("/save")
    private ResponseEntity<EntityRespone> saveOrUpdateTask(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                EntityRespone entityRespone = mapperEntityRespone.setEntityResponseT(bindingResult.getAllErrors(), "Ocurrio un error", null);
                return ResponseEntity.badRequest().body(entityRespone);
            }
            return ResponseEntity.ok(mapperEntityRespone.setEntityTobj(taskService.saveOrUpdate(taskDTO)));
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return ResponseEntity.badRequest().body(entityRespone);
        }
    }


}
