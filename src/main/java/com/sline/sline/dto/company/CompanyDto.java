package com.sline.sline.dto.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sline.sline.dto.order.OrderDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.user.UserDto;
import com.sline.sline.entity.project.protuct.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class CompanyDto {
    private String uuid;
    @ToString.Exclude
    @JsonManagedReference
    private Set<ProductDto> products = new HashSet<>();
    @ToString.Exclude
    @JsonManagedReference
    private Set<OrderDto> orders = new HashSet<>();
    private Set<UserDto> user;
}
