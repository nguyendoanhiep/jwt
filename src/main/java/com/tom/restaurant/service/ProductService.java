package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.ProductRequest;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Response<?> getAll(Pageable pageable , String name, Integer status , Integer type);
    Response<?> addOrUpdate(ProductRequest request);
    Response<?> getImageByProductId(Long productId);
    Response<?> setPriorityImage(Long imageId, Long productId);
    Response<?> deleteImageOfProduct(Long imageId);
    Response<?> delete(Long id);

}
