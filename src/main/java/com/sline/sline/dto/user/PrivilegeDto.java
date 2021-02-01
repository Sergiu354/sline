package com.sline.sline.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@ToString
public class PrivilegeDto {
    private Long id;
    private String name;
    private Collection<RoleDto> roles;
}
