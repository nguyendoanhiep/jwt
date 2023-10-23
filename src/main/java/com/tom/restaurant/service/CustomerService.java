package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.CustomerDto;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Response<?> getAll(Pageable pageable, String search , Integer status);
    Response<?> addOrUpdate(CustomerDto dto);
    Response<?> delete(Long id);

}
