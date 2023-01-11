package com.sorgetz.peoplemanagement.service;

import com.sorgetz.peoplemanagement.dto.req.PersonReqDTO;
import com.sorgetz.peoplemanagement.dto.res.PersonResDTO;
import com.sorgetz.peoplemanagement.model.Address;
import com.sorgetz.peoplemanagement.model.Person;
import com.sorgetz.peoplemanagement.repository.PersonRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonResDTO create(PersonReqDTO dto){
        if(dto.getName() == null) throw new ServiceException("User name cannot be null");

        Person person = new Person();
        person.setName(dto.getName());
        person.setBirthdate(dto.getBirthdate());
        person.setAddresses(new ArrayList<>());

        this.repository.save(person);

        return PersonResDTO.toRes(person);
    }

    public PersonResDTO update(PersonReqDTO dto, Long id){
        Person person = this.getEntityById(id);

        BeanUtils.copyProperties(dto, person);
        this.repository.save(person);

        return PersonResDTO.toRes(person);
    }

    public PersonResDTO getById(Long id){
        return PersonResDTO.toRes(this.getEntityById(id));
    }

    public List<PersonResDTO> getAll(){
        return this.repository.findAll().stream()
                .map(PersonResDTO::toRes)
                .collect(Collectors.toList());
    }

    public void addAddress(Address address, Long personId){
        Person person = this.getEntityById(personId);
        List<Address> addresses = person.getAddresses();
        addresses.add(address);
        person.setAddresses(addresses);
    }

    public Person getEntityById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new ServiceException("Person not found"));
    }

}
