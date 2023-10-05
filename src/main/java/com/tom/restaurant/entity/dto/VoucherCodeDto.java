package com.tom.restaurant.entity.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class VoucherCodeDto {
    private Long id;
    private String name;
    private String code;
    private Long value;
    private Integer status;
    private Long userCreateId;
    private Date voucherStartDate;
    private Date voucherExpirationDate;
    private List<Long> listCustomerId;
    private List<Long> listProductId;
}
