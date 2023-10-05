package com.tom.restaurant.service;

import com.tom.restaurant.response.Response;

import java.util.List;

public interface CartService {
    Response<?> showCart(Long userId , Long voucherCodeId);
    Response<?> addProductOrVoucher(Long customerId, Long voucherCodeId , Long productId);

}
