package com.sorgetz.peoplemanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate birthdate;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

}
