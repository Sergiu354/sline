package com.sline.sline.dto.person;

import com.sline.sline.dto.order.OrderDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class PersonDto {
    private String uuid;
    private String lastName;
    private String firstName;
    private String companyName;
    private String idno;
    private Boolean company;
    private AddressDto address;
    @ToString.Exclude
    private Set<PhoneDto> phones = new HashSet<>();
    @ToString.Exclude
    private OrderDto order;
}
