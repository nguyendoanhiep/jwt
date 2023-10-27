package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class CustomerDto {

    private Long id;

    private String name;

    private String numberPhone;

    private String email;

    private String gender;

    private String address;

    private Integer status;

    private String urlImage;

    private Long loyaltyPoints;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateOfBirth;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;
}
