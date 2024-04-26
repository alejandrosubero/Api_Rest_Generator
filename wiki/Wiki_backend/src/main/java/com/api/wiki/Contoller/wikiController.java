package com.api.wiki.Contoller;

import com.api.wiki.dto.EntityRespone;
import com.api.wiki.mapper.MapperEntityRespone;
import com.api.wiki.utility.TaskSate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/control/wiki/")
public class wikiController {

    @Autowired
    private MapperEntityRespone mapperEntityRespone;

    @GetMapping("/check")
    private ResponseEntity<EntityRespone> findByName() {
        try {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(TaskSate.OK.toString(), "The app is "+ TaskSate.RUNNING.toString() , null);
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.OK);
        } catch (DataAccessException e) {
            EntityRespone entityRespone = mapperEntityRespone.setEntityResponT(null, "Ocurrio un error", e.getMessage());
            return new ResponseEntity<EntityRespone>(entityRespone, HttpStatus.BAD_REQUEST);
        }
    }


}
