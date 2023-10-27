package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tom.restaurant.entity.OrdersProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    private List<OrdersProduct> ordersProducts;
}
