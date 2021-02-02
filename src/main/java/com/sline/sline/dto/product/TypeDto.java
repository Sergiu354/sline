package com.sline.sline.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class TypeDto {
    private String uuid;
    private String name;
    @ToString.Exclude
    @JsonManagedReference
    private Set<MediaDto> medias;
    @ToString.Exclude
    @JsonBackReference
    private ProductDto product;
    @JsonManagedReference
    private AmountDto amount;

}
