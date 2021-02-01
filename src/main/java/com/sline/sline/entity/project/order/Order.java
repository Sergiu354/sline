package com.sline.sline.entity.project.order;

import com.sline.sline.entity.project.person.Person;
import com.sline.sline.entity.project.protuct.Product;
import com.sline.sline.entity.system.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column
    private Date deliveryTime;

    @Column
    private Date dateOrder;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private Integer amount;

    @Column
    private Float price;

    @Column
    private Float advance;

    @Column
    private Float discount;

    @Column(columnDefinition="TEXT")
    private String description;
}
