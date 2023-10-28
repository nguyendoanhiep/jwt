package com.tom.restaurant.service;

import com.tom.restaurant.entity.Image;
import com.tom.restaurant.response.Response;

public interface ImageService {

    Response<?> getImageByProductId(Long productId);
    Response<?> setPriorityImage(Long imageId, Long productId);
    Response<?> delete(Long id);
    Response<?> add(Image image);
}
