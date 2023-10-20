package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tom.restaurant.entity.OrdersProduct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class OrdersDto {
    private Long id;
    private String customerName;
    private String code;
    private String numberPhone;
    private Long voucherId;
    private String voucherName;
    private Long originalTotalValue;
    private Long discountAmount;
    private Long totalValue;
    private Integer status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    private List<OrdersProduct> ordersProducts;

    public OrdersDto(String customerName, String code, String numberPhone, String voucherName, Long originalTotalValue, Long discountAmount, Long totalValue, Integer status, Date createDate) {
        this.customerName = customerName;
        this.code = code;
        this.numberPhone = numberPhone;
        this.voucherName = voucherName;
        this.originalTotalValue = originalTotalValue;
        this.discountAmount = discountAmount;
        this.totalValue = totalValue;
        this.status = status;
        this.createDate = createDate;
    }
}
