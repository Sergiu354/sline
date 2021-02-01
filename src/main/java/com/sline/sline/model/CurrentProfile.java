package com.sline.sline.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Locale;

@Getter
@Setter
@ToString
public class CurrentProfile extends User {
    private final String uuid;
    private String fullName;
    private String imageAvatar;
    private Locale locale;

    public CurrentProfile(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.uuid = username;
    }
}
