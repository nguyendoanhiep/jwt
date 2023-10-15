package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tom.restaurant.entity.Role;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String numberPhone;
    private Integer status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;
    private Set<Role> roles;

    public UserDto(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }
}
