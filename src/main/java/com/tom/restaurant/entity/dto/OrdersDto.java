package com.tom.restaurant.entity.dto;

import javax.persistence.Column;
import java.util.Date;

public class OrdersDto {
    private Long id;
    private String code;
    private Long customerId;
    private Long numberPhone;
    private Long fullName;
    private Long originalPrice;
    private Long discountAmount;
    private Long finalPrice;
    private Date createDate;
    private Date modifiedDate;
}
