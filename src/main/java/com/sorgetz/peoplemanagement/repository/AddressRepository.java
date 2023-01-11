package com.sorgetz.peoplemanagement.repository;

import com.sorgetz.peoplemanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByPerson_Id(Long personId);

    Optional<Address> findByMainAddress(Boolean value);

}
