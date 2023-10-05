package com.tom.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "number_phone",length = 20)
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
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Image image;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_orders",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "orders_id")})
    private List<Orders> orders;
}
