package com.sorgetz.peoplemanagement.controller;

import com.sorgetz.peoplemanagement.dto.req.AddressReqDTO;
import com.sorgetz.peoplemanagement.dto.res.AddressResDTO;
import com.sorgetz.peoplemanagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping
    public AddressResDTO create(@RequestBody AddressReqDTO dto){
        return this.service.create(dto);
    }

    @GetMapping("/{personId}")
    public List<AddressResDTO> getAddressByPersonId(@PathVariable("personId") Long personId){
        return this.service.getAddressByPersonId(personId);
    }

    @PutMapping("/main/{id}")
    public void setMainAddress(@PathVariable("id") Long id){
        this.service.setMainAddress(id);
    }

}
