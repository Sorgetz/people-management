package com.sorgetz.peoplemanagement.service;

import com.sorgetz.peoplemanagement.dto.req.AddressReqDTO;
import com.sorgetz.peoplemanagement.dto.res.AddressResDTO;
import com.sorgetz.peoplemanagement.model.Address;
import com.sorgetz.peoplemanagement.repository.AddressRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private PersonService personService;

    public AddressResDTO create(AddressReqDTO dto){

        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        address.setPerson(this.personService.getEntityById(dto.getPersonId()));
        address.setMainAddress(Boolean.FALSE);

        this.repository.save(address);

        return AddressResDTO.toRes(address);
    }

    public List<AddressResDTO> getAddressByPersonId(Long personId) {

        List<Address> addresses = this.repository.findByPerson_Id(personId);

        return addresses.stream().map(AddressResDTO::toRes)
                .sorted(Comparator.comparing(AddressResDTO::getMainAddress, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public void setMainAddress(Long id){
        Optional<Address> oldMainAddress = this.repository.findByMainAddress(Boolean.TRUE);

        if(oldMainAddress.isPresent()){
            if(oldMainAddress.get().getId() == id)
                throw new ServiceException("This address is already the main address");

            this.changeMainAddressValue(oldMainAddress.get(), Boolean.FALSE);
        }

        Address mainAddress = this.repository.findById(id)
                .orElseThrow(() -> new ServiceException("Address not found"));
        this.changeMainAddressValue(mainAddress, Boolean.TRUE);
    }

    private void changeMainAddressValue(Address address, Boolean value){
        address.setMainAddress(value);
        this.repository.save(address);
    }

}
