package com.tom.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "url_image")
    private String urlImage;
    @Column(name = "create_date")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @Column(name = "priority")
    private Integer priority;

}
