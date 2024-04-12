package com.api.wiki.Contoller;

import com.api.wiki.dto.EntityRespone;
import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.mapper.MapperEntityRespone;
import com.api.wiki.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @PostMapping("/create")
    private ResponseEntity<EntityRespone> createProject(@RequestBody ProjectDTO projectDTO) {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(projectService.saveUpdate(projectDTO));
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/all")
    private ResponseEntity<EntityRespone> findByName() {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponseT(projectService.getAllProjects(), "", null);
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }
}
