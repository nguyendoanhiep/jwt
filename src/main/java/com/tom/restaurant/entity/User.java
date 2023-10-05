package com.tom.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "number_phone" , length = 20)
    private String numberPhone;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "create_date", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_phone", referencedColumnName = "number_phone", updatable = false, insertable = false)
    private Customer customer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
}
