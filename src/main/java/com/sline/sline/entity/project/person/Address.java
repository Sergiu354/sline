package com.sline.sline.entity.project.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String city;

    @Column
    private String street;

    @Column(length = 10)
    private String nrHouse;

    @Column(length = 10)
    private String apartment;

    @Column(length = 10)
    private String entrance;

    @Column()
    private Integer floor;

    @ToString.Exclude
    @OneToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    private Person person;

}
