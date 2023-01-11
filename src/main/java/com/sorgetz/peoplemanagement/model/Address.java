package com.sorgetz.peoplemanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String publicPlace;

    private String zipCode;

    private String number;

    private String city;

    private Boolean mainAddress;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
