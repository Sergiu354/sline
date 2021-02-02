package com.sline.sline.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sline.sline.dto.company.CompanyDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class ProductDto {
    private String uuid;
    private String name;
    @ToString.Exclude
    @JsonManagedReference
    private Set<TypeDto> types = new HashSet<>();
    private String description;
    private Float price;
    private Float discount;
    @JsonBackReference
    private CompanyDto company;
}
