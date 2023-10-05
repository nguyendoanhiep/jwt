package com.tom.restaurant.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersDto {
    private Long id;
    private String code;
    private Long customerId;
    private String numberPhone;
    private Long voucherCodeId;
    private Long originalPrice;
    private Long discountAmount;
    private Long finalPrice;
    private List<Long> listProductId;
}
