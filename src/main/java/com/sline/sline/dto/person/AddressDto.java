package com.sline.sline.dto.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    private String uuid;
    private String city;
    private String street;
    private String nrHouse;
    private String apartment;
    private String entrance;
    private Integer floor;
    private PersonDto person;
}
