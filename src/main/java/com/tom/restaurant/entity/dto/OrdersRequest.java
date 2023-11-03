package com.tom.restaurant.entity.dto;

import com.tom.restaurant.entity.OrdersProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrdersRequest {
    private Long id;
    private String code;
    private String numberPhone;
    private Long voucherId;
    private Long originalTotalValue;
    private Long discountAmount;
    private Long totalValue;
    private Integer status;
    private List<OrdersProduct> ordersProducts;
}
