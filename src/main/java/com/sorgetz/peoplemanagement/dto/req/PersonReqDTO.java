package com.sorgetz.peoplemanagement.dto.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonReqDTO {

    private String name;

    private LocalDate birthdate;

}
