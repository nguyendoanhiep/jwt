package com.tom.mycat.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormRegister {
    private String name;
    private String email;
    private String username;
    private String password;
    private Set<String> roles;
}
