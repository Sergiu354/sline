package com.sline.sline.dto.order;

import com.sline.sline.dto.person.PersonDto;
import com.sline.sline.dto.product.ProductDto;
import com.sline.sline.dto.user.UserDto;
import com.sline.sline.entity.project.order.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDto {
    private String uuid;
    @ToString.Exclude
    private PersonDto person;
    @ToString.Exclude
    private ProductDto product;
    private Date deliveryTime;
    private Date dateOrder;
    private Status status;
    @ToString.Exclude
    private UserDto user;
    private Integer amount;
    private Float price;
    private Float advance;
    private Float discount;
    private String description;
}
