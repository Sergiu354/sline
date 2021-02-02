package com.sline.sline.entity.project.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sline.sline.entity.project.order.Order;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.system.Role;
import com.sline.sline.entity.system.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private Set<Product> products = new HashSet<>();

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "company_users",
            joinColumns = @JoinColumn(
                    name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();
}
