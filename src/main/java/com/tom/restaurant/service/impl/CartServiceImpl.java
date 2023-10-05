package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Cart;
import com.tom.restaurant.entity.Product;
import com.tom.restaurant.entity.VoucherCode;
import com.tom.restaurant.repository.CartRepository;
import com.tom.restaurant.repository.ProductRepository;
import com.tom.restaurant.repository.VoucherCodeRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VoucherCodeRepository voucherCodeRepository;
    @Override
    public Response<?> showCart(Long userId, Long voucherCodeId) {
        try{
            return Response.SUCCESS(cartRepository.showCart(userId,voucherCodeId));
        }catch (Exception e){
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addProductOrVoucher(Long customerId, Long voucherCodeId, Long productId) {
        try{
            Cart cart = cartRepository.findByCustomerId(customerId);
            VoucherCode voucherCode = voucherCodeRepository.findById(voucherCodeId).get();
            Product product = productRepository.findById(productId).get();
            return Response.SUCCESS();
        }catch (Exception e){
            return Response.FAIL();
        }
    }
}
