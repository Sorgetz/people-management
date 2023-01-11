package com.sorgetz.peoplemanagement.dto.res;

import com.sorgetz.peoplemanagement.model.Person;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PersonResDTO {

    private Long id;

    private String name;

    private LocalDate birthdate;

    private List<AddressResDTO> addresses;

    public static PersonResDTO toRes(Person entity){
        return PersonResDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthdate(entity.getBirthdate())
                .addresses(entity.getAddresses().stream().map(AddressResDTO::toRes)
                        .sorted(Comparator.comparing(AddressResDTO::getMainAddress, Comparator.reverseOrder()))
                        .collect(Collectors.toList()))
                .build();
    }

}
