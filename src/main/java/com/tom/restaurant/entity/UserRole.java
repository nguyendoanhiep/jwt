package com.tom.restaurant.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "role_id", nullable = false)
    private Long roleId;
}
