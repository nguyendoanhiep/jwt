package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.ProductDto;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Response<?> getAll(Pageable pageable , String search , Integer status , Long type);
    Response<?> addOrUpdate(ProductDto dto);
}
