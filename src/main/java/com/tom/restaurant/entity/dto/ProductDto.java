package com.tom.restaurant.entity.dto;

import com.tom.restaurant.entity.Image;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer status;
    private Integer type;
    private Date createDate;
    private Date modifiedDate;
    private Long userId;
    private List<Image> images;
}
