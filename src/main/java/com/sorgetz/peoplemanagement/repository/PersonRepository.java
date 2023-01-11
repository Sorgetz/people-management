package com.sorgetz.peoplemanagement.repository;

import com.sorgetz.peoplemanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
