package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.VoucherDto;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoucherService {
    Response<?> getAll(Pageable pageable,String name , String code , Integer status);
    Response<?> save(VoucherDto voucherDto);
    Response<?> findByNumberPhone(String numberPhone);
    Response<?> addVoucherForCustomer(List<String> numberPhoneList , Long voucherId);
    Response<?> delete(Long id);
}
