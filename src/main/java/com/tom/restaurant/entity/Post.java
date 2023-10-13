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
import java.util.List;

@Entity
@Data
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "status" , nullable = false)
    private Integer status;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_date", nullable = false)
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date createDate;

    @Column(name = "modified_date", nullable = false)
    @JsonFormat(pattern = "DD-MM-YYYY HH:MM:SS")
    private Date modifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_image",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;
}
