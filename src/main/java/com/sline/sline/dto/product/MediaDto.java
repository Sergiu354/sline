package com.sline.sline.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MediaDto {
    private String uuid;
    private String link;
    private String path;
    private Integer point;
    private boolean principal;
    @ToString.Exclude
    @JsonBackReference
    private TypeDto type;
}
