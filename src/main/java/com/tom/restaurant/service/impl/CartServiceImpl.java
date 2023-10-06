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

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VoucherCodeRepository voucherCodeRepository;

    @Override
    public Response<?> showCart(Long customerId, Long voucherCodeId) {
        try {
            return Response.SUCCESS(cartRepository.showCart(customerId, voucherCodeId));
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            Product product = productRepository.findById(productId).orElse(null);
            cart.getProducts().add(product);
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> deleteProductForCart(Long customerId, Long productId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            Product product = productRepository.findById(productId).orElse(null);
            cart.getProducts().removeIf(data -> data.equals(product));
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    @Override
    public Response<?> addOrDeleteVoucher(Long customerId, Long voucherCodeId) {
        try {
            Cart cart = cartRepository.findByCustomerId(customerId);
            VoucherCode voucherCode = voucherCodeRepository.findById(voucherCodeId).get();
            if (cart.getVoucherCode() != null && cart.getVoucherCode().equals(voucherCode)) {
                cart.setVoucherCodeId(null);
                cart.setVoucherCode(null);
                cart.setDiscountAmount(0L);
            } else {
                cart.setVoucherCodeId(voucherCodeId);
                cart.setVoucherCode(voucherCode);
            }
            calculateOrder(cart);
            cartRepository.save(cart);
            return Response.SUCCESS(cart);
        } catch (Exception e) {
            return Response.FAIL();
        }
    }

    private void calculateOrder(Cart cart) {
        if (cart.getVoucherCode() != null && cart.getVoucherCode().getValue() != null) {
            cart.setDiscountAmount(cart.getVoucherCode().getValue());
        }
        if (!cart.getProducts().isEmpty()) {
            cart.setOriginalPrice(cart.getProducts().stream().mapToLong(Product::getPrice).sum());
        }
        cart.setFinalPrice(cart.getOriginalPrice() - cart.getDiscountAmount());
    }
}
