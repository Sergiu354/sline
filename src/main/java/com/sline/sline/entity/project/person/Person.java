package com.sline.sline.entity.project.person;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sline.sline.entity.project.order.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String companyName;

    @Column(unique = true)
    private String idno;

    @Column
    private Boolean company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "person")
    private Set<Phone> phones = new HashSet<>();

    @ToString.Exclude
    @OneToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    private Order order;
}
