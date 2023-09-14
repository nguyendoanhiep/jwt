package com.tom.restaurant.entity.dto;

import lombok.Data;

@Data
public class FormChangePassword {
    Long id;
    String currentPassword;
    String newPassword;
}
