package com.tom.restaurant.entity.dto;

import lombok.Data;
@Data
public class CartResponse {
    private Long productId;
    private String name;
    private Integer quantity;
    private Long unitPrice;
    private Long totalPrice;

    public CartResponse(Long productId, String name, Integer quantity, Long unitPrice, Long totalPrice) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
}
