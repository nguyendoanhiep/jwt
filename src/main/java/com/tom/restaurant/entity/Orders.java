package com.tom.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "number_phone")
    private Long numberPhone;
    @Column(name = "full_name")
    private Long fullName;
    @Column(name = "original_price")
    private Long originalPrice;
    @Column(name = "discount_amount")
    private Long discountAmount;
    @Column(name = "final_price")
    private Long finalPrice;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_product",
            joinColumns = {@JoinColumn(name = "orders_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
}
