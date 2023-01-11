package com.sorgetz.peoplemanagement.dto.req;

import lombok.Data;

@Data
public class AddressReqDTO {

    private String publicPlace;

    private String zipCode;

    private String number;

    private String city;

    private Long personId;

}
