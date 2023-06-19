package com.tom.mycat.entity.dto;

import com.tom.mycat.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer age;
    private String gender;
    private String city;
    private Integer status;
    private Set<Role> roles;
}
