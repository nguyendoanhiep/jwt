package com.tom.restaurant.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "customer")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "city")
    private String city;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "image_id")
    private Long imageId;
    @Column(name = "loyalty_points")
    private Long loyaltyPoints;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Image image;
}