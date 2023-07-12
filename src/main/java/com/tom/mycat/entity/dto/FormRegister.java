package com.tom.mycat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormRegister {
    private String username;
    private String password;
    private String numberPhone;
    private String fullName;
    private Set<String> roles;
}
