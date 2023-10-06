package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date voucherStartDate;
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date voucherExpirationDate;
    private List<Long> listCustomerId;
    private List<Long> listProductId;
}
