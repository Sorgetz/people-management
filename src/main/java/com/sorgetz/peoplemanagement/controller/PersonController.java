package com.sorgetz.peoplemanagement.controller;

import com.sorgetz.peoplemanagement.dto.req.PersonReqDTO;
import com.sorgetz.peoplemanagement.dto.res.PersonResDTO;
import com.sorgetz.peoplemanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    public PersonResDTO create(@RequestBody PersonReqDTO dto){
        return this.service.create(dto);
    }

    @PutMapping("/{id}")
    public PersonResDTO update(@RequestBody PersonReqDTO dto, @PathVariable("id") Long id){
        return this.service.update(dto, id);
    }

    @GetMapping("/{id}")
    public PersonResDTO getById(@PathVariable("id") Long id){
        return this.service.getById(id);
    }

    @GetMapping
    public List<PersonResDTO> getAll(){
        return this.service.getAll();
    }

}
