package com.sline.sline.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AmountDto {
    private String uuid;
    @ToString.Exclude
    @JsonBackReference
    private TypeDto type;
    private Integer amount;

}
