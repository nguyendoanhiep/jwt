package com.tom.restaurant.service;

import com.tom.restaurant.entity.dto.OrdersDto;
import com.tom.restaurant.entity.dto.VoucherCodeDto;
import com.tom.restaurant.response.Response;
import org.springframework.data.domain.Pageable;

public interface VoucherCodeService {
    Response<?> getAll(Pageable pageable);
    Response<?> save(VoucherCodeDto voucherCodeDto);
}
