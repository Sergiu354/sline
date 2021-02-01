package com.sline.sline.dto.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhoneDto {
    private Long id;
    private String uuid;
    private Boolean main = false;
    @ToString.Exclude
    private PersonDto person;
}
