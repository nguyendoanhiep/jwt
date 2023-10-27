package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class VoucherDto {
    private Long id;
    private String name;
    private String code;
    private Long value;
    private Long quantity;
    private Integer status;
    private Long userCreateId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherStartDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherExpirationDate;
}
