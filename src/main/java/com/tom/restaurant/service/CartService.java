package com.tom.restaurant.service;

import com.tom.restaurant.response.Response;

public interface CartService {
    Response<?> showCart(Long userId, Long voucherCodeId);

    Response<?> addProductForCart(Long customerId, Long productId);

    Response<?> deleteProductForCart(Long customerId, Long productId);

    Response<?> addOrDeleteVoucher(Long customerId, Long voucherCodeId);

}
