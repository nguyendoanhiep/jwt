package com.tom.mycat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Long price;
    @Column(name = "status" , nullable = false)
    private Integer status;
    @Column(name = "type")
    private Integer type;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    private User user;
    @ManyToMany
    @JoinTable(name = "product_image",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;
}
