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
    private Set<String> roles;
}
