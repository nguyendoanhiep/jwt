package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String numberPhone;
    private Integer status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;
    private Set<String> roles;
}
