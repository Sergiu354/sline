package com.sline.sline.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Locale;

@Getter
@Setter
@ToString
public class UserDto {
    private String uuid;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarImage;
    private String password;
    private String secretText;
    private Locale language;
    private boolean enabled;
    private Collection<RoleDto> roles;
}
