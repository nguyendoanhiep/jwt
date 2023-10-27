package com.tom.restaurant.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tom.restaurant.entity.Image;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer status;
    private Integer type;
    private Long userId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createDate;
    private List<Image> images;
}
