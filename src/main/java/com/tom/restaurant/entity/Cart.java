package com.tom.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "number_phone" , length = 20 , unique = true)
    private String numberPhone;
    @Column(name = "voucher_code_id")
    private Long voucherCodeId;
    @Column(name = "original_price")
    private Long originalPrice;
    @Column(name = "discount_amount")
    private Long discountAmount;
    @Column(name = "final_price")
    private Long finalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_code_id", referencedColumnName = "id", updatable = false, insertable = false)
    private VoucherCode voucherCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_product",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

}
