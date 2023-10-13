package com.tom.restaurant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "voucher_product")
public class VoucherProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "voucher_id")
    private Long voucherId;
    @Column(name = "product_id")
    private Long productId;
}
