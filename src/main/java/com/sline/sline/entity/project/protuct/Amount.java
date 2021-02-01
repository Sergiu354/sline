package com.sline.sline.entity.project.protuct;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Amount")
@Getter
@Setter
@ToString
public class Amount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @ToString.Exclude
    @OneToOne(targetEntity = Type.class, fetch = FetchType.LAZY)
    private Type type;

    @Column
    private Integer amount;
}
