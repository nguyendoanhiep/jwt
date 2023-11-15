package com.tom.restaurant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "voucher_customer")
public class VoucherCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "voucher_id", nullable = false)
    private Long voucherId;
    @Column(name = "number_phone", nullable = false)
    private String numberPhone;
}
