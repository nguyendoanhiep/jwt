package com.tom.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image implements Serializable {
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
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date createDate;
    @Column(name = "modified_date")
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date modifiedDate;
    @Column(name = "priority")
    private Integer priority;

}
