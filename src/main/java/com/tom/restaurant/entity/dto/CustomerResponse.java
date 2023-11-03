package com.tom.restaurant.entity.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@MappedSuperclass
public class CustomerResponse implements Serializable {
    @Id
    private Long id;

    private String name;

    private String numberPhone;

    private Integer status;

    private Long loyaltyPoints;

    private Integer result;

    public CustomerResponse(Long id, String name, String numberPhone, Integer status, Long loyaltyPoints, Integer result) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
        this.status = status;
        this.loyaltyPoints = loyaltyPoints;
        this.result = result;
    }
}
