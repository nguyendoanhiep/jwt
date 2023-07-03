package com.tom.mycat.service;

import com.tom.mycat.entity.dto.ProductDto;
import com.tom.mycat.response.Response;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Response<?> getAll(Pageable pageable , String search);
    Response<?> addOrUpdate(ProductDto dto);
}
