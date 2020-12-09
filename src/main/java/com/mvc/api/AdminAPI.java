package com.mvc.api;

import com.mvc.dto.NewDTO;
import com.mvc.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "newAPIOfAdmin")
public class AdminAPI {

    @Autowired
    private INewService newService;

    @PostMapping("/api/new")
    public NewDTO createNew(@RequestBody NewDTO newDTO) {
        return newService.save(newDTO);
    }

    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO updateNew) {
        return newService.save(updateNew);
    }

    @DeleteMapping("/api/new")
    public void deleteNew(@RequestBody long[] ids) {
        newService.delete(ids);
    }
}