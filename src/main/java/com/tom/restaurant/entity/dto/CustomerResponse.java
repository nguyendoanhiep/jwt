package com.tom.restaurant.entity.dto;

import lombok.Data;


@Data
public class CustomerResponse {
    private Long id;

    private String name;

    private String numberPhone;

    private Integer status;

    private Long loyaltyPoints;

    private Long voucherId;

    public CustomerResponse(Long id, String name, String numberPhone, Integer status, Long loyaltyPoints, Long voucherId) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.status = status;
        this.loyaltyPoints = loyaltyPoints;
        this.voucherId = voucherId;
    }
}
