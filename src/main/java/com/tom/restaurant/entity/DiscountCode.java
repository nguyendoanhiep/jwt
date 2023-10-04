package com.tom.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "discount_code")
public class DiscountCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "value")
    private Long value;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "user_create_id")
    private Long userCreateId;
    @Column(name = "discount_start_date", nullable = false)
    private Date discountStartDate;
    @Column(name = "discount_expiration_date", nullable = false)
    private Date discountExpirationDate;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "discount_code_user",
            joinColumns = {@JoinColumn(name = "discount_code_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customers;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "discount_code_product",
            joinColumns = {@JoinColumn(name = "discount_code_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

}
