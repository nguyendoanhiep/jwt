package com.tom.restaurant.service.impl;

import com.tom.restaurant.entity.Cart;
import com.tom.restaurant.repository.CartRepository;
import com.tom.restaurant.response.Response;
import com.tom.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Response<?> showCart(Long customerId) {
        try {
            return Response.SUCCESS(cartRepository.getCartResponse(customerId));
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findProductOfCustomerId(customerId, productId);
            if (cart == null) {
                cartRepository.save(Cart
                        .builder()
                        .customerId(customerId)
                        .productId(productId)
                        .quantity(1)
                        .build());
            } else {
                cart.setQuantity(cart.getQuantity() + 1);
                cartRepository.save(cart);
            }
            return Response.SUCCESS();
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> deleteProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findProductOfCustomerId(customerId, productId);
            if (cart.getQuantity() == 1) {
                cartRepository.delete(cart);
            } else {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            }
            return Response.SUCCESS();
        } catch (
                Exception e) {
            return Response.FAIL();
        }

    }
}
