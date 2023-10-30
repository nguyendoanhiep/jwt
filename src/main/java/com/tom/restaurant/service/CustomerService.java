package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.CustomerRequest;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Response<?> getAll(Pageable pageable, String search , Integer status);
    Response<?> findById(Long id);
    Response<?> getAllByVoucherId(Pageable pageable, String search , Long voucherId);
    Response<?> addOrUpdate(CustomerRequest request);
    Response<?> delete(Long id);

}
