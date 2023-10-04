package com.tom.restaurant.service;

import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Response<?> save();
}
