package com.sline.sline.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sline.sline.entity.project.company.Company;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false, length = 50, unique = true)
    private String userName;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column
    private String avatarImage;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String secretText;

    @Column
    private Locale language;

    @Column
    private boolean enabled;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Collection<Company> company;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
