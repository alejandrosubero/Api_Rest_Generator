package com.api.wiki.Contoller;

import com.api.wiki.dto.DeveloperDTO;
import com.api.wiki.dto.EntityRespone;
import com.api.wiki.dto.ProjectDTO;
import com.api.wiki.mapper.MapperEntityRespone;
import com.api.wiki.service.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private MapperEntityRespone mapperEntityRespone;


    @PostMapping("/create")
    private ResponseEntity<EntityRespone> createDeveloper(@Valid @RequestBody DeveloperDTO developerDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                EntityRespone entityRespone = mapperEntityRespone.setEntityResponseT(bindingResult.getAllErrors(), "Ocurrio un error", null);
                return ResponseEntity.badRequest().body(entityRespone);
            }
                return ResponseEntity.ok(mapperEntityRespone.setEntityTobj(developerService.save(developerDTO)));
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return ResponseEntity.badRequest().body(entityRespone);
        }
    }


    @GetMapping("/check/{developerName}")
    private ResponseEntity<EntityRespone> findByCodeEmployee(@PathVariable("developerName") String developerName) {
        try {
            if(developerName == null){
                EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "the variable is null", "developer = null Error");
                return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
            }

            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj(developerService.checkDeveloper(developerName));
            return ResponseEntity.ok( entityRespone);

        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/check")
    private ResponseEntity<EntityRespone> findByCodeEmployee() {
        try {

            EntityRespone entityRespone = mapperEntityRespone.setEntityTobj("check ok");
            return ResponseEntity.ok( entityRespone);

        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }

}
