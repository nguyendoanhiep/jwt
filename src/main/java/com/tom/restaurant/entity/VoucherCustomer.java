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
    @Column(name = "voucher_id")
    private Long voucherId;
    @Column(name = "customer_id")
    private Long customerId;
}
