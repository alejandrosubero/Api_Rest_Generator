package com.api.wiki.Contoller;

import com.api.wiki.dto.EntityRespone;
import com.api.wiki.dto.SubTaskDTO;
import com.api.wiki.mapper.MapperEntityRespone;
import com.api.wiki.service.SubTaskService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sub/task")
public class SubTaskController {

    private MapperEntityRespone mapperEntityRespone;
    private SubTaskService subTaskService;


    public SubTaskController(MapperEntityRespone mapperEntityRespone, SubTaskService subTaskService) {
        this.mapperEntityRespone = mapperEntityRespone;
        this.subTaskService = subTaskService;
    }

    @PostMapping("/save")
    private ResponseEntity<EntityRespone> saveOrUpdateTask(@Valid @RequestBody SubTaskDTO taskDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                EntityRespone entityRespone = mapperEntityRespone.setEntityResponseT(bindingResult.getAllErrors(), "Ocurrio un error", null);
                return ResponseEntity.badRequest().body(entityRespone);
            }
            return ResponseEntity.ok(mapperEntityRespone.setEntityTobj(subTaskService.saveOrUpdate(taskDTO)));
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return ResponseEntity.badRequest().body(entityRespone);
        }
    }

    @GetMapping("/all")
    private ResponseEntity<EntityRespone> findByName() {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponseT(subTaskService.getAll(), "", null);
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }
}
