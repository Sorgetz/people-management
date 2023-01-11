package com.sorgetz.peoplemanagement.dto.res;

import com.sorgetz.peoplemanagement.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResDTO {

    private Long id;

    private String publicPlace;

    private String zipCode;

    private String number;

    private String city;

    private Boolean mainAddress;

    private Long personId;

    public static AddressResDTO toRes(Address entity){
        return AddressResDTO
                .builder()
                .id(entity.getId())
                .publicPlace(entity.getPublicPlace())
                .zipCode(entity.getZipCode())
                .number(entity.getNumber())
                .city(entity.getCity())
                .mainAddress(entity.getMainAddress())
                .personId(entity.getPerson().getId())
                .build();
    }

}
