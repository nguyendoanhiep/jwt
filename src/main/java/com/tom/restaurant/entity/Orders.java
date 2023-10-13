package com.tom.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@Builder
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "voucher_id")
    private Long voucherId;

    @Column(name = "original_price")
    private Long originalPrice;

    @Column(name = "discount_amount")
    private Long discountAmount;

    @Column(name = "final_price")
    private Long finalPrice;

    @Column(name = "create_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;

    @Column(name = "modified_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_code_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Voucher voucher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_product",
            joinColumns = {@JoinColumn(name = "orders_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
}
